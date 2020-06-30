package premitives;
/**
 * The class: Material
 * @author  Simha Ben-David & Tahel Nadav
 */
public class Material {
    double kd;
    double ks;
    int nShininess;

    /**
     * Material ctr
     * @param _kd
     * @param _ks
     * @param _nShininess
     */
   public Material(double _kd,double _ks,int _nShininess)
    {
        kd=_kd;
        ks=_ks;
        nShininess=_nShininess;
    }

    /**
     * Material ctr copy
     * @param material
     */
    public Material(Material material) {
        this(material.kd, material.ks, material.nShininess);
    }

    /**
     * getKd
     * @return
     */
    public double getKd() {
        return kd;
    }

    /**
     * getKs
     * @return
     */
    public double getKs() {
        return ks;
    }

    /**
     * getnShininess
     * @return
     */
    public int getnShininess() {
        return nShininess;
    }
}
