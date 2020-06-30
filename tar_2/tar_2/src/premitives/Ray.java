package premitives;

public class Ray {
    Vector Direction;
    Point3D p;

    public Ray(Vector direction, Point3D p) {
        Direction = direction;
        this.p = p;
    }

    public Vector getDirection() {
        return Direction;
    }

    public Point3D getP() {
        return p;
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
}
