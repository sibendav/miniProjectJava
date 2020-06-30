package elements;

import premitives.Color;
import premitives.Point3D;
import premitives.Vector;

import static premitives.Util.isZero;
/**
 * The Class: SpotLight extends PointLight
 * @author  Simha Ben-David & Tahel Nadav
 */
public class SpotLight extends PointLight {
    Vector _direction;

    /**
     * SpotLight ctr
     * @param colorIntensity
     * @param position
     * @param direction
     * @param kC
     * @param kL
     * @param kQ
     */
    public SpotLight(Color colorIntensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(colorIntensity, position, kC, kL, kQ);
        this._direction = new Vector(direction).normalize();
    }
    @Override
    public Color getIntensity(Point3D p) {
        double projection = _direction.dotProduct(getL(p));

        if (isZero(projection)) {
            return Color.BLACK;
        }
        double factor = Math.max(0, projection);
        Color pointlightIntensity = super.getIntensity(p);
        return (pointlightIntensity.scale(factor));
    }



}
