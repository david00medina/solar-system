package vehicle;

import camera.Camera;
import processing.core.*;

public class Spaceship {
    private final static float POS_X_SPAN = 10.f;
    private final static float POS_Y_SPAN = -40.f;
    private final static float POS_Z_SPAN = 130.f;
    private final static float CENTER_DISTANCE = -500.f;
    private final static float MAX_SPEED = 10.f;
    private final static float SPEED_STEP = .25f;
    private final static float PITCH_STEP = PApplet.radians(.5f);
    private final static float ROLL_STEP = PApplet.radians(1.f);
    private final static float ROTATION_ANIMATION = PApplet.radians(200.f);

    public final static float UP = -1.f;
    public final static float DOWN = 1.f;
    public final static float LEFT = -1.f;
    public final static float RIGHT = 1.f;
    public final static float POWER_DOWN = -1.f;
    public final static float POWER_UP = 1.f;


    private PApplet parent;
    private PShape spaceship;
    private PVector pos;
    private float speed;
    private Camera cam;

    private float headingX;
    private float headingY;
    private float headingZ;

    private float pitch;
    private float roll;

    private int intro;
    private int[] labelRGB;


    public Spaceship(PApplet parent, PShape spaceship, PVector pos, float speed, float scale, int[] labelRGB, Camera cam) {
        this.parent = parent;
        this.spaceship = spaceship;
        this.pos = pos;
        this.speed = speed;
        this.cam = cam;

        headingX = PApplet.cos(-90);
        headingY = PApplet.cos(90);
        headingZ = PApplet.cos(180);

        pitch = .0f;
        roll = .0f;

        intro = 0;
        this.labelRGB = labelRGB;

        this.spaceship.scale(scale);
        this.spaceship.rotateX(PApplet.radians(180));
        setupCameraView();
    }

    public void refresh() {
        moveForward();

        updateCamera();

        parent.pushMatrix();
        parent.translate(pos.x + POS_X_SPAN, pos.y, pos.z);

        parent.text("VELOCIDAD: " + speed, 50, 100, -200);
        intro();

        parent.rotateX(pitch * ROTATION_ANIMATION);
        parent.rotateZ(roll * ROTATION_ANIMATION);
        parent.shape(spaceship);
        parent.popMatrix();
    }

    private void updateCamera() {
        cam.setPosition(pos.x, pos.y + POS_Y_SPAN, pos.z + POS_Z_SPAN);
        cam.setCenter(pos.x, pos.y ,pos.z + CENTER_DISTANCE);
        cam.setPerspective(PApplet.PI/3.f,
                parent.width/parent.height,
                cam.CAMERAZ/30.f,
                cam.CAMERAZ * 30.f);
        cam.refresh();
    }

    private void setupCameraView() {
        cam.setPosition(pos.x, pos.y + POS_Y_SPAN, pos.z /*+ POS_Z_SPAN*/);
        cam.setCenter(pos.x, pos.y ,pos.z + CENTER_DISTANCE);
        cam.setRotation(.0f, 1f, 0.f);
        cam.refresh();
    }

    private void moveForward() {
        pos.x += speed * headingX;
        pos.y += speed * headingY;
        pos.z += speed * headingZ;
    }

    public void updateSpeed(float speedDir) {
        this.speed += speedDir * SPEED_STEP % MAX_SPEED;
    }

    public void updatePitch(float pitchDir) {
        pitch += pitchDir * PITCH_STEP % PApplet.radians(360);
        headingY += pitchDir * PApplet.cos(pitch);
    }

    public void updateRoll(float rollDir) {
        roll += rollDir * ROLL_STEP % PApplet.radians(360);
        headingX += rollDir * PApplet.cos(roll);
    }

    private void intro() {
        parent.stroke(labelRGB[0], labelRGB[1], labelRGB[2]);
        if(0 < intro && intro < 40) parent.text("PULSE A, W, S o D para moverse", -150,-150,-200);
        else if (intro > 40 && intro < 80) parent.text("PULSE + o - para acelerar y desacelerar",-150,-150,-200);

        if (intro < 80) intro++;
    }
}
