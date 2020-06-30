package geometries;

import premitives.Point3D;
import premitives.Ray;

import java.util.List;

public interface Intersectable {
    List<Point3D> findIntsersections(Ray ray);
}
