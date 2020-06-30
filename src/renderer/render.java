package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.Intersectable;
import premitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static premitives.Util.alignZero;

/**
 * The Class: render
 * @author  Simha Ben-David and Tahel Nadav
 */

public class render {


    private int _threads = 1;
    private final int SPARE_THREADS = 2;
    private boolean _print = false;

    /**
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     *
     * @author Dan
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (render.this._print) System.out.printf("\r %02d%%", _percents);
        }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {
        }

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_print&&_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                target.row=this.row;
                target.col=this.col;
                if (_print&&_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (render.this._print) System.out.printf("\r %02d%%", percents);
            if (percents >= 0)
                return true;
            if (render.this._print) System.out.printf("\r %02d%%", 100);
            return false;
        }
    }

    /**
     * This function renders image's pixel color map from the scene included with
     * the Renderer object
     */


    public static final double DELTA = 0.1;
    private Scene _scene;
    private ImageWriter _imageWriter;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    /**
     * ctr render
     * @param _scene Scene
     */
    public render(Scene _scene) {
        this._scene = _scene;
    }

    /**
     * ctr render
     * @param imageWriter ImageWriter
     * @param scene Scene
     */
    public render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    /**
     * findClosestIntersection func
     * @param ray Ray
     * @return GeoPoint closest
     */
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

    /**
     * get_scene func
     * @return Scene
     */
    public Scene get_scene() {
        return _scene;
    }

    /**
     * Filling the buffer according to the geometries that are in the scene.
     * This function does not creating the picture file, but create the buffer pf pixels
     */
    public void renderImage() {
        Camera camera = _scene.get_camera();
        java.awt.Color background = _scene.get_background().getColor();
        double distance = _scene.get_distance();
        Intersectable geometries = _scene.get_geometries();

        int Nx = _imageWriter.getNx();
        int Ny = _imageWriter.getNy();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();



	    /*for (int row = 0; row < Ny; row++) {
	        for (int collumn = 0; collumn < Nx; collumn++)
	        {
	               Ray ray = camera.constructRayThroughPixel(Nx, Ny, collumn, row, distance, width, height);
	               GeoPoint closestPoint=findCLosestIntersection(ray);
	               if (closestPoint == null) {
	                   image.writePixel(collumn, row, background);
	               } else {
	                   image.writePixel(collumn, row, calcColor(closestPoint,ray).getColor());
	               }
	        }
	    }*/

        final Pixel thePixel = new Pixel(Ny, Nx); // Main pixel management object
        // Generate threads
        Thread[] threads = new Thread[_threads];
        for (int i = _threads - 1; i >= 0; --i) {
            threads[i] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {

                    Ray ray = camera.constructRayThroughPixel(Nx, Ny, pixel.col, pixel.row, distance, width, height);
                    List<GeoPoint> intersectionPoints = geometries.findIntsersections(ray);
                    if( intersectionPoints==null)
                    {
                        _imageWriter.writePixel(pixel.col, pixel.row, background);
                    }
                    else
                    {
                        GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                        _imageWriter.writePixel(pixel.col, pixel.row, calcColor(Nx, Ny, pixel.col, pixel.row, distance, width, height).getColor());
                    }
                }
            });
        }

        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
        if (_print)
            System.out.printf("\r100%%\n");

    }
    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of coress less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public render setMultithreading(int threads) {
        if (threads < 0)
            throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
        if (threads != 0)
            _threads = threads;
        else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2)
                _threads = 1;
            else
                _threads = cores;
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public render setDebugPrint() {
        _print = true;
        return this;
    }



    /**
     * calcColor func, calcolating the pixel color
     * @param geopoint GeoPoint to color
     * @param inRay Ray that coming to pixel
     * @return color
     */
    private Color calcColor(GeoPoint geopoint, Ray inRay) {
        GeoPoint closestPoint = findClosestIntersection(inRay);
        return calcColor(closestPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.get_ambientLight().get_intensity());
    }

    private Color calcColor(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {

        Color color = Color.BLACK;

        color = calcSubColor(nX, nY, j, i, screenDistance, screenWidth, screenHeight, 0);
        color = color.add(_scene.get_ambientLight().get_intensity());
        return color;
    }
    //////////////////////////////////////////////////////////////////////////////////////תיקוןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןןן
    private Color calcSubColor(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight, int depth){
        Camera camera = _scene.get_camera();
        Color bkg = _scene.get_background();
        List<Ray> fourCorners = camera.constructRayBeamThroughPixel(nX, nY, j, i, screenDistance, screenWidth, screenHeight);
        Color[] fiveColors = new Color[5];

        boolean areEqual = true;

        Color color=new Color();
        int v=0;

        for(Ray ray : fourCorners) {

            // Claculate the color of the ray
            GeoPoint gp = findClosestIntersection(ray);
            color = color.add(gp == null ? bkg : calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, 1d));

            fiveColors[v++]=color;

        }

        for(int n=1; n<5; n++) {
            areEqual &= fiveColors[n-1].equals(fiveColors[n]);
        }

        // Change to all equal in the list
        if(areEqual)
            return fiveColors[0];


        // If we arrived at the thrashold
        if(depth == 3){

            Color sumColor = new Color();
            for(Color c : fiveColors){
                sumColor =  sumColor.add(c);
            }

            return sumColor.reduce(5.5);
        }

        // The complicated part

        Color sumColor = new Color();

        // Sum all the sub colors;

        // Top right
        sumColor =sumColor.add(calcSubColor(nX*2, nY*2, j*2+1, i*2, screenDistance, screenWidth, screenHeight, depth+1));

        // Top left
        sumColor =sumColor.add(calcSubColor(nX*2, nY*2, j*2, i*2, screenDistance, screenWidth, screenHeight, depth+1));

        // Bottom right
        sumColor =sumColor.add(calcSubColor(nX*2, nY*2, j*2+1, i*2+1, screenDistance, screenWidth, screenHeight, depth+1));

        // Bottom left
        sumColor =sumColor.add(calcSubColor(nX*2, nY*2, j*2, i*2+1, screenDistance, screenWidth, screenHeight, depth+1));

        return sumColor.reduce(5.5);
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

    /**
     * calcColor func for special location
     * @param intersection GeoPoint
     * @param inRay Ray
     * @param level int
     * @param k double
     * @return color
     */
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

    /**
     * GetLightsSource func
     * @param intersection GeoPoint
     * @param result color
     * @param lights List<LightSource>
     * @param v vector
     * @param n vector
     * @param nShininess int
     * @param kd double value
     * @param ks double value
     * @param k double value
     * @return color
     */
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
