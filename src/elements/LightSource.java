package elements;

import premitives.Color;
import premitives.Point3D;
import premitives.Vector;
/**
 * The interface LightSource
 * @author  Simha Ben-David & Tahel Nadav
 */
public interface LightSource {
    /**
     * getIntensity function
     * @param p point
     * @return color
     */
    public Color getIntensity(Point3D p);

    /**
     * getL
     * @param p point
     * @return vector
     */
    public Vector getL(Point3D p);

}
