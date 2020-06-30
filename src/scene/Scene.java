package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import premitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class: Sence
 * @author  Simha Ben-David and Tahel Nadav
 */
public class Scene {
    String _name;
    Color _background;
    AmbientLight _ambientLight;
    Geometries _geometries;
    Camera _camera;
    double _distance;
    List<LightSource> _lights;

    /**
     * add lights func
     * @param light LightSource
     */
    public void addLights(LightSource... light) {
        if (_lights == null) {
            _lights = new LinkedList<>();
        }
        for (LightSource i : light) {
            _lights.add(i);
        }

    }
    /**
     * ctr sence
     * @param _name1 string
     */
    public Scene(String _name1)
    {
        _name=_name1;
        _geometries=new Geometries();
    }

    /**
     * get_lights func
     * @return List<LightSource>
     */
    public List<LightSource> get_lights() {
        return _lights;
    }
    /**
     * get_distance func
     * @return double
     */
    public double get_distance() {
        return _distance;
    }

    /**
     * get_name func
     * @return String
     */
    public String get_name() {
        return _name;
    }

    /**
     * get_background func
     * @return Color
     */
    public Color get_background() {
        return _background;
    }

    /**
     * get_geometries func
     * @return Geometries
     */
    public Geometries get_geometries() {
        return _geometries;
    }

    /**
     * get_camera func
     * @return Camera
     */
    public Camera get_camera() {
        return _camera;
    }

    /**
     * get_ambientLight func
     * @return AmbientLight
     */
    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    /**
     * set_background func
     * @param _background Color
     */
    public void set_background(Color _background) {
        this._background = _background;
    }

    /**
     * set_ambientLight func
     * @param _ambientLight AmbientLight
     */
    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    /**
     * set_camera func
     * @param _camera Camera
     */
    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    /**
     * set_distance func
     * @param _distance - new distance to the scene
     */
    public void set_distance(double _distance) {
        this._distance = _distance;
    }

    /**
     * add Geometries function
     * @param geometries to add to the scene
     */
    public void addGeometries(Intersectable... geometries) {
      for(Intersectable H: geometries)
          _geometries.add(H);
    }
}
