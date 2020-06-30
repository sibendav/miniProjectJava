package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import premitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static premitives.Util.alignZero;

public class render {
    public static final double DELTA = 0.1;
    private Scene _scene;
    private ImageWriter _imageWriter;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    public render(Scene _scene) {
        this._scene = _scene;
    }

    public render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }


    private GeoPoint findClosestIntersection(Ray ray) {

        if (ray == null) {
            return null;
        }

        GeoPoint closestPoint = null;
        double closestDistance = Double.MAX_VALUE;
        Point3D ray_p0 = ray.getP();

        List<GeoPoint> intersections = _scene.get_geometries().findIntsersections(ray);
        if (intersections == null)
            return null;

        for (GeoPoint geoPoint : intersections) {
            double distance = ray_p0.distance(geoPoint.getPoint());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = geoPoint;
            }
        }
        return closestPoint;
    }

    public Scene get_scene() {
        return _scene;
    }

    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        java.awt.Color background = _scene.get_background().getColor();
        Camera camera = _scene.get_camera();
        Intersectable geometries = _scene.get_geometries();
        double distance = _scene.get_distance();

        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                GeoPoint closestPoint = findClosestIntersection(ray);
                if (closestPoint == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    _imageWriter.writePixel(column, row, calcColor(closestPoint, ray).getColor());
                }
            }
        }
    }

    private Color calcColor(GeoPoint geopoint, Ray inRay) {
        GeoPoint closestPoint = findClosestIntersection(inRay);
        return calcColor(closestPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.get_ambientLight().get_intensity());
    }




    /**
     * Finding the closest point to the P0 of the camera.
     *
     * @param intersectionPoints list of points, the function should find from
     *                           this list the closet point to P0 of the camera in the scene.
     * @return the closest point to the camera
     */

    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        Point3D p0=_scene.get_camera().getP();
        double dis;
        double min=Double.MAX_VALUE;
        GeoPoint p=null;
        for (GeoPoint g:intersectionPoints)
        {
            dis=p0.distance(g.getPoint());
            if(dis<min)
            {
                min=dis;
                p=g;
            }
        }
        return p;
    }

    /**
     * Printing the grid with a fixed interval between lines
     *
     * @param interval The interval between the lines.
     */
    public void printGrid(int interval, java.awt.Color colorsep) {
        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        for (int i = 0; i < Ny; i++) {
            for (int j = 0; j < Nx; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j, i, colorsep);
                }
            }
        }
    }

    public void writeToImage() {
        _imageWriter.writeToImage();
    }


    private Color calcColor(GeoPoint intersection, Ray inRay, int level, double k) {

       // Color result = _scene.get_ambientLight().get_intensity();
        Color color=(intersection.getGeometry().getEmmission());
        List<LightSource> lights = _scene.get_lights();
        if (level == 0 || k < MIN_CALC_COLOR_K) return Color.BLACK;

        Vector v = intersection.getPoint().subtract(_scene.get_camera().getP()).normalize();
        Vector n = intersection.getGeometry().getNormal(intersection.getPoint());

        Material material = intersection.getGeometry().getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        double kr = intersection._geometry.getMaterial().getKr();
        double   kkr = k * kr;
        if (_scene.get_lights() != null) {
            color = color.add(GetLightsSource(intersection, color, lights, v, n, nShininess, kd, ks,k));
        }
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay( intersection._point, inRay,n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null)
                color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));

        }
        double kt = intersection.getGeometry().getMaterial().getKt();
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K)
        { Ray refractedRay = constructRefractedRay( intersection.getPoint(), inRay,n) ;
        GeoPoint refractedPoint = findClosestIntersection(refractedRay);
        if (refractedPoint != null)
            color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
        }
            return color;

    }

    private Color GetLightsSource(GeoPoint intersection, Color result, List<LightSource> lights, Vector v, Vector n, int nShininess, double kd, double ks,double k) {
        for (LightSource lightSource : lights) {

            Vector l = lightSource.getL(intersection.getPoint());
            double nl = alignZero(n.dotProduct(l));
            double nv = alignZero(n.dotProduct(v));
            if (nl * nv > 0) {
          double ktr=transparency(lightSource, l, n, intersection);
                if ((ktr) * k > MIN_CALC_COLOR_K) {

                    Color ip = lightSource.getIntensity(intersection.getPoint()).scale(ktr);
                    result = result.add(
                            calcDiffusive(kd, nl, ip),
                            calcSpecular(ks, l, n, nl, v, nShininess, ip)
                    );

                }
            }
        }
        return result;
    }


    private boolean sign(double val) {
        return (val > 0d);
    }

    /**
     * Calculate Specular component of light reflection.
     *
     * @param ks         specular component coef
     * @param l          direction from light to point
     * @param n          normal to surface at the point
     * @param nl         dot-product n*l
     * @param v          direction from point of view to point
     * @param nShininess shininess level
     * @param ip         light intensity at the point
     * @return specular component light effect at the point
     * @author Dan Zilberstein
     * <p>
     * Finally, the Phong model has a provision for a highlight, or specular, component, which reflects light in a
     * shiny way. This is defined by [rs,gs,bs](-V.R)^p, where R is the mirror reflection direction vector we discussed
     * in class (and also used for ray tracing), and where p is a specular power. The higher the value of p, the shinier
     * the surface.
     */
    private Color calcSpecular(double ks, Vector l, Vector n, double nl, Vector v, int nShininess, Color ip) {
        double p = nShininess;

        Vector R = l.add(n.scale(-2 * nl)); // nl must not be zero!
        double minusVR = -alignZero(v.dotProduct(R));
        if (minusVR <= 0) {
            return Color.BLACK; // view from direction opposite to r vector
        }
        return ip.scale(ks * Math.pow(minusVR, p));
    }
    private Ray constructRefractedRay(Point3D pointGeo, Ray inRay, Vector n) {
        return new Ray(pointGeo, inRay.getDirection(), n);
    }

    private Ray constructReflectedRay(Point3D pointGeo, Ray inRay, Vector n) {

        Vector v = inRay.getDirection();
        double vn = v.dotProduct(n);

        if (vn == 0) {
            return null;
        }

        Vector r = v.subtract(n.scale(2 * vn));
        return new Ray(pointGeo, r, n);
    }
    /**
     * Calculate Diffusive component of light reflection.
     *
     * @param kd diffusive component coef
     * @param nl dot-product n*l
     * @param ip light intensity at the point
     * @return diffusive component of light reflection
     * @author Dan Zilberstein
     * <p>
     * The diffuse component is that dot product n•L that we discussed in class. It approximates light, originally
     * from light source L, reflecting from a surface which is diffuse, or non-glossy. One example of a non-glossy
     * surface is paper. In general, you'll also want this to have a non-gray color value, so this term would in general
     * be a color defined as: [rd,gd,bd](n•L)
     */
    private Color calcDiffusive(double kd, double nl, Color ip) {
        if (nl < 0)
            nl = -nl;
        return ip.scale(nl * kd);
    }
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint._point.add(delta);
        Ray lightRay = new Ray( lightDirection,point);
        List<GeoPoint> intersections = _scene.get_geometries().findIntsersections(lightRay);
        if (intersections ==null) return 1.0;
        double lightDistance = light.getDistance(geopoint.getPoint());
        double ktr = 1.0; 
        for (GeoPoint gp : intersections)
        { 
            if (alignZero(gp.getPoint().distance(geopoint.getPoint()) - lightDistance) <= 0)
            {
                ktr *= gp.getGeometry().getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) return 0.0; } 
        }
        return ktr;



    }

}
