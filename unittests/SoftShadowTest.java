import elements.*;
import geometries.Geometry;
import geometries.Intersectable;
import geometries.Sphere;
import geometries.Triangle;
import org.testng.annotations.Test;
import premitives.Color;
import premitives.Material;
import premitives.Point3D;
import premitives.Vector;
import renderer.ImageWriter;
import renderer.render;
import scene.Scene;

import java.util.Random;


public class SoftShadowTest {
    @Test
    public void SoftShadowTest1() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.white), 0.00000001));
        scene.addGeometries(
                new Sphere(new Color(255, 5 , 0),new Material(0.2, 0.2, 25, 1, 0),50, new Point3D(10,-95, -10)),
                new Sphere(new Color(25,25,25), new Material(0.5, 0.5, 100), 20, new Point3D(-5, 0, 20)),
                new Sphere(new Color(0, 31, 77), new Material(0.5, 0.5, 100,0, 0 ), 40, new Point3D(-50, 30, 30)),
                new Sphere(new Color(0, 20, 7), new Material(0.5, 0.5, 100,0, 0 ), 5, new Point3D(-50, -10, -25)),
                new Sphere(new Color(0, 0, 0), new Material(0.5, 0.5, 100,0, 0 ), 5, new Point3D(-55, -47, 65)),
                new Sphere(new Color(0, 0, 0), new Material(0.5, 0.5, 100,0, 0 ), 10, new Point3D(-50, 47, -60))

        );

        Random rand = new Random();

        for(double i=0; i<100; i+=1) {
            int y = rand.nextInt(100);
            int z = rand.nextInt(100);
            scene.addGeometries(new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0), 0.5, new Point3D(-50, y, z)));
        }
        for(double i=0; i<100; i+=1) {
            int y = rand.nextInt(100);
            int z = rand.nextInt(100);
            scene.addGeometries(new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0), 0.5, new Point3D(-50, y, -z)));
        }
        for(double i=0; i<100; i+=1) {
            int y = rand.nextInt(100);
            int z = rand.nextInt(100);
            scene.addGeometries(new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0), 0.5, new Point3D(-50, -y, z)));
        }
        for(double i=0; i<100; i+=1) {
            int y = rand.nextInt(100);
            int z = rand.nextInt(100);
            scene.addGeometries(new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0), 0.5, new Point3D(-50, -y, -z)));
        }

        scene.addLights(new SpotLight(new Color(255, 204, 51), new Point3D(10,-95, -10), new Vector(-0.5, 4, 0.7), 1, 0.0000001, 0.0000001),
                new PointLight(new Color(255, 204, 102), new Point3D(60,-45, -10),1, 0.000001, 0.000001),
                new PointLight(new Color(255, 204, 102), new Point3D(60,-45, -20),1, 0.000001, 0.000001),
                new SpotLight(new Color(0, 255, 0), new Point3D(-50, 30, 60), new Vector(-1, -1,1), 1, 0.0001, 0.0001)
                );
        ImageWriter imageWriter = new ImageWriter("SoftShadowTest1", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);
        //render render = (new render(imageWriter, scene)).setMultithreading(3).setDebugPrint();
        //render.setSupersampling(true);
       // render render = new render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
}
