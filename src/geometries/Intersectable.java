package geometries;

import premitives.Point3D;
import premitives.Ray;

import java.util.List;

/**
 * The interface: Intersectable representing an Intersectable object
 * Function: getNormal
 * @author  Simha Ben-David & Tahel Nadav
 */
public interface Intersectable {
    /**
     * findIntsersections function
     * @param ray Ray
     * @return intsersections points
     */
    List<GeoPoint> findIntsersections(Ray ray);

    /**
     * The inner class: GeoPoint representing a point of geometry
     */
    public static class GeoPoint {
        public Geometry _geometry;
        public Point3D point;

        /**
         * ctr GeoPoint
         * @param _geometry
         * @param pt that the point is related to
         */
        public GeoPoint(Geometry _geometry, Point3D pt) {
            this._geometry = _geometry;
            point = pt;
        }
        @Override
        public boolean equals(Object obj){

            if(point.equals(((GeoPoint) obj).point)&&_geometry.equals(((GeoPoint) obj)._geometry))
                return true;
            return  false;
        }

        /**
         * getPoint func
         * @return
         */
        public Point3D getPoint() {
            return point;
        }

        /**
         * getGeometry func
         * @return
         */
        public Geometry getGeometry() {
            return _geometry;
        }


    }

}
