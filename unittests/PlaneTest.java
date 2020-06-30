import geometries.Plane;
import org.junit.Test;
import premitives.Point3D;
import premitives.Ray;
import premitives.Vector;

import java.awt.*;

import static org.junit.Assert.*;
/**
 * The Test Class: PlaneTest
 * @author  Simha Ben-David & Tahel Nadav
 */
public class PlaneTest {

    @Test
    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
     */
    public void getNormal() {
        Point3D point=new Point3D(2,2,3);
        Vector V=new Vector(1,2,3);
        Plane p= new Plane (point,V);

        if(p.getNormal(point).equals(V))
            assertTrue(true);
        else
            fail("ERROR "+p.getNormal(point));
    }

    /**
     * Test method for {@link geometries.Plane#findIntsersections(premitives.Ray)}.
     */
    @Test
    public void findIntsersections() {
        Plane plane=new Plane(new Point3D(1,3,0), new Point3D(1,1,0), new Point3D(3,1,0));
        // ============ Equivalence Partitions Tests ==============
        //EP1: Ray intersects the plane
        Ray ray=new Ray(new Vector(0,0,-2),new Point3D(2,2,1));
        assertEquals("Ray intersects the plane",1,plane.findIntsersections(ray).size());
        //
        //EP2: Ray does not intersect the plane
        ray=new Ray(new Vector(0,0,2),new Point3D(2,2,1));
        assertEquals("Ray does not intersect the plane",null,plane.findIntsersections(ray));

        //=============== Boundary Values Tests ==================
        // **** Group: Ray is parallel to the plane
        //BVA1: – the ray included in the plane
        ray=new Ray(new Vector(0,1,0), new Point3D(1,2,0));
        assertEquals("Ray is parallel and included to the plane",null,plane.findIntsersections(ray));

        //BVA2: – the ray not included in the plane
        ray=new Ray(new Vector(0,1,0), new Point3D(1,2,1));
        assertEquals("Ray is parallel and not included to the plane",null,plane.findIntsersections(ray));

        // **** Group: Ray is orthogonal to the plane
        //BVA3: – according to p0 before the plane
        ray=new Ray(new Vector(0,0,2), new Point3D(1,2,1));
        assertEquals("Ray is orthogonal to the plane - according to P0 before the plane",null,plane.findIntsersections(ray));

        //BVA4: – according to p0 in the plane
        //ray=new Ray(new Vector(0,0,2), new Point3D(1,2,1));
        //assertEquals("Ray is orthogonal to the plane - according to P0 in the plane",null,plane.findIntsersections(ray));

        //BVA5: – according to p0 after the plane
        ray=new Ray(new Vector(0,0,-2), new Point3D(1,2,-1));
        assertEquals("Ray is orthogonal to the plane - according to P0 after the plane",null,plane.findIntsersections(ray));
        //BVA6: Ray is neither orthogonal nor parallel to and begins at the plane
        //(p0 is in the plane, but not the ray)
        ray=new Ray(new Vector(0,1,4), new Point3D(1,2,0));
        assertEquals("Ray is orthogonal to the plane - according to P0 after the plane",null,plane.findIntsersections(ray));
        //BVA7: Ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane (Q)
        ray=new Ray(new Vector(0,2,7), new Point3D(1,3,0));
        assertEquals("Ray is orthogonal to the plane - according to P0 after the plane",null,plane.findIntsersections(ray));

    }




}