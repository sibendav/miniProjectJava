package elements;

import premitives.Point3D;
import premitives.Ray;
import premitives.Util;
import premitives.Vector;

import static premitives.Util.isZero;

public class Camera {
    private Point3D  p;
    private Vector to;
    private Vector up;
    private Vector right;

    public Camera(Point3D p1, Vector to1, Vector up1) {
        //if the the vectors are not orthogonal, throw exception.
        if (up1.dotProduct(to1) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this.p = new Point3D(p1);
        this.to = to1.normalized();
        this.up = up1.normalized();

        right = this.to.crossProduct(this.up).normalize();
    }

    public Ray constructRayThroughPixel (int nX, int nY,
                                         int j, int i, double screenDistance,
                                         double screenWidth, double screenHeight){
        if (isZero(screenDistance)) {
            throw new IllegalArgumentException("distance cannot be 0");
        }

        Point3D Pc =p.add(to.scale(screenDistance));

        double Ry = screenHeight / nY;
        double Rx = screenWidth / nX;

        double yi = ((i - nY / 2d) * Ry + Ry / 2d);
        double xj = ((j - nX / 2d) * Rx + Rx / 2d);

        Point3D Pij = Pc;

        if (!isZero(xj)) {
            Pij = Pij.add(right.scale(xj));
        }
        if (!isZero(yi)) {
            Pij = Pij.subtract((up.scale(yi))); // Pij.add(_vUp.scale(-yi))
        }

        Vector Vij = Pij.subtract(p);

        return new Ray( Vij,p);
    }

    public Point3D getP() {
        return p;
    }
    public Vector getTo() {
        return to;
    }

    public Vector getUp() {
        return up;
    }

    public Vector getRight() {
        return right;
    }


}
