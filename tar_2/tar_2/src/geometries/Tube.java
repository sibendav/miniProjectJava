package geometries;

import premitives.Point3D;
import premitives.Vector;
import premitives.Ray;

import java.util.Objects;

public class Tube extends RadialGeometry  {
    private Ray _axisRay;

   public Tube(double j, Ray r) {
        super(j);
        _axisRay=r;
    }

    public Tube(RadialGeometry j, Ray r) {
        super(j);
        _axisRay=r;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
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
}
