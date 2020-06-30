package geometries;

import premitives.Ray;
/**
 * The class: Cylinder representing a tube with height
 * Fields: tube's fields + height
 * implements Tube class
 * implements RadialGeometry abstract class
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * ctr thid radius ray and height
     * @param j double
     * @param r Ray
     * @param h double
     */
    Cylinder(double j, Ray r, double h) {
        super(j, r);
        _height=h;
    }

    /**
     * ctr with radial geometry object and height
     * @param j RadialGeometry
     * @param r Ray
     * @param h double
     */
    public Cylinder(RadialGeometry j, Ray r, double h) {
        super(j, r);
        _height=h;
    }

    /**
     * get height function
     * @return height of the cylinder
     */
    public double get_height() {
        return _height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Cylinder)) return false;
        Cylinder oth = (Cylinder)obj;
        return (_height==oth._height) && super.equals(obj);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "_height=" + _height +
                '}';
    }
}
