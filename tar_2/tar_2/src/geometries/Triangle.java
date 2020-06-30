package geometries;

import premitives.Point3D;
import premitives.Vector;

import java.util.List;

public class Triangle extends Polygon {

    public Triangle(Point3D... vertices) {
        super(vertices);
        if(vertices.length!=3)
            throw new IllegalArgumentException("You Have to send only 3 points!");

    }

    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }
}
