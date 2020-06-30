package primitives;

public class Point3D {
    private Coordinate coord1;
    private Coordinate coord2;
    private Coordinate coord3;
    public static final Point3D ZERO=new Point3D(0,0,0);
    public Point3D(Coordinate c1,Coordinate c2,Coordinate c3) {
        coord1 = c1;
        coord2 = c2;
        coord3 = c3;
    }
    public Point3D(double c1,double c2,double c3) {
        coord1 = new Coordinate(c1);
        coord2 = new Coordinate(c2);
        coord3 = new Coordinate(c3);
    }
    public Point3D(Point3D P) {
        coord1 = new Coordinate(P.getCoord1());
        coord2 = new Coordinate(P.getCoord2());
        coord3 = new Coordinate(P.getCoord3());
    }
     public Vector subtract (Point3D P){
        return new Vector(getCoord1()._coord- P.getCoord1()._coord, getCoord2()._coord- P.getCoord2()._coord, getCoord3()._coord- P.getCoord3()._coord);
     }
     public Point3D add (Vector p) {
        return new Point3D(p.getPoint().getCoord1()._coord+coord1._coord,p.getPoint().getCoord2()._coord+coord2._coord,p.getPoint().getCoord3()._coord+coord3._coord);
     }
     public double distanceSquared(Point3D p){
        double c1= (p.getCoord1()._coord-coord1._coord)*(p.getCoord1()._coord-coord1._coord);
        double c2= (p.getCoord2()._coord-coord2._coord)*(p.getCoord2()._coord-coord2._coord);
        double c3= (p.getCoord3()._coord-coord3._coord)*(p.getCoord3()._coord-coord3._coord);
        return c1+c2+c3;
     }
     public double distance(Point3D p)
     {
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D)obj;
        return coord1.equals(oth.coord1) && coord2.equals(oth.coord2) && coord3.equals(oth.coord3);
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
