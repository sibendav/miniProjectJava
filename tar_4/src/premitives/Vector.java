package premitives;

import java.awt.*;
import premitives.Coordinate;
/**
 * The class: Vector
 * Fields: a point in 3D space
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Vector {
    private Point3D Point;

    //ctr with 3 coordinates
    public Vector(Coordinate c1,Coordinate c2,Coordinate c3) {
        Point=new Point3D(c1,c2,c3);
    }
    //ctr with 3 doubles,
    // throws exception if it is the zero vector
    public Vector(double c1,double c2,double c3) throws IllegalArgumentException {
        Point3D N=new Point3D(c1,c2,c3);
        if(N.equals(new Point3D(0,0,0))) {
            throw new IllegalArgumentException("the zero vector");
        }
      else
        Point=new Point3D(c1,c2,c3);
    }
    //ctr with point
    public Vector(Point3D p) {
        Point=new Point3D(p);
    }
    //copy ctr
    public Vector(Vector v) {
        Point=new Point3D(v.getPoint());
    }
    //The function that adds 2 vectors
    public Vector add(Vector v){
        if(v!=null)
            return new Vector(Point.add(v));
        return null;
    }
    //The function that subtract between two vectors
    public Vector subtract(Vector v)throws Throwable{
        return new Vector(getPoint().getCoord1().get()-v.getPoint().getCoord1().get(),getPoint().getCoord2().get()-v.getPoint().getCoord2().get(),getPoint().getCoord3().get()-v.getPoint().getCoord3().get());
        /*if(v!=null)
            return new Vector(Point.subtract(v.getPoint()));
        return null;*/
    }
    //The vector mult in double
    public Vector scale(double n)   {
            return new Vector(Point.getCoord1().get() * n, Point.getCoord2().get() * n, Point.getCoord3().get() * n);
    }
    //dot prodact between 2 vectors returning double
    public double dotProduct(Vector v){
        return (Point.getCoord1().get()*v.Point.getCoord1().get())+(Point.getCoord2().get()*v.Point.getCoord2().get())+(Point.getCoord3().get()*v.Point.getCoord3().get());
    }
    //cross prodact between 2  vectors returning vector
    public Vector crossProduct (Vector v) {
        return new Vector(Point.getCoord2().get() * v.Point.getCoord3().get() - Point.getCoord3().get() * v.Point.getCoord2().get()
                , Point.getCoord3().get() * v.Point.getCoord1().get() - Point.getCoord1().get() * v.Point.getCoord3().get()
                , Point.getCoord1().get() * v.Point.getCoord2().get() - Point.getCoord2().get() * v.Point.getCoord1().get());
    }
    //vector length in squared
    public double lengthSquared()    {
        return ((Point.getCoord1().get())*(Point.getCoord1().get())+(Point.getCoord2().get())*(Point.getCoord2().get())+(Point.getCoord3().get())*(Point.getCoord3().get()));
    }
    //vector length
    public double length()    {
        return Math.sqrt(lengthSquared());
    }
    // normelizing this Vector
    public Vector normalize()  {
        double ehadHelkeOrech = 1/length();
        Vector h=scale(ehadHelkeOrech);
        Point=h.getPoint();
        return this;
    }
    //returning normal to this vector
    public Vector normalized () {
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
