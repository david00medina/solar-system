package galaxy;

import camera.Camera;
import vehicle.Spaceship;
import galaxy.planet.Planet;
import galaxy.planet.Ring;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;
import processing.sound.SoundFile;

public class Galaxy extends PApplet {
    private PShape universe;
    private Planet sun;
    private Spaceship spaceship;
    private Camera cam;
    private int [] labelRGB;

    public void settings() {
        size(1200, 1200, P3D);
    }

    public void setup() {
        initialization();

        generateUniverse();

        generateSolarSystem();

        createSpaceship();
    }

    public void draw() {
        pollKeyboard();

        spaceship.refresh();

        translate(width/2.f, height/2.f, 0);

        shape(universe);

        sun.refresh();
    }

    private void initialization() {
        background(200);

        noStroke();

        cam = new Camera(this);

        labelRGB = new int[]{150, 150, 150};

        SoundFile soundtrack = new SoundFile(this, "res/sounds/Orbit Beat 130.wav");

        soundtrack.loop();
    }

    private void generateUniverse() {
        universe = createShape(SPHERE, 1000);
        PImage universe_mat = loadImage("res/planets-texture/2k_stars_milky_way.jpg");
        universe.setTexture(universe_mat);
    }

    private void generateSolarSystem() {
        PImage sun_mat = loadImage("res/planets-texture/2k_sun.jpg");
        sun = new Planet(this, "Sun", sun_mat, new PVector(0,0,0), 110f, .0f,
                .0f, .0f, .0f, 2.5f, .0f, .0f, labelRGB);

        PImage mercury_mat = loadImage("res/planets-texture/2k_mercury.jpg");
        Planet mercury = new Planet(this, "Mercury", mercury_mat, new PVector(-140, 0, 0),
                9.351f, 140.f, .0f, random(360), .25f,
                .25f, .01f, random(-45, 45), labelRGB);

        PImage venus_mat = loadImage("res/planets-texture/2k_venus_atmosphere.jpg");
        Planet venus = new Planet(this, "Venus", venus_mat, new PVector(-200, 0, 0),
                15.87f, 200.f, .0f, random(360), .25f,
                .25f, 177.3f, random(-45, 45), labelRGB);

        PImage earth_mat = loadImage("res/planets-texture/2k_earth_daymap.jpg");
        Planet earth = new Planet(this, "Earth", earth_mat, new PVector(-260, 0, 0),
                15.9f, 260.f, .0f, random(360), .25f,
                .25f, 23.26f, random(-45, 45), labelRGB);

        PImage moon_mat = loadImage("res/planets-texture/2k_moon.jpg");
        Planet moon = new Planet(this, "Moon", moon_mat, new PVector(-40, 0, 0),
                6.25f, 40.f, .0f, random(360), .25f,
                .25f, 1.5f, random(-45, 45), labelRGB);

        PImage mars_mat = loadImage("res/planets-texture/2k_mars.jpg");
        Planet mars = new Planet(this, "Mars", mars_mat, new PVector(-320, 0, 0),
                15.487f, 320.f, .0f, random(360), .25f,
                .25f, 25.19f, random(-45, 45), labelRGB);

        PImage jupiter_mat = loadImage("res/planets-texture/2k_jupiter.jpg");
        Planet jupiter = new Planet(this, "Jupiter", jupiter_mat, new PVector(-380, 0, 0),
                30.1f, 380.f, .0f, random(360), .25f,
                .25f, 3.13f, random(-45, 45), labelRGB);

        PImage saturn_mat = loadImage("res/planets-texture/2k_saturn.jpg");
        Planet saturn = new Planet(this, "Saturn", saturn_mat, new PVector(-440, 0, 0),
                25.07f, 440.f, .0f, random(360), .25f,
                .25f, 26.73f, random(-45, 45), labelRGB);

        PImage saturn_ring_mat = loadImage("res/planets-texture/2k_saturn_ring_alpha.png");
        Ring saturn_ring = new Ring(this, new PVector(0, 0, 0), saturn.getRadius() + saturn.getRadius() * 1.2f,
                saturn.getRadius() + saturn.getRadius() * .025f, saturn_ring_mat, 200);

        PImage uranus_mat = loadImage("res/planets-texture/2k_uranus.jpg");
        Planet uranus = new Planet(this, "Uranus", uranus_mat, new PVector(-500, 0, 0),
                10.92f, 500.f, .0f, random(360), .25f,
                .25f, 97.77f, random(-45, 45), labelRGB);

        PImage neptune_mat = loadImage("res/planets-texture/2k_neptune.jpg");
        Planet neptune = new Planet(this, "Neptune", neptune_mat, new PVector(-560, 0, 0),
                10.36f, 560.f, .0f, random(360), .25f,
                .25f, 28.32f, random(-45, 45), labelRGB);

        earth.addSatellite(moon);

        saturn.setRing(saturn_ring);

        sun.addSatellite(mercury);
        sun.addSatellite(venus);
        sun.addSatellite(earth);
        sun.addSatellite(mars);
        sun.addSatellite(jupiter);
        sun.addSatellite(saturn);
        sun.addSatellite(uranus);
        sun.addSatellite(neptune);
    }

    private void createSpaceship() {
        PShape spaceship_model = loadShape("res/Falcon t45 Rescue ship/Falcon t45 Rescue ship flying.obj");
        spaceship = new Spaceship(this, spaceship_model, new PVector(width/2.f,height/2.f,600.f),
                1.5f, .2f, labelRGB, cam);
    }

    private void pollKeyboard() {
        if (key == '+') {
            spaceship.updateSpeed(spaceship.POWER_UP);
        } else if (key == '-') {
            spaceship.updateSpeed(spaceship.POWER_DOWN);
        } else if (key == 's') {
            spaceship.updatePitch(spaceship.UP);
        } else if (key == 'w') {
            spaceship.updatePitch(spaceship.DOWN);
        } else if (key == 'a') {
            spaceship.updateRoll(spaceship.LEFT);
        } else if (key == 'd') {
            spaceship.updateRoll(spaceship.RIGHT);
        }
    }

    public void keyReleased() {
        key = '\0';
    }

    public static void main(String... args) {
        PApplet.main("galaxy.Galaxy");
    }
}
