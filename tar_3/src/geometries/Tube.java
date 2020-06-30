package geometries;

import premitives.Point3D;
import premitives.Vector;
import premitives.Ray;

import java.util.List;
import java.util.Objects;
/**
 * The class: Tube representing a tube in 3D space
 * Fields: ray
 * implements RadialGeometry abstract class
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Tube extends RadialGeometry  {
    private Ray _axisRay;
    //ctr with radius and ray
   public Tube(double j, Ray r) {
        super(j);
        _axisRay=r;
    }
    //ctr with radialGeometry and ray
    public Tube(RadialGeometry j, Ray r) {
        super(j);
        _axisRay=r;
    }

    @Override
    //imlements of getNormal func
    public Vector getNormal(Point3D p) {
        double t = _axisRay.getDirection().dotProduct(p.subtract(_axisRay.getP()));
        Point3D o=new Point3D(_axisRay.getP().add(_axisRay.getDirection().scale(t)));
        Vector n=new Vector(p.subtract(o)).normalize();
        return n;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Tube)) return false;
        Tube oth = (Tube)obj;
        return _axisRay.equals(oth._axisRay) && Objects.equals(get_radius(), oth.get_radius());

    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                '}';
    }

    public Ray get_axisRay() {
        return _axisRay;
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }
}
