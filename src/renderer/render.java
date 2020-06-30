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
    private Scene _scene;
    private ImageWriter _imageWriter;

    public render(Scene _scene) {
        this._scene = _scene;
    }

    public render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
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
        double height =_imageWriter.getHeight();

        //Nx and Ny are the width and height of the image.
        int Nx = _imageWriter.getNx(); //columns
        int Ny = _imageWriter.getNy(); //rows
        //pixels grid
        for (int row = 0; row < Ny; row++) {
            for (int column = 0; column < Nx; column++) {
                Ray ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(column, row, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(column, row, calcColor(closestPoint).getColor());
                }
            }
        }
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


    private Color calcColor(GeoPoint intersection) {

        Color result = _scene.get_ambientLight().get_intensity();
        result = result.add(intersection.getGeometry().get_emmission());
        List<LightSource> lights = _scene.get_lights();

        Vector v = intersection.getPoint().subtract(_scene.get_camera().getP()).normalize();
        Vector n = intersection.getGeometry().getNormal(intersection.getPoint());

        Material material = intersection.getGeometry().get_material();
        int nShininess = material.getnShininess();
        double kd = material.getKd();
        double ks = material.getKs();
        if (_scene.get_lights() != null) {
            for (LightSource lightSource : lights) {

                Vector l = lightSource.getL(intersection.getPoint());
                double nl = alignZero(n.dotProduct(l));
                double nv = alignZero(n.dotProduct(v));

                if (sign(nl) == sign(nv)) {
                    Color ip = lightSource.getIntensity(intersection.getPoint());
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
}
