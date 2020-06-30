package elements;

import premitives.Color;
/**
 * The Class: Light
 * @author  Simha Ben-David & Tahel Nadav
 */
abstract class  Light {
    protected Color _intensity;
    /**
     * Light
     * @param _intensity
     */
    public Light(Color _intensity) {
        this._intensity = _intensity;
    }

    /**
     * get_intensity function
     * @return color
     */
    public Color get_intensity() {
        return _intensity;
    }



}
