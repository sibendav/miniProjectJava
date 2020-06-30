package geometries;

import premitives.Point3D;
import premitives.Vector;

/**
 * The interface: Geometry representing a geometry object
 * Function: getNormal
 * @author  Simha Ben-David & Tahel Nadav
 */
public interface Geometry extends Intersectable{
    //decleration od must impified function thar returning the object normal
    public Vector getNormal(Point3D p);
}
