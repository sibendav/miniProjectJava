package geometries;

import premitives.Color;
import premitives.Material;

import java.util.Objects;
/**
 * The class: RadialGeometry representing an object with radius
 * Fields: radius
 * * implements Geometry interface
 * @author  Simha Ben-David and Tahel Nadav
 */
public abstract class RadialGeometry extends Geometry {
    double _radius;

    /**
     * ctr with radius
     * @param j double
     */
    RadialGeometry(double j)
    {
        _radius=j;
    }

    /**
     * get radius function
     * @return this radial geometry radius
     */
    public double get_radius() {
        return _radius;
    }

    /**
     * copy ctr
     * @param j RadialGeometry
     */
    public RadialGeometry(RadialGeometry j)
    {
        _radius=j._radius;
    }

    /**
     * RadialGeometry ctr with material
     * @param emissionLight
     * @param radius
     * @param material
     */
    public RadialGeometry(Color emissionLight, double radius, Material material) {
        super(emissionLight, material);
        _radius=radius;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof RadialGeometry)) return false;
        RadialGeometry oth = (RadialGeometry)obj;
        return Objects.equals(_radius, oth._radius);

    }

    @Override
    public String toString() {
        return "RadialGeometry{" +
                "_radius=" + _radius +
                '}';
    }
}
