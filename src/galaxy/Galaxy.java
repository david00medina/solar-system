package galaxy;

import vehicle.Spaceship;
import galaxy.planet.Planet;
import galaxy.planet.Ring;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;
import processing.sound.SoundFile;

public class Galaxy extends PApplet {
    float angP = .0f;
    float angS = .0f;
    PShape universe;
    PImage universe_mat, sun_mat, mercury_mat, venus_mat, earth_mat, moon_mat, mars_mat, jupiter_mat, saturn_mat, uranus_mat, neptune_mat;
    Planet sun, mercury, venus, earth, moon, mars, jupiter, saturn, uranus, neptune;
    PImage saturn_ring_mat;
    Ring saturn_ring;
    int[] labelRGB;
    SoundFile soundtrack;

    PShape spaceship_model;
    Spaceship spaceship;

    public void settings() {
        size(1200, 1200, P3D);
    }

    public void setup() {
        background(200);

        noStroke();

        soundtrack = new SoundFile(this, "res/sounds/Orbit Beat 130.wav");

        universe = createShape(SPHERE, 1000);
        universe_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_stars_milky_way.jpg");
        universe.setTexture(universe_mat);

        labelRGB = new int[]{150, 150, 150};

        spaceship_model = loadShape("res/Falcon t45 Rescue ship/Falcon t45 Rescue ship flying.obj");
        spaceship = new Spaceship(this, spaceship_model, new PVector(width/2.f,height/2.f,600.f),
                1.5f, .2f, labelRGB);

        sun_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_sun.jpg");
        sun = new Planet(this, "Sun", sun_mat, new PVector(0,0,0), 110f, .0f,
                .0f, .0f, .0f, 2.5f, .0f, .0f, labelRGB);

        mercury_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_mercury.jpg");
        mercury = new Planet(this, "Mercury", mercury_mat, new PVector(-140,0,0),
                9.351f, 140.f, .0f, random(360), .25f,
                .25f, .01f, random(-45,45), labelRGB);

        venus_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_venus_atmosphere.jpg");
        venus = new Planet(this, "Venus", venus_mat, new PVector(-200,0,0),
                15.87f, 200.f, .0f, random(360),  .25f,
                .25f, 177.3f, random(-45,45), labelRGB);

        earth_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_earth_daymap.jpg");
        earth = new Planet(this, "Earth",earth_mat, new PVector(-260, 0,0),
                15.9f, 260.f, .0f, random(360), .25f,
                .25f, 23.26f, random(-45,45), labelRGB);

            moon_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_moon.jpg");
        moon = new Planet(this, "Moon", moon_mat, new PVector(-40,0,0),
                6.25f, 40.f, .0f, random(360),.25f,
                .25f, 1.5f, random(-45,45), labelRGB);

        mars_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_mars.jpg");
        mars = new Planet(this, "Mars", mars_mat, new PVector(-320,0,0),
                15.487f, 320.f, .0f, random(360), .25f,
                .25f, 25.19f, random(-45,45), labelRGB);

        jupiter_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_jupiter.jpg");
        jupiter = new Planet(this, "Jupiter", jupiter_mat, new PVector(-380,0,0),
                30.1f, 380.f, .0f, random(360), .25f,
                .25f, 3.13f, random(-45,45), labelRGB);

        saturn_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_saturn.jpg");
        saturn = new Planet(this, "Saturn", saturn_mat, new PVector(-440,0,0),
                25.07f, 440.f, .0f, random(360), .25f,
                .25f, 26.73f, random(-45,45), labelRGB);

        saturn_ring_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_saturn_ring_alpha.png");
        saturn_ring = new Ring(this, new PVector(0,0,0), saturn.getRadius() + saturn.getRadius() * 1.2f,
                saturn.getRadius() + saturn.getRadius() * .025f, saturn_ring_mat, 200);

        uranus_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_uranus.jpg")             ;
        uranus = new Planet(this, "Uranus", uranus_mat, new PVector(-500,0,0),
                10.92f, 500.f, .0f, random(360), .25f,
                .25f, 97.77f, random(-45,45), labelRGB);

        neptune_mat = loadImage("https://www.solarsystemscope.com/textures/download/2k_neptune.jpg");
        neptune = new Planet(this, "Neptune", neptune_mat, new PVector(-560,0,0),
                10.36f, 560.f, .0f, random(360), .25f,
                .25f, 28.32f, random(-45,45), labelRGB);

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

        soundtrack.play();
    }

    public void draw() {
        spaceship.refresh();

        translate(width/2.f, height/2.f, 0);

        shape(universe);

        sun.refresh();
    }

    public void keyTyped() {
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

    public static void main(String... args) {
        PApplet.main("galaxy.Galaxy");
    }
}
