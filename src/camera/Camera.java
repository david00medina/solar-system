package camera;

import galaxy.utils.PVectorUV;
import processing.core.PApplet;
import processing.core.PVector;

public class Camera {
    public final float CAMERAZ;

    private PApplet parent;
    private PVector pos;
    private PVector center;
    private PVector rotation;
    private PVectorUV perspective;

    public Camera(PApplet parent, PVector pos, PVector center, PVector rotation, PVectorUV perspective) {
        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.rotation = rotation;
        this.perspective = perspective;

        CAMERAZ = (parent.height/2.f)/PApplet.tan(PApplet.PI * 60.f / 360.f);
    }

    public Camera(PApplet parent, PVector pos, PVector center, PVectorUV perspective) {
        this.parent = parent;
        this.pos = pos;
        this.center = center;
        this.rotation = new PVector(0,1,0);
        this.perspective = perspective;

        CAMERAZ = (parent.height/2.f)/PApplet.tan(PApplet.PI * 60.f / 360.f);
    }

    public Camera(PApplet parent) {
        this.parent = parent;
        reset();

        CAMERAZ = (parent.height/2.f)/PApplet.tan(PApplet.PI * 60.f / 360.f);
    }

    public void refresh() {
        parent.camera(pos.x, pos.y, pos.z, center.x, center.y, center.z, rotation.x, rotation.y, rotation.z);
        parent.perspective(perspective.x, perspective.y, perspective.z, perspective.u);
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

    public void setPerspective(float fovy, int aspect, float zNear, float zFar) {
        perspective.x = fovy;
        perspective.y = aspect;
        perspective.z = zNear;
        perspective.u = zFar;
    }

    public void reset() {
        this.pos = new PVector(parent.width/2.f,
                parent.height/2.f,
                (parent.height/2.f) / PApplet.tan(PApplet.PI*30.f / 180.f));

        this.center = new PVector(parent.width/2.f,
                parent.height/2.f,
                .0f);

        this.rotation = new PVector(.0f,1.f,.0f);

        this.perspective = new PVectorUV(PApplet.PI/3.f,
                parent.width/parent.height,
                CAMERAZ/10.f, CAMERAZ * 10.f);
    }
}
