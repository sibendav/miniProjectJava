package premitives;

import static premitives.Util.isZero;

/**
 * The class: Ray a ray in 3D space
 * includes direction vector and a point
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Ray {
    Vector Direction;
    Point3D p;


    //ctr with vector and point
    public Ray(Vector direction, Point3D p) {
        Direction = direction.normalize();
        this.p = p;
    }

    public Vector getDirection() {
        return new Vector(Direction);
    }

    public Point3D getP() {
        return new Point3D(p);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray)obj;
        return Direction.equals(oth.Direction) && p.equals(oth.p);

    }

    @Override
    public String toString() {
        return "Ray{" +
                "Direction=" + Direction +
                ", p=" + p +
                '}';
    }
    public Point3D getTargetPoint(double length) {
        return isZero(length) ? p : p.add(Direction.scale(length));
    }
}
