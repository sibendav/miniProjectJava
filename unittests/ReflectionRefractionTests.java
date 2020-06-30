import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import premitives.Color;
import premitives.Material;
import premitives.Point3D;
import premitives.Vector;
import renderer.ImageWriter;
import renderer.render;
import scene.Scene;

public class ReflectionRefractionTests {

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
                        new Point3D(0, 0, 50)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(10000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400),  new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
        render render = new render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     *  producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
        render render = new render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void ourimage()
    {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(20,500,-30), new Vector(0, -1,0), new Vector(0, 0, 1)));
        scene.set_distance(500);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //


                new Sphere(new Color(java.awt.Color.BLUE),
                        new Material(0.2, 0.2, 30, 1, 0), // )
                        28, new Point3D(-12, -20, -22)),
                new Sphere(new Color(java.awt.Color.GREEN),
                        new Material(0.2, 0.2, 30, 1, 0), // )
                        20, new Point3D(34, -20,-30)),
                new Sphere(new Color(java.awt.Color.RED),
                        new Material(0.2, 0.2, 30, 1, 0), // )
                        15, new Point3D(-40, 130, -35)),
                new Sphere(new Color(java.awt.Color.MAGENTA),
                        new Material(0.2, 0.2, 30, 1, 0), // )
                        11, new Point3D(68, 100,-39))
               /* new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(-1500, -100, -100),
                        new Point3D(1500, 5000, -100), new Point3D(3000, -3000, -100))*/,
        new Triangle(new Color(20, 20, 20), new Material(1, 1, 1, 0, 1), new Point3D(1500, 5000, -50),
                new Point3D(-3000, -3000, -50), new Point3D(670, -670, -50)));
        scene.addLights(new SpotLight(new Color(700, 800, 50), //
                new Point3D(20,2000,-30), new Vector(0, -1,0), 1, 4E-5, 2E-7),
                new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1))
               /* new PointLight(new Color(100, 800, 450),
                        new Point3D(45, 200, -10),
                        1, 0.0005, 0.0005)*/
               , new SpotLight(new Color(700, 800, 50), //
                        new Point3D(-50, -20,-50), new Vector(0, 0, 1), 1, 4E-5, 2E-7));
        ImageWriter imageWriter = new ImageWriter("ourimage", 200, 200, 600, 600);
        render render = new render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();



    }
}

