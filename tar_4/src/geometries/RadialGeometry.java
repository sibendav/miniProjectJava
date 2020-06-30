package geometries;

import java.util.Objects;
/**
 * The class: RadialGeometry representing an object with radius
 * Fields: radius
 * * implements Geometry interface
 * @author  Simha Ben-David & Tahel Nadav
 */
public abstract class RadialGeometry implements Geometry{
    double _radius;

    //ctr with radius
    RadialGeometry(double j)
    {
        _radius=j;
    }

    public double get_radius() {
        return _radius;
    }
    //copy ctr
    public RadialGeometry(RadialGeometry j)
    {
        _radius=j._radius;
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
