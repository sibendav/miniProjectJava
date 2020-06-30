package geometries;

import primitives.Point3D;

import java.util.List;

public class Triangle extends Polygon {

    public Triangle(Point3D... vertices) {
            super(vertices);
        if(vertices.length!=3)
            throw new IllegalArgumentException("You Have to send only 3 points!");
    }
}
