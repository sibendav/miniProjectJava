package geometries;

import premitives.Point3D;
import premitives.Vector;
import premitives.Point3D;
import premitives.Vector;

public class Sphere extends RadialGeometry {

    public Point3D get_center() {
        return _center;
    }

    private Point3D _center;

    Sphere(double R, Point3D center) {
        super(R);
        _center=center;
    }
    public Sphere(Sphere j) {
        super(j.get_radius());
        _center=j.get_center();
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Sphere)) return false;
        Sphere oth = (Sphere)obj;
        return _center.equals(oth._center);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                '}';
    }



}
