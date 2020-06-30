package geometries;

import premitives.Ray;
import premitives.Vector;

/**
 * The class: Cylinder representing a tube with height
 * Fields: tube's fields + height
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Cylinder extends Tube {
    private double _height;

    //ctr thid radius ray and height
    Cylinder(double j, Ray r, double h) {
        super(j, r);
        _height=h;
    }

    //ctr with radial geometry object and height
    public Cylinder(RadialGeometry j, Ray r, double h) {
        super(j, r);
        _height=h;
    }

    public double get_height() {
        return _height;
    }

    public Vector getNormal() {
        return  (new Vector(this.get_axisRay().getDirection())).normalize();
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
