package geometries;

import premitives.Material;
import premitives.Point3D;
import premitives.Vector;
import premitives.Color;

import javax.swing.*;
import java.util.Objects;

/**
 * The interface: Geometry representing a geometry object
 * Function: getNormal
 * @author  Simha Ben-David and Tahel Nadav
 */
public abstract class Geometry implements Intersectable{

    protected Color _emmission;
    protected Material _material;

    /**
     * get_emmission func
     * @return color
     */
    public Color getEmmission() {
        return _emmission;
    }

    /**
     * get_material func
     * @return material
     */
    public Material getMaterial() {
        return _material;
    }
    /**
     * decleration of must implemented function that returning the object's normal
     * @param p Point3D
     * @return normal vector
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * ctr geometry
     * @param _emmission color
     */
    public Geometry(Color _emmission) {
        this._emmission = _emmission;
        _material=new Material(0,0,0);
    }

    /**
     * initial geometry ctr
     */
    public Geometry() {
        this(Color.BLACK, new Material(0d, 0d, 0));
    }

    /**
     * ctr geomerty with material
     * @param emission color
     * @param material maretial
     */
    public Geometry(Color emission, Material material) {
        this._emmission = new Color(emission);
        this._material = new Material(material.getKd(),material.getKs(),material.getnShininess(),material.getKt(),material.getKr());
    }

}
