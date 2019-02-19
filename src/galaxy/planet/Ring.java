package galaxy.planet;

import galaxy.utils.PVectorUV;
import processing.core.*;

import java.util.ArrayList;
import java.util.List;

public class Ring {
    private final int NUM_PARTS;
    private final PImage MATERIAL;

    private PShape ring;

    private PApplet parent;
    private List<PVectorUV> outerRing;
    private List<PVectorUV> innerRing;

    private PVector pos;
    private float outerRadius;
    private float innerRadius;

    public Ring(PApplet parent, PVector pos, float outerRadius, float innerRadius, PImage material, int numParts) {
        this.NUM_PARTS = numParts;

        this.parent = parent;
        this.pos = pos;
        this.outerRadius = outerRadius;
        this.innerRadius = innerRadius;
        this.MATERIAL = material;

        this.outerRing = new ArrayList<>();
        this.innerRing = new ArrayList<>();

        renderRing();
    }

    public void refresh() {
        ring = parent.createShape();

        ring.beginShape(PConstants.QUAD_STRIP);
        ring.noStroke();
        ring.texture(MATERIAL);

        setUpVertices();

        ring.endShape(PConstants.CLOSE);
        parent.shape(ring);
    }

    private void setUpVertices() {
        for (int i = 0; i < NUM_PARTS; i++) {
            PVectorUV outer1 = outerRing.get(i % NUM_PARTS);
            PVectorUV inner1 = innerRing.get(i % NUM_PARTS);
            assignVerticesSet(outer1, inner1);

            PVectorUV outer2 = outerRing.get((i+1) % NUM_PARTS);
            PVectorUV inner2 = innerRing.get((i+1) % NUM_PARTS);
            assignVerticesSet(outer2, inner2);
        }
    }

    private void assignVerticesSet(PVectorUV outer, PVectorUV inner) {
        ring.vertex(outer.x, outer.y, outer.z, outer.u, outer.v);
        ring.vertex(inner.x, inner.y, inner.z, inner.u, inner.v);
    }

    private void renderRing() {
        float angleStep = PApplet.radians(360.f/NUM_PARTS);

        float angle = .0f;

        for (int i = 0; i <= NUM_PARTS; i++) {
            angle = (i * angleStep) % PApplet.radians(360);
            if ((i % 2) == 0) {
                outerRing.add(buildRingPerimeter(angle, outerRadius, true, false));
                innerRing.add(buildRingPerimeter(angle, innerRadius, false, false));
            } else {
                outerRing.add(buildRingPerimeter(angle, outerRadius, true, true));
                innerRing.add(buildRingPerimeter(angle, innerRadius, false, true));
            }
        }
    }

    private PVectorUV buildRingPerimeter(float angle, float radius, boolean isOuter, boolean isNextPair) {
        float x = radius * PApplet.cos(angle) - radius * PApplet.sin(angle);
        float y = 0;
        float z = radius * PApplet.sin(angle) + radius * PApplet.cos(angle);
        if(!isNextPair)
            if(isOuter)
                return new PVectorUV(x, y, z, 0, MATERIAL.height);
            else
                return new PVectorUV(x, y, z, MATERIAL.width, MATERIAL.height);
        else
            if(isOuter)
                return new PVectorUV(x, y, z, 0, 0);
            else
                return new PVectorUV(x, y, z, MATERIAL.width, 0);
    }
}
