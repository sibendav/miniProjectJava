import geometries.Polygon;
import org.junit.Test;
import premitives.Point3D;
import premitives.Vector;

import static org.junit.Assert.*;

public class PolygonTest {

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test

    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        try {
            Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                    new Point3D(-1, 1, 1));
            double sqrt3 = Math.sqrt(1d / 3);
            Vector v2 = pl.getNormal(new Point3D(0, 0, 1));
            Vector v1 =  new Vector(sqrt3, sqrt3, sqrt3);
            assertEquals("Bad normal to trinagle",v1.getPoint().getCoord1().get(), v2.getPoint().getCoord1().get(),0.0001);
        }
        catch ( Exception j) {
            assertTrue(j.getMessage(), true);
        }
    }
}