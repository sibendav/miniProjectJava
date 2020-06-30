package elements;

import premitives.Color;

public class AmbientLight {
    Color _intensity;
    public AmbientLight(Color _intensity, double ka) {
        this._intensity = _intensity.scale(ka);
    }
    public Color GetIntensity()
    {
        return _intensity;
    }
}
