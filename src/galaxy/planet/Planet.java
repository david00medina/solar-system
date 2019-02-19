package galaxy.planet;


import galaxy.utils.BodyLabel;
import processing.core.*;

import java.util.ArrayList;
import java.util.List;

public class Planet {
    private final float TRANSLATION_SPEED;
    private final float ROTATION_SPEED;
    private final float AXIAL_TILT;
    private final float ORBIT_TILT;
    private final PImage MATERIAL;

    private final String PLANET_LABEL;

    private PApplet parent;
    private PShape planet;
    private Ring ring;
    private List<Planet> moons;
    private int[] labelRGB;

    private PVector pos;
    private float radius;
    private float orbitRadius;
    private float rotationDegree;
    private float translationDegree;

    public Planet(PApplet parent, String name, PImage material, PVector pos, float radius, float orbitRadius, float rotationDegree,
                  float translationDegree, float translationSpeed, float rotationSpeed, float axialTilt, float orbitTilt, int[] labelRGB) {
        TRANSLATION_SPEED = PApplet.radians(translationSpeed);
        ROTATION_SPEED = PApplet.radians(rotationSpeed);
        AXIAL_TILT = PApplet.radians(axialTilt);
        ORBIT_TILT = PApplet.radians(orbitTilt);

        PLANET_LABEL = name;

        this.pos = pos;
        this.radius = radius;
        this.orbitRadius = orbitRadius;
        this.MATERIAL = material;
        this.rotationDegree = PApplet.radians(rotationDegree);
        this.translationDegree = PApplet.radians(translationDegree);
        this.parent = parent;

        moons = new ArrayList<>();
        planet = parent.createShape(PConstants.SPHERE, radius);
        planet.setTexture(material);
        this.labelRGB = labelRGB;
    }

    public void addSatellite(Planet planet) {
        moons.add(planet);
    }

    public List<Planet> getSatellites() {
        return moons;
    }

    public float getRadius() {
        return radius;
    }

    public void refresh() {
        parent.pushMatrix();

        parent.rotateZ(ORBIT_TILT);
        parent.rotateY(translationDegree);

        translationDegree = (translationDegree + TRANSLATION_SPEED) % PApplet.radians(360);


        parent.translate(orbitRadius, 0, 0);


        parent.pushMatrix();
        parent.rotateY(rotationDegree);
        rotationDegree = (rotationDegree + ROTATION_SPEED) % PApplet.radians(360);

        parent.rotateZ(AXIAL_TILT);

        if(hasRing()) ring.refresh();

        parent.shape(planet);

        if (hasRing()) ring.refresh();

        parent.popMatrix();


        if (getSatellites() != null && getSatellites().size() > 0) {
            for (Planet satellite : getSatellites()) {
                parent.pushMatrix();
                parent.rotateY(-translationDegree);
                parent.rotateZ(-ORBIT_TILT);
                satellite.refresh();
                parent.popMatrix();
            }
        }
        label();
        parent.popMatrix();
    }

    private void label() {
        parent.rotateY(-translationDegree);
        parent.rotateZ(-ORBIT_TILT);

        BodyLabel bl = new BodyLabel(parent, PLANET_LABEL, radius + 100, 1, labelRGB);
        bl.refresh();
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    private boolean hasRing() {
        if(ring != null)
            return true;
        else
            return false;
    }
}
