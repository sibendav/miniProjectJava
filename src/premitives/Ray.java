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


    /**
     * ctr Ray
     * @param direction vector
     * @param p point
     */
    public Ray(Vector direction, Point3D p) {
        _direction = direction.normalize();
        this._p = p;
    }

    /**
     * ctr Ray func
     * @param point point
     * @param direction vector
     * @param normal vector
     */
    public Ray(Point3D point, Vector direction, Vector normal) {
        _direction = new Vector(direction).normalized();

        double nv = normal.dotProduct(direction);

        Vector normalDelta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _p = point.add(normalDelta);
    }

    /**
     * getDirection func
     * @return direction vector
     */
    public Vector getDirection() {
        return new Vector(_direction);
    }

    /**
     * getP func
     * @return initial point
     */
    public Point3D getP() {
        return new Point3D(_p);
    }

    /**
     * getTargetPoint func
     * @param length double
     * @return Point3D that exist in this length
     */
    public Point3D getTargetPoint(double length) {
        return isZero(length) ? _p : _p.add(_direction.scale(length));
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

}
