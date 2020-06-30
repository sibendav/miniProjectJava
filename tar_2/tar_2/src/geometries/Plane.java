package geometries;

import premitives.Point3D;
import premitives.Vector;

public class Plane implements Geometry {
    private Point3D _p;
    private Vector _normal;

    public Plane(Point3D _p, Vector _normal) {
        this._p = _p;
        this._normal = _normal;
    }
    public Plane(Point3D _p1, Point3D _p2,Point3D _p3)  {
        this._p = _p1;
        Vector v1=new Vector(_p2);
        Vector v2=new Vector(_p3);
        this._normal = v1.crossProduct(v2);
    }
    @Override
    public Vector getNormal(Point3D p) {
        return null;
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
}
