import org.junit.Before;
import org.junit.Test;
import premitives.Vector;
import premitives.Point3D;
import static org.junit.Assert.*;

public class VectorTest {



    @Test
    public void add() {
        Vector v1=new Vector(3, 6, 5);
        Vector v2=new Vector(9, 1, 2);
        Vector v4=new Vector(12,7,7);

        assertTrue(v4.equals(v1.add(v2)));

    }

    @Test
    public void subtract() {
        Vector v1=new Vector(3, 6, 5);
        Vector v2=new Vector(9, 1, 2);
        Vector v4=new Vector(-6,5,3);
        assertEquals(true, v4.equals(v1.subtract(v2)));

    }

    @Test
    public void scale() {
        Vector v1=new Vector(3, 6, 5);
        Vector v2=new Vector(6, 12, 10);
        assertEquals(true, v2.equals(v1.scale(2)));
    }

    @Test
    public void dotProduct() {
        Vector v= new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        double b=v.dotProduct(v2);
        if(b!=-28)
        {
            fail("The dotProduct() for parallel vectors does not throw an exception");
        }

    }

    @Test
    public void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", Point3D.ZERO.equals(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", Point3D.ZERO.equals(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

    }

    @Test
    public void lengthSquared() {
        Vector v = new Vector(0, -3, 4);
        double b = v.lengthSquared();
        if (b != 25)
            fail("Didn't made a right calculation");
    }

    @Test
    public void length() {
        Vector v = new Vector(0, -3, 4);
        double b = v.length();
        if (b != 5)
            fail("Didn't made a right calculation");
    }


    @Test
    public void normalize() throws Throwable {
        Vector v = new Vector(3.5,-5,10);
        v.normalize();
        assertEquals(""
                , 1, v.length(),1e-10);
        v = new Vector(0,0,0);
        try {
            v.normalize();
            fail("Didn't throw divide by zero exception!");
        } catch (ArithmeticException e) {
            assertTrue(true);
        }
    }

    @Test
    public void normalized() {
    }
}