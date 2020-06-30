package geometries;

import premitives.Point3D;
import premitives.Vector;

public interface Geometry {
    public Vector getNormal(Point3D p);
}
