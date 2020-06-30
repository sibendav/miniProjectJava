package geometries;

import premitives.Point3D;
import premitives.Ray;
import premitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static premitives.Util.alignZero;
import static premitives.Util.isZero;

/**
 * The class: Plane representing a Plane in 3D space
 * Fields: Point and normal vector
 * implements Geometry interface
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Plane implements Geometry {
    private Point3D _p;
    private Vector _normal;

    //ctr with point and normal vec
    public Plane(Point3D _p, Vector _normal) {
        this._p = new Point3D(_p);
        this._normal = new Vector(_normal);
    }
    //ctr with 3 points
    public Plane(Point3D _p1, Point3D _p2,Point3D _p3)  {
        this._p =new Point3D(_p1);
        Vector v1=_p1.subtract(_p2);
        Vector v2=_p1.subtract(_p3);
        this._normal = v1.crossProduct(v2).normalize();
    }
    ///
    @Override
    //imlements of getNormal func
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    public Point3D get_p() {
        return _p;
    }

    public Vector getNormal() {
        return _normal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Plane)) return false;
        Plane oth = (Plane)obj;
        return _p.equals(oth._p) && _normal.equals(oth._normal);
    }

    @Override
    public String toString() {
        return "Plane{" +
                "_p=" + _p +
                ", _normal=" + _normal +
                '}';
    }

    @Override
    public List<Point3D> findIntsersections(Ray R) {
        Vector p0Q;
        try {
            p0Q = _p.subtract(R.getP());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct(R.getDirection());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(p0Q) / nv);

        return t <= 0 ? null : List.of(R.getTargetPoint(t));
    }
}
