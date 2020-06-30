package geometries;

import premitives.Point3D;
import premitives.Ray;
import premitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static premitives.Util.isZero;

/**
 * The class: Triangle representing a Triangle in 3D space
 *  implements Polygon class
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Triangle extends Polygon {


    @Override
    //imlements of getNormal fun
    public Vector getNormal(Point3D p){
        return super.getNormal(p);
    }

    //ctr with 3 points
    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        super(_p1, _p2, _p3);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }
    public List<Point3D> findIntsersections(Ray ray) {
        List<Point3D> intersections = _plane.findIntsersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.getP();
        Vector v = ray.getDirection();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;




    }








}
