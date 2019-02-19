package vehicle;

import camera.Camera;
import processing.core.*;

public class Spaceship {
    private final static float POS_X_SPAN = 5.f;
    private final static float POS_Y_SPAN = -50.f;
    private final static float POS_Z_SPAN = 200.f;
    private final static float CENTER_DISTACE = -200.f;
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


    public Spaceship(PApplet parent, PShape spaceship, PVector pos, float speed, float scale, int[] labelRGB) {
        this.parent = parent;
        this.spaceship = spaceship;
        this.pos = pos;
        this.speed = speed;
        this.cam = new Camera(parent);

        headingX = PApplet.cos(-90);
        headingY = PApplet.cos(90);
        headingZ = PApplet.cos(180);

        pitch = .0f;
        roll = .0f;

        intro = 0;
        this.labelRGB = labelRGB;

        this.spaceship.scale(scale);
        this.spaceship.rotateX(PApplet.radians(180));
        this.spaceship.rotateY(PApplet.radians(3));
        setupCameraView();
    }

    public void refresh() {
        moveForward();

        cam.setPosition(pos.x + POS_X_SPAN, pos.y + POS_Y_SPAN, pos.z + POS_Z_SPAN);
        cam.setCenter(pos.x, pos.y ,pos.z + CENTER_DISTACE);
        cam.refresh();

        parent.pushMatrix();
        parent.translate(pos.x, pos.y, pos.z);

        intro();

        parent.rotateX(pitch * ROTATION_ANIMATION);
        parent.rotateZ(roll * ROTATION_ANIMATION);
        parent.shape(spaceship);
        parent.popMatrix();
    }

    private void setupCameraView() {
        cam.setPosition(pos.x + POS_X_SPAN, pos.y + POS_Y_SPAN, pos.z + POS_Z_SPAN);
        cam.setCenter(pos.x, pos.y ,pos.z + CENTER_DISTACE);
        cam.setRotation(.0f, 1.f, 0.f);
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
        if(intro < 40) parent.text("PULSE A, W, S o D para moverse", -150,-150,-200);
        else if (intro > 40 && intro < 80) parent.text("PULSE + o - para acelerar y desacelerar",-150,-150,-200);

        if (intro < 80) intro++;
    }
}
