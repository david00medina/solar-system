package camera;

import processing.core.PApplet;
import processing.core.PVector;

public class Camera {
    private PApplet parent;
    private PVector pos;
    private PVector center;
    private PVector rotation;

    public Camera(PApplet parent, PVector pos, PVector center, PVector rotation) {
        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.rotation = rotation;
    }

    public Camera(PApplet parent, PVector pos, PVector center) {
        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.rotation = new PVector(0,1,0);
    }

    public Camera(PApplet parent) {
        this.parent = parent;
        reset();
    }

    public void refresh() {
        parent.camera(pos.x, pos.y, pos.z, center.x, center.y, center.z, rotation.x, rotation.y, rotation.z);
    }

    public void setPosition(float x, float y, float z) {
        pos.x = x;
        pos.y = y;
        pos.z = z;
    }

    public void setCenter(float x, float y, float z) {
        center.x = x;
        center.y = y;
        center.z = z;
    }

    public void setRotation(float x, float y, float z) {
        rotation.x = x;
        rotation.y = y;
        rotation.z = z;
    }

    public void reset() {
        this.pos = new PVector(parent.width/2.f, parent.height/2.f, (parent.height/2.f) / PApplet.tan(PApplet.PI*30.f / 180.f));
        this.center = new PVector(parent.width/2.f, parent.height/2.f, .0f);
        this.rotation = new PVector(.0f,1.f,.0f);
    }
}
