package premitives;

/**
 * The class: Point3D representing a point in 3D space
 * Fields: 3 coordinates
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Point3D {
    Coordinate _coord1;
    Coordinate _coord2;
    Coordinate _coord3;

    /**
     * the final Zero Point
     */
    public static final Point3D ZERO=new Point3D(0,0,0);

    /**
     * ctr with 3 coordinate
     * @param c1 coordinate value
     * @param c2 coordinate value
     * @param c3 coordinate value
     */
    public Point3D(Coordinate c1,Coordinate c2,Coordinate c3) {
        _coord1 = new Coordinate(c1);
        _coord2 = new Coordinate(c2);
        _coord3 = new Coordinate(c3);
    }

    /**
     * ctr with 3 double
     * @param d1 double value
     * @param d2 double value
     * @param d3 double value
     */
    public Point3D(double d1,double d2,double d3) {
        _coord1 = new Coordinate(d1);
        _coord2 = new Coordinate(d2);
        _coord3 = new Coordinate(d3);
    }

    /**
     * copy ctr
     * @param P Point3D value
     */
    public Point3D(Point3D P) {
        _coord1 = new Coordinate(P._coord1);
        _coord2 = new Coordinate(P._coord2);
        _coord3 = new Coordinate(P._coord3);
    }

    /**
     * subtract function between 2 diffrent Point and returning direction vector
     * @param P Point3D value that substract from
     * @return Th substracted vector
     */
    public Vector subtract (Point3D P){
        Vector V= new Vector(_coord1.get()- P._coord1.get(), _coord2.get()- P._coord2.get(), _coord3.get()- P._coord3.get());
        return V;
    }

    /**
     * add function between Point and vector returning a point
     * @param p Vector value
     * @return the added vector
     */
    public Point3D add (Vector p) {
        return new Point3D(p.getPoint()._coord1.get()+ _coord1.get(),p.getPoint().getCoord2().get()+ _coord2.get(),p.getPoint().getCoord3().get()+ _coord3.get());
    }

    /**
     * distance Squared function
     * @param p Point3D value
     * @return sum double
     */
    public double distanceSquared(Point3D p){
        double c1= (p.getCoord1().get()- _coord1.get())*(p.getCoord1().get()- _coord1.get());
        double c2= (p.getCoord2().get()- _coord2.get())*(p.getCoord2().get()- _coord2.get());
        double c3= (p.getCoord3().get()- _coord3.get())*(p.getCoord3().get()- _coord3.get());
        return c1+c2+c3;
    }

    /**
     * return the distance between 2 points
     * @param p Point3D
     * @return the distance double value
     */
    public double distance(Point3D p) {
        return Math.sqrt(distanceSquared(p));
    }

    /**
     * getCoord1 function
     * @return coord1 Coordinate
     */
    public Coordinate getCoord1() {
        return _coord1;
    }

    /**
     * getCoord2 function
     * @return coord2 Coordinate
     */
    public Coordinate getCoord2() {
        return _coord2;
    }

    /**
     * getCoord3 function
     * @return coord3 Coordinate
     */
    public Coordinate getCoord3() {
        return _coord3;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D)obj;
        if (_coord1.get()==oth._coord1.get() && _coord2.get()==(oth._coord2).get() && _coord3.get()==(oth._coord3).get())
            return  true;
        else
            return  false;
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "coord1=" + _coord1 +
                ", coord2=" + _coord2 +
                ", coord3=" + _coord3 +
                '}';
    }
}
