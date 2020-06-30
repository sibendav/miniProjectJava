package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Intersectable;
import premitives.Color;

public class Scene {
    String _name;
    Color _background;
    AmbientLight _ambientLight;
    Geometries _geometries;
    Camera _camera;
    double _distance;
    public   Scene(String _name1)
    {
        _name=_name1;
        _geometries=new Geometries();
    }

    public double get_distance() {
        return _distance;
    }

    public String get_name() {
        return _name;
    }

    public Color get_background() {
        return _background;
    }

    public Geometries get_geometries() {
        return _geometries;
    }

    public Camera get_camera() {
        return _camera;
    }

    public AmbientLight get_ambientLight() {
        return _ambientLight;
    }

    public void set_background(Color _background) {
        this._background = _background;
    }

    public void set_ambientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }

    public void set_camera(Camera _camera) {
        this._camera = _camera;
    }

    public void set_distance(double _distance) {
        this._distance = _distance;
    }

    public void addGeometries(Intersectable... geometries) {
      for(Intersectable H: geometries)
          _geometries.add(H);
    }
}
