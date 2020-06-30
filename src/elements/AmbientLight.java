package elements;

import premitives.Color;
/**
 * The Class: AmbientLight
 * @author  Simha Ben-David and Tahel Nadav
 */
public class AmbientLight {
    Color _intensity;

    /**
     * crt AmbientLight
     * @param _intensity color
     * @param ka value double
     */
    public AmbientLight(Color _intensity, double ka) {
        this._intensity = _intensity.scale(ka);
    }

    /**
     * GetIntensity function
     * @return color
     */
    public Color get_intensity(){
        return _intensity;
    }
}
