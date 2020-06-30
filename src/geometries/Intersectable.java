package geometries;

import premitives.Point3D;
import premitives.Ray;

import java.util.List;

/**
 * The interface: Intersectable representing an Intersectable object
 * Function: getNormal
 * @author  Simha Ben-David and Tahel Nadav
 */
public interface Intersectable {
    /**
     * findIntsersections function
     * @param ray Ray
     * @return intsersections points
     */
    List<GeoPoint> findIntsersections(Ray ray,double max);
    default List<GeoPoint> findIntsersections(Ray ray) {
        return findIntsersections(ray, Double.POSITIVE_INFINITY);
    }
    /**
     * The inner class: GeoPoint representing a point of geometry
     */
    public static class GeoPoint {
        public Geometry _geometry;
        public Point3D _point;

        /**
         * ctr GeoPoint
         * @param _geometry Geometry
         * @param pt that the point is related to
         */
        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            this._point = pt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return _geometry.equals(geoPoint._geometry) && _point.equals(geoPoint.getPoint());
        }

        /**
         * getPoint func
         * @return Point3D
         */
        public Point3D getPoint() {
            return _point;
        }

        /**
         * getGeometry func
         * @return Geometry
         */
        public Geometry getGeometry() {
            return _geometry;
        }


    }

}
