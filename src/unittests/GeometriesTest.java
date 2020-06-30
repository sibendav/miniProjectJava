import geometries.*;
import org.junit.Test;
import premitives.Point3D;
import premitives.Ray;
import premitives.Vector;

import java.awt.*;
import java.util.List;

import static org.junit.Assert.*;
/**
 * The Test Class: GeometriesTest
 * @author  Simha Ben-David & Tahel Nadav
 */
public class GeometriesTest {

    /**
     * Test method for {@link geometries.Geometries#findIntsersections(premitives.Ray)}.
     */
    @Test
    public void findIntsersections() {
        //BVA
        //
        Point3D p4 = new Point3D(2,3,3);
        Ray ray=new Ray(new Vector(-1,-1,-3),p4);
        Geometries h=new Geometries();
        assertEquals("no intersections points",null,h.findIntsersections(ray));
        //BVA
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);
        Plane plane=new Plane(new Point3D(4,-1,0), new Point3D(2,-1,0),new Point3D(2,-3,0));
        ray= new Ray( new Vector(1, 1, 0),new Point3D(-1, 0, 0));
        h.add(sphere , plane);
        assertEquals("no intersections points",null,h.findIntsersections(ray));
        //BVA intersact the sphere
        // List<Point3D> result=sphere.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(1, 0, 0)));
        plane=new Plane(new Point3D(-1,-1,0),new Point3D(-3,-1,0),new Point3D(-4,-4,0));
        Geometries k=new Geometries();
        k.add(plane,sphere);
        assertEquals("intersact the sphere",1,k.findIntsersections(new Ray( new Vector(1, 0, 0),new Point3D(1, 0, 0))).size());

        //triangle and plane intersect,sphere not

        ray=new Ray(new Vector(0,0,-2),new Point3D(2,2,1));
        plane=new Plane(new Point3D(1,3,0), new Point3D(1,1,0), new Point3D(3,1,0));
        Triangle t=new Triangle(new Point3D(3,3,0),new Point3D(1,3,0),new Point3D(2,-1,0));
        sphere=new Sphere(new Point3D(2,3,5), 1d );
        Geometries p=new Geometries();
        p.add(plane,t,sphere);
        assertEquals("Intersect the plane and the traingle",2,p.findIntsersections(ray).size());
        //all the geometry intersect
        ray=new Ray( new Vector(1, 0, 0),new Point3D(1, 0, 0));
        sphere = new Sphere(new Point3D(1, 0, 0), 1d);
        List<Intersectable.GeoPoint> J=  sphere.findIntsersections(ray);
        plane=new Plane(new Point3D(3,5,0),new Point3D(3,1,-3),new Point3D(5,-1,3));
        Geometries j=new Geometries();
        j.add(sphere,plane);
        assertEquals("Intersect all the geomerties",2,j.findIntsersections(ray).size());


    }
}