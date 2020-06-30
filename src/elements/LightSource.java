package elements;

import premitives.Color;
import premitives.Point3D;
import premitives.Vector;
/**
 * The interface LightSource
 * @author  Simha Ben-David and Tahel Nadav
 */
public interface LightSource {
    /**
     * getIntensity function
     * @param p point
     * @return color
     */
    public Color getIntensity(Point3D p);
    double getDistance(Point3D point);
    /**
     * getL
     * @param p point
     * @return vector
     */
    public Vector getL(Point3D p);

}
