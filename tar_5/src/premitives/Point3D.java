package premitives;
import premitives.Coordinate;

/**
 * The class: Point3D representing a point in 3D space
 * Fields: 3 coordinates
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Point3D {
    private Coordinate coord1;
    private Coordinate coord2;
    private Coordinate coord3;

    //the final Zero Point
    public static final Point3D ZERO=new Point3D(0,0,0);

    //ctr with 3 coordinate,
    public Point3D(Coordinate c1,Coordinate c2,Coordinate c3) {
        coord1 = c1;
        coord2 = c2;
        coord3 = c3;
    }
    public Point3D subtract(Vector v) {
        return new Point3D(this.coord1._coord - v.getPoint().coord1._coord,
                this.coord2._coord - v.getPoint().coord2._coord,
                this.coord3._coord - v.getPoint().coord3._coord);
    }


    //ctr with 3 double
    public Point3D(double c1,double c2,double c3) {
        coord1 = new Coordinate(c1);
        coord2 = new Coordinate(c2);
        coord3 = new Coordinate(c3);
    }

    //copy ctr
    public Point3D(Point3D P) {
        coord1 = new Coordinate(P.getCoord1());
        coord2 = new Coordinate(P.getCoord2());
        coord3 = new Coordinate(P.getCoord3());
    }
    //subtract function between 2 diffrent Point and returning direction vector
    public Vector subtract (Point3D P){
        Vector V= new Vector(getCoord1().get()- P.getCoord1().get(), getCoord2().get()- P.getCoord2().get(), getCoord3().get()- P.getCoord3().get());
        return V;
    }
    //add function between Point and vector returning  a point
    public Point3D add (Vector p) {
        return new Point3D(p.getPoint().getCoord1().get()+coord1.get(),p.getPoint().getCoord2().get()+coord2.get(),p.getPoint().getCoord3().get()+coord3.get());
    }
    public double distanceSquared(Point3D p){
        double c1= (p.getCoord1().get()-coord1.get())*(p.getCoord1().get()-coord1.get());
        double c2= (p.getCoord2().get()-coord2.get())*(p.getCoord2().get()-coord2.get());
        double c3= (p.getCoord3().get()-coord3.get())*(p.getCoord3().get()-coord3.get());
        return c1+c2+c3;
    }
    //return the distance between 2 points
    public double distance(Point3D p) {
        return Math.sqrt(distanceSquared(p));
    }
    public Coordinate getCoord1() {
        return coord1;
    }

    public Coordinate getCoord2() {
        return coord2;
    }

    public Coordinate getCoord3() {
        return coord3;
    }

    @Override
    //comparing between 2 point
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D)obj;
        if (coord1.get()==oth.coord1.get() && coord2.get()==(oth.coord2).get() && coord3.get()==(oth.coord3).get())
            return  true;
        else
            return  false;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "coord1=" + coord1 +
                ", coord2=" + coord2 +
                ", coord3=" + coord3 +
                '}';
    }
}
