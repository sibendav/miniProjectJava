package premitives;

/**
 * The class: Material
 *
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Material {
    double _kd;
    double _ks;
    int _nShininess;
    double _kt =0d;
    double _kr =0d;

    /**
     * Material ctr
     *
     * @param kd
     * @param ks
     * @param nShininess
     * @param kt
     * @param kr
     */
    public Material(double kd, double ks, int nShininess, double kt, double kr) {
        this._kd = kd;
        this._ks = ks;
        this._nShininess = nShininess;
        this._kt = kt;
        this._kr = kr;
    }

    /**
     *
     * @param kd
     * @param ks
     * @param nShininess
     */
    public Material(double kd, double ks, int nShininess) {
        this(kd, ks, nShininess, 0d, 0d);
    }

    /**
     * copy ctor
     * @param material
     */
    public Material(Material material){
        this(material._kd,material._ks, material._nShininess,material._kt,material._kr);
    }

    //getters

    /**
     *
     * @return _kd
     */
    public double getKd() {
        return _kd;
    }

    public double getKs() {
        return _ks;
    }

    public int getnShininess() {
        return _nShininess;
    }

    public double getKt() {
        return _kt;
    }

    public double getKr() {
        return _kr;
    }
}
