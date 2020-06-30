package geometries;

import premitives.*;

import java.util.ArrayList;
import java.util.List;

import static premitives.Util.isZero;

/**
 * The class: Triangle representing a Triangle in 3D space
 *  implements Polygon class
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Triangle extends Polygon {


    @Override
    /**
     * imlements of getNormal function
     */
    public Vector getNormal(Point3D p){
        return super.getNormal(p);
    }

    /**
     * ctr with 3 points
     * @param _p1 Point3D
     * @param _p2 Point3D
     * @param _p3 Point3D
     */
    public Triangle(Point3D _p1, Point3D _p2, Point3D _p3) {
        super(_p1, _p2, _p3);

    }

    /**
     *
     * @param emissionLight color
     * @param material Material
     * @param p1 Point3D
     * @param p2 Point3D
     * @param p3 Point3D
     */
    public Triangle(Color emissionLight, Material material, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight, material, p1, p2, p3);
    }

    /**
     *
     * @param emissionLight color
     * @param p1 Point3D
     * @param p2 Point3D
     * @param p3 Point3D
     */
    public Triangle(Color emissionLight, Point3D p1, Point3D p2, Point3D p3) {
        super(emissionLight,p1, p2, p3);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    /**
     * the function finds intsersections points between ray and Triangle
     * @param ray ray that cuts thr traingle
     * @return Intsersections points
     */
    public List<GeoPoint> findIntsersections(Ray ray,double max) {
        List<GeoPoint> planeIntersections = _plane.findIntsersections(ray, max);
        if (planeIntersections == null) return null;

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

        if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) {
            //for GeoPoint
            for (GeoPoint geo : planeIntersections) {
                geo._geometry = this;
            }
            return planeIntersections;
        }

        return null;

    }

}
