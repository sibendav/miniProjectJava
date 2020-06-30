package premitives;

import static premitives.Util.isZero;
import static renderer.render.DELTA;

/**
 * The class: Ray a ray in 3D space
 * includes direction vector and a point
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Ray {
    Vector _direction;
    Point3D _p;


    //ctr with vector and point
    public Ray(Vector direction, Point3D p) {
        _direction = direction.normalize();
        this._p = p;
    }
    public Ray(Point3D point, Vector direction, Vector normal) {
        _direction = new Vector(direction).normalized();

        double nv = normal.dotProduct(direction);

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _p = point.add(normalDelta);
    }
    public Vector getDirection() {
        return new Vector(_direction);
    }

    public Point3D getP() {
        return new Point3D(_p);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray)obj;
        return _direction.equals(oth._direction) && _p.equals(oth._p);

    }

    @Override
    public String toString() {
        return "Ray{" +
                "Direction=" + _direction +
                ", p=" + _p +
                '}';
    }
    public Point3D getTargetPoint(double length) {
        return isZero(length) ? _p : _p.add(_direction.scale(length));
    }
}
