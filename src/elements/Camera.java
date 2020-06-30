package elements;

import premitives.Point3D;
import premitives.Ray;
import premitives.Util;
import premitives.Vector;

import static premitives.Util.isZero;

/**
 * The Class: Camera
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Camera {
    private Point3D  p;
    private Vector to;
    private Vector up;
    private Vector right;

    /**
     * ctr camera
     * @param p1 point
     * @param to1 to direction
     * @param up1 up direction
     * @throws Throwable
     */
    public Camera(Point3D p1, Vector to1, Vector up1)  {
        //if the the vectors are not orthogonal, throw exception.
        if (up1.dotProduct(to1) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this.p = new Point3D(p1);
        this.to = to1.normalized();
        this.up = up1.normalized();

        right = this.to.crossProduct(this.up).normalize();
    }

    /**
     * construct Ray Through Pixel function
     * @param nX X's pixel length
     * @param nY Y's pixel length
     * @param j X index of the shoting ray
     * @param i Y index of the shoting ray
     * @param screenDistance
     * @param screenWidth
     * @param screenHeight
     * @return the builded ray
     */
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
            Pij = (Pij.subtract(up.scale(yi).getPoint())).getPoint(); // Pij.add(_vUp.scale(-yi))
        }

        Vector Vij = Pij.subtract(p);

        return new Ray( Vij,p);
    }

    /**
     * get P function
     * @return
     */
    public Point3D getP() {
        return p;
    }
    /**
     * get TO vector function
     * @return
     */
    public Vector getTo() {
        return to;
    }
    /**
     * get Up vector function
     * @return
     */
    public Vector getUp() {
        return up;
    }
    /**
     * get Right vector function
     * @return
     */
    public Vector getRight() {
        return right;
    }


}
