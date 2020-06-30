import geometries.Sphere;
import geometries.Tube;
import org.junit.Test;
import premitives.Point3D;
import premitives.Ray;
import premitives.Vector;

import static org.junit.Assert.*;

/**
 * The Test Class: TubeTest
 * @author  Simha Ben-David & Tahel Nadav
 */
public class TubeTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(premitives.Point3D)}.
     */
    @Test
    //testing the getnormal function
    public void getNormal() {
        Tube tube = new Tube(1.0, new Ray(new Vector(0, 1, 0), new Point3D(0, 0, 1)));
        assertEquals("Bad normal to tube", new Vector(0, 0, 1), tube.getNormal(new Point3D(0, 0.5, 2)));
    }
}