package elements;

import premitives.Color;
import premitives.Point3D;
import premitives.Vector;
/**
 * The Class: PointLight extends Light implements LightSource
 * @author  Simha Ben-David and Tahel Nadav
 */
public class PointLight extends Light implements LightSource {
    Point3D _position;
    double _kC;
    double _kL;
    double _kQ;

    /**
     * PointLight ctr
     @param colorIntensity color
      * @param position point
     * @param kC double value
     * @param kL double value
     * @param kQ double value
     */
    public PointLight(Color colorIntensity, Point3D position, double kC, double kL, double kQ) {
        super(colorIntensity);
        //this._intensity = colorIntensity;
        this._position = new Point3D(position);
        this._kC = kC;
        this._kL = kL;
        this._kQ = kQ;
    }

    /**
     * getIntensity func
     * @return color
     */
    public Color getIntensity() {
        return super.get_intensity();
    }

    /**
     * overriding LightSource getIntensity(Point3D)
     * @param p point
     * @return color
     */
    @Override
    public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        return (_intensity.reduce(_kC + _kL * d + _kQ * dsquared));
    }

    /**
     * getDistance overrided func
     * @param point point
     * @return distance double
     */
    @Override
    public double getDistance(Point3D point) {
        return _position.distance(point);
    }
    /**
     * getL func
     * @param p point
     * @return Light vector
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        }
        return p.subtract(_position).normalize();
    }


}
