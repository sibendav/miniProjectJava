package elements;

import premitives.Color;
import premitives.Point3D;
import premitives.Vector;
/**
 * The Class: DirectionalLight  extends Light implements LightSource
 * @author  Simha Ben-David & Tahel Nadav
 */
public class DirectionalLight  extends Light implements LightSource {
    private Vector _direction;

    /**
     * DirectionalLight ctr
     * @param colorintensity
     * @param direction
     */
    public DirectionalLight(Color colorintensity, Vector direction)  {
        super(colorintensity);
        _direction = direction.normalized();
    }

    /**
     * getIntensity
     * @param p point
     * @return color
     */
    public Color getIntensity(Point3D p) {
        return super.get_intensity();
        //       return _intensity;
    }

    //instead of getDirection()
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }
}
