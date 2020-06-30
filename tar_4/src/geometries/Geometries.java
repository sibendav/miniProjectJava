package geometries;

import premitives.Point3D;
import premitives.Ray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Geometries implements Intersectable {
    private List<Intersectable> _geometries;
    public Geometries()
    {
        _geometries=new ArrayList();
    }
    public Geometries(Intersectable... geometries)
    {
            add(geometries);
    }
    public void add(Intersectable... geometries)
    {
        for (Intersectable geo : geometries ) {
            _geometries.add(geo);
        }

    }
   public List<Point3D> findIntsersections(Ray ray)
    {
        List<Point3D> intersections = null;

        for (Intersectable geo : _geometries) {
            List<Point3D> tempIntersections = geo.findIntsersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
        //return (intersections.size() == 0) ? null : intersections.stream().distinct().collect(Collectors.toList());
    }
}
