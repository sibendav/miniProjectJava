package geometries;

import premitives.*;
import premitives.Point3D;
import premitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static premitives.Util.alignZero;

/**
 * The class: Sphere representing a Sphere in the space
 * Fields: center point
 *  implements RadialGeometry abstract class
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Sphere extends RadialGeometry {

    public Point3D get_center() {
        return _center;
    }

    private Point3D _center;

    //ctr with point and radius
    public Sphere(Point3D center,double R) {
        super(R);
        _center=center;
    }
    //copy ctr
    public Sphere(Sphere j) {
        super(j.get_radius());
        _center=j.get_center();
    }

    @Override
    //implements the getNurmal func
    public Vector getNormal(Point3D p) {

        Vector v = p.subtract(_center);
        return v;
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


    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        Point3D p0 = ray.getP();
        Vector v = ray.getDirection();
        Vector u;
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(ray.getTargetPoint(_radius));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(ray.getTargetPoint(t1), ray.getTargetPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getTargetPoint(t1));
        else
            return List.of(ray.getTargetPoint(t2));
    }
}
