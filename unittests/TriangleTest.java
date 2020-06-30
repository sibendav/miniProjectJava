import geometries.Triangle;
import org.junit.Test;
import premitives.Point3D;
import premitives.Ray;
import premitives.Vector;

import static org.junit.Assert.*;
/**
 * The Test Class: TriangleTest
 * @author  Simha Ben-David & Tahel Nadav
 */
public class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(premitives.Point3D)}.
     */
    @Test
    //testing the getnormal function
    public void getNormal() {
        Point3D p1=new Point3D(0,1,0);
        Point3D p2=new Point3D(1,1,1);
        Point3D p3=new Point3D(2,0,2);
        Triangle t=new Triangle(p1,p2,p3);
        Vector v=new Vector(1,0,-1);
        v.normalize();
      // System.out.println(""+v.toString());
        if(t.getNormal(p3).equals(v)==true)
            assertTrue(true);
        else fail("ERROR"+t.getNormal(p3));

    }
    /**
     * Test method for {@link geometries.Triangle#findIntsersections(premitives.Ray)}.
     */
    @Test
    public void testFindIntsersections(){
        Point3D p1 = new Point3D(1, 1, 0);
        Point3D p2 = new Point3D(2,5, 0);
        Point3D p3 = new Point3D(7,1, 0);

        Triangle t1 = new Triangle( p1, p2, p3);
        //Outside against edge
        Point3D p4 = new Point3D(2,3,3);
        Ray ray=new Ray(new Vector(-1,-1,-3),p4);
        assertEquals("not  intersect",null,t1.findIntsersections(ray));
        //Outside against vertex
        // p4 = new Point3D(-5,-1,1);
        ray=new Ray(new Vector(-1.5,-1.5,-3),p4);
        assertEquals("not  intersect",null,t1.findIntsersections(ray));
        //Inside polygon/triangle
        ray=new Ray(new Vector(0,0,-3),p4);
        assertEquals("  intersect",1,t1.findIntsersections(ray).size());
        boolean b=new Point3D(2,3,0).equals(t1.findIntsersections(ray).get(0));

        //BVA
        // In vertex on(1,1,0)
        ray=new Ray(new Vector(-1,-2,-3),p4);
        assertEquals("not  intersect",null,t1.findIntsersections(ray));
        // On edge on (4,1,0)
        ray=new Ray(new Vector(2,-2,-3),p4);
        assertEquals("not  intersect",null,t1.findIntsersections(ray));
        // On edge's continuation
        ray=new Ray(new Vector(6,-2,-3),p4);
        assertEquals("not  intersect",null,t1.findIntsersections(ray));

    }
}