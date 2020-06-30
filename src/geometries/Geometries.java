package geometries;

import premitives.Point3D;
import premitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class: Geometries representing a geometry object
 * implements Intersectable
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Geometries implements Intersectable {
    private List<Intersectable> _geometries;

    /**
     * initial ctr for geometry class
     */
    public Geometries()
    {
        _geometries=new ArrayList();
    }

    /**
     * ctr of geometry with parameters
     * @param geometries  Intersectable
     */
    public Geometries(Intersectable... geometries)
    {
            add(geometries);
    }

    /**
     * adding geometries function
     * @param geometries Intersectable
     */
    public void add(Intersectable... geometries)
    {
        for (Intersectable geo : geometries ) {
            _geometries.add(geo);
        }

    }

    /**
     * findIntsersections function
     * @param ray Ray
     * @param max double
     * @return the intersection point of the ray with the ray
     */
   public List<GeoPoint> findIntsersections(Ray ray,double max)
    {
        List<GeoPoint> intersections = null;

        for (Intersectable geo : _geometries) {
            List<GeoPoint> tempIntersections = geo.findIntsersections(ray, max);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new LinkedList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
    }
}
