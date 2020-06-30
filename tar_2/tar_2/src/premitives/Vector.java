package premitives;

import java.awt.*;
import premitives.Coordinate;

public class Vector {
    private Point3D Point;
    public Vector(Coordinate c1,Coordinate c2,Coordinate c3) {
        Point=new Point3D(c1,c2,c3);
    }
    public Vector(double c1,double c2,double c3) throws IllegalArgumentException {
        if(new Point3D(c1,c2,c3).equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("can't create the zero vector");
        }
        Point=new Point3D(c1,c2,c3);
    }
    public Vector(Point3D p) {
        Point=new Point3D(p);
    }
    public Vector(Vector v) {
        Point=new Point3D(v.getPoint());
    }
    public Vector add(Vector v){
        if(v!=null)
            return new Vector(Point.add(v));
        return null;
    }
    public Vector subtract(Vector v){
        if(v!=null)
            return new Vector(Point.subtract(v.getPoint()));
        return null;
    }
    public Vector scale(double n) {
            return new Vector(Point.getCoord1().get() * n, Point.getCoord2().get() * n, Point.getCoord3().get() * n);
    }
    public double dotProduct(Vector v){
        return (Point.getCoord1().get()*v.Point.getCoord1().get())+(Point.getCoord2().get()*v.Point.getCoord2().get())+(Point.getCoord3().get()*v.Point.getCoord3().get());
    }
    public Vector crossProduct (Vector v)  {
        return new Vector(Point.getCoord2().get()*v.Point.getCoord3().get()-Point.getCoord3().get()*v.Point.getCoord2().get()
                , Point.getCoord3().get()*v.Point.getCoord1().get()-Point.getCoord1().get()*v.Point.getCoord3().get()
                , Point.getCoord1().get()*v.Point.getCoord2().get()-Point.getCoord2().get()*v.Point.getCoord1().get());
    }
    public double lengthSquared()    {
        return ((Point.getCoord1().get())*(Point.getCoord1().get())+(Point.getCoord2().get())*(Point.getCoord2().get())+(Point.getCoord3().get())*(Point.getCoord3().get()));
    }
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() throws Throwable {
        double ehadHelkeOrech = 1/length();

            Vector h=scale(ehadHelkeOrech);
            Point=h.getPoint();
            return this;

    }
    public Vector normalized ()throws Throwable {
        Vector h=new Vector(this);
        return h.normalize();
    }
    public Point3D getPoint() {
        return Point;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector oth = (Vector)obj;
        return Point.equals(oth.Point);
    }
    @Override
    public String toString() {
        return "Vector{" +
                "Point=" + Point +
                '}';
    }
}
