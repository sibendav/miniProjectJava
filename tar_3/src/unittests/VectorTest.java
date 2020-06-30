import org.junit.Before;
import org.junit.Test;
import premitives.Vector;
import premitives.Point3D;
import static org.junit.Assert.*;
/**
 * The Test Class: VectorTest
 * @author  Simha Ben-David & Tahel Nadav
 */
public class VectorTest {



    @Test
    // //testing the add function
    public void add() {
        Vector v1 = new Vector(3, 6, 5);
        Vector v2 = new Vector(9, 1, 2);
        Vector v4 = new Vector(12, 7, 7);

      if(v4.equals(v1.add(v2)))
        assertTrue(true);
      else
      fail("ERROR" );
    }

    @Test
    //testing the subtract function
    public void subtract() throws Throwable {
        Vector v1 = new Vector(3, 6, 5);
        Vector v2 = new Vector(9, 1, 2);
        Vector v4 = new Vector(-6, 5, 3);
        if (v4.equals(v1.subtract(v2)))
            assertTrue(true);
        else fail("ERROR" + v1.toString());
    }

    @Test
    //testing the scale function
    public void scale() {
        Vector v1 = new Vector(3, 6, 5);
        Vector v2 = new Vector(6, 12, 10);
        if (v2.equals(v1.scale(2)))
            assertTrue(true);
        else fail("ERROR " + v1.toString());
    }

    @Test
    //testing the dotproduct function
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
    //testing the crossproduct function
    public void crossProduct() {
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(4,2,0);
        v1.crossProduct(v2);
        Vector v3=v1.crossProduct(v2);
        if(v3.getPoint().getCoord1().get()==-6&&v3.getPoint().getCoord2().get()==12&&v3.getPoint().getCoord3().get()==-6)
            assertTrue(true);
        else fail("ERROR "+v3.toString());


    }

    @Test
    //testing the lengthsquarde function
    public void lengthSquared() {
        Vector v = new Vector(0, -3, 4);
        double b = v.lengthSquared();
        if (b != 25)
            fail("Didn't made a right calculation");
    }

    @Test
    //testing the length function
    public void length() {
        Vector v = new Vector(0, -3, 4);
        double b = v.length();
        if (b != 5)
            fail("Didn't made a right calculation");
    }

    @Test
    //testing the normelize function
    public void normalize() throws Throwable {
        Vector v = new Vector(3.5,-5,10);
        v.normalize();
        assertEquals(""
                , 1, v.length(),1e-10);

    }

    @Test
    //testing the normalized function
    public void normalized() {
        Vector v = new Vector(3.5,-5,10);
        v.normalize();
        assertEquals(""
                , 1, v.length(),1e-10);

    }
}