import elements.*;
import geometries.Geometry;
import geometries.Intersectable;
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

import java.util.List;
import java.util.Random;

public class SolarSystemTest {

    @Test
    public void sunTest1(){
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, 1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(153, 0 , 0),new Material(0.2, 0.2, 25, 1, 0),50, new Point3D(0, 0, 50))
                /*new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0),0.9, new Point3D(20, 10, 50))
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300), new Point3D(0, 50, 52), new Point3D(30, -20, 52), new Point3D(-30, -20, 52))*/
                );
/*
        Random rand = new Random();

        for(double i=5; i<=95; i+=0.5) {
            int r;
            if(i<50)
                r = rand.nextInt((int)(i+1));
            else
                r = rand.nextInt((int)(100-i));
            if(i%2==0)
                r*=-1;
            scene.addGeometries(new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0), 0.5, new Point3D((0 + r), (50 - i), -50)));

        }

        rand = new Random();
        for(double i=5; i<=95; i+=0.5) {
            int r;
            if(i<=50)
                r = rand.nextInt((int)(i+1));
            else
                r = rand.nextInt((int)(100-i));
            if(i%2==0)
                r*=-1;
            scene.addGeometries(new Sphere(new Color(java.awt.Color.orange), new Material(0.2, 0.2, 30, 1, 0), 0.5, new Point3D((0 + r), (50 - i), -50)));

        }
        rand = new Random();
        for(double i=5; i<=95; i+=0.5) {
            int r;
            if(i<=50)
                r = rand.nextInt((int)(i+1));
            else
                r = rand.nextInt((int)(100-i));
            if(i%2==0)
                r*=-1;
            scene.addGeometries(new Sphere(new Color(java.awt.Color.red), new Material(0.2, 0.2, 30, 1, 0), 0.5, new Point3D((0 + r), (50 - i), -50)));

        }

*/
        scene.addLights(new SpotLight(new Color(255, 204, 51), new Point3D(0, 0, 50), new Vector(1, -1, 2), 1, 0.00001, 0.00001),

                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(0, 0, -1), 1, 0.00001, 0.00001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(1, 0, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(-1, 0, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(0, 1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(0, -1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(1, 1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(-1, -1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(1, -1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(-1, 1, 0), 1, 0.0000001, 0.0000001)
                /*new SpotLight(new Color(255, 204, 51), new Point3D(0, 0, 50),new Vector(1,1,0), 1, 0.001, 0.00001)
                new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1))

                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 0, 15), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 0, -15), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(40, 0, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(-40, 0, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 40, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, -40, 0), 1, 0.00001, 0.00001)
                ,new PointLight(new Color(100, 0, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.00001)*/);

        ImageWriter imageWriter = new ImageWriter("sunTest1", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void sunTest2(){
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(153, 0 , 0),new Material(0.2, 0.2, 25, 1, 0),50, new Point3D(0, 0, 50))
                /*new Sphere(new Color(java.awt.Color.yellow), new Material(0.2, 0.2, 30, 1, 0),0.5, new Point3D(20, 10, 50))
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300), new Point3D(0, 50, 52), new Point3D(30, -20, 52), new Point3D(-30, -20, 52))*/
        );


        scene.addLights(new SpotLight(new Color(255, 204, 51), new Point3D(0, 0, 50), new Vector(1, -1, 2), 1, 0.00001, 0.00001),

                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(0, 0, -1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(1, 0, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(-1, 0, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(0, 1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 150), new Vector(0, -1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 100), new Vector(1, 1, -0.1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 100), new Vector(-1, -1, -0.1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 100), new Vector(1, -1, -0.1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(0, 0, 100), new Vector(-1, 1, -0.1), 1, 0.0000001, 0.0000001)
                /*new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1))
                new PointLight(new Color(100, 350, 0), new Point3D(0, 0, 50), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 0, 15), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 0, -15), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(40, 0, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(-40, 0, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 40, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, -40, 0), 1, 0.00001, 0.00001)
                ,new PointLight(new Color(100, 0, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.00001)*/);

        ImageWriter imageWriter = new ImageWriter("sunTest2", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sunTest3(){
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                //new Sphere(new Color(java.awt.Color.white), new Material(0.2, 0.2, 30, 1, 0),55, new Point3D(-105, 0, 0)),
                        new Sphere(new Color(153, 0 , 0),new Material(0.2, 0.2, 25, 1, 0),50, new Point3D(0, 0, 0)
                )
            /*
                new Triangle(Color.BLACK, new Material(0.8, 0.2, 300), new Point3D(0, 50, 52), new Point3D(30, -20, 52), new Point3D(-30, -20, 52))*/
        );


        scene.addLights(new SpotLight(new Color(255, 204, 51), new Point3D(0, 0, 0), new Vector(-1, -1, 0), 1, 0.00001, 0.00001),
                //new SpotLight(new Color(255, 255, 255), new Point3D(-150, 0, 0), new Vector(1, 0, 0), 1, 0.00001, 0.00001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-100, 0, 0), new Vector(1, 0, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-100, 0, 0), new Vector(0, 0, 1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-100, 0, 0), new Vector(0, 0, -1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-100, 0, 0), new Vector(0, 1, 0), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-100, 0, 0), new Vector(0, -1, 0), 1, 0.0000001, 0.0000001),

                new SpotLight(new Color(500, 300, 0), new Point3D(-50, 0, 0), new Vector(0.1, 1, 1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-50, 0, 0), new Vector(0.1, 1, -1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-50, 0, 0), new Vector(0.1, -1, -1), 1, 0.0000001, 0.0000001),
                new SpotLight(new Color(500, 300, 0), new Point3D(-50, 0, 0), new Vector(0.1, -1, 1), 1, 0.0000001, 0.0000001)

                // new SpotLight(new Color(500, 30, 50), new Point3D(5, 5, 26), new Vector(1, 1, 1), 1, 0.00001, 0.00001)
                /*new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1))
                new PointLight(new Color(100, 350, 0), new Point3D(0, 0, 50), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 0, 15), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 0, -15), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(40, 0, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(-40, 0, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, 40, 0), 1, 0.00001, 0.00001),
                new PointLight(new Color(java.awt.Color.ORANGE), new Point3D(0, -40, 0), 1, 0.00001, 0.00001)
                ,new PointLight(new Color(100, 0, 0), new Point3D(-50, 50, -50), 1, 0.00001, 0.00001)*/);

        ImageWriter imageWriter = new ImageWriter("sunTest3", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void JupiterTest() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        //scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(150, 100, 50), new Material(0.2, 0.2, 30, 0.6, 0), 51, new Point3D(0, 0, 0))
                //new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50, new Point3D(0, 0, 0))
                /*new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 47, new Point3D(0, 1, 5)),
                new Sphere(new Color(java.awt.Color.green), new Material(0.5, 0.5, 100), 42, new Point3D(0, 1, -5))*/);
        scene.addLights(/*new DirectionalLight(new Color(500, 300, 0), new Vector(1, 1, 1))*/);
        ImageWriter imageWriter = new ImageWriter("JupiterTest", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void MoonTest() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        //scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(25,25,25), new Material(0.5, 0.5, 100), 51, new Point3D(0, 0, 0))
                //new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50, new Point3D(0, 0, 0))
                /*new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 47, new Point3D(0, 1, 5)),
                new Sphere(new Color(java.awt.Color.green), new Material(0.5, 0.5, 100), 42, new Point3D(0, 1, -5))*/);
        scene.addLights(new DirectionalLight(new Color(400, 300, 200), new Vector(1, 5, 0)));
        ImageWriter imageWriter = new ImageWriter("MoonTest", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void NeptuneTest() {
        Scene scene = new Scene("Test scene");
        scene.set_camera(new Camera(new Point3D(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 0, 1)));
        scene.set_distance(1000);
        scene.set_background(Color.BLACK);
        //scene.set_ambientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        scene.set_ambientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(25,25,125), new Material(0.5, 0.5, 1000), 51, new Point3D(0, 0, 0))
                /*new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 47, new Point3D(0, 1, 5)),
                new Sphere(new Color(java.awt.Color.green), new Material(0.5, 0.5, 100), 42, new Point3D(0, 1, -5))*/);
        scene.addLights(new DirectionalLight(new Color(100, 300, 200), new Vector(-1, 1, 0)));
        ImageWriter imageWriter = new ImageWriter("NeptuneTest", 150, 150, 500, 500);
        render render = new render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
    }

}
