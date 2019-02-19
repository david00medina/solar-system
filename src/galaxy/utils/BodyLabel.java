package galaxy.utils;

import processing.core.PApplet;
import processing.core.PMatrix;

public class BodyLabel extends PApplet {
    private final String LABEL_TXT;

    private final PApplet parent;

    private PMatrix pos;
    private float lineLenght;
    private int lineWeight;
    private int[] rgb;

    public BodyLabel(PApplet parent, String label, float lineLength, int lineWeight, int[] rgb) {
        this.LABEL_TXT = label;

        this.parent = parent;
        this.pos = pos;
        this.lineLenght = lineLength;
        this.lineWeight = lineWeight;
        this.rgb = rgb;
    }

    public void refresh() {
        parent.pushMatrix();

        parent.setMatrix(pos);
        parent.stroke(rgb[0], rgb[1], rgb[2]);
        parent.strokeWeight(lineWeight);
        parent.fill(rgb[0], rgb[1], rgb[2]);
        parent.line(0,0,0,0,-lineLenght,0);
        parent.text(LABEL_TXT,5,-lineLenght+10,0);

        parent.popMatrix();
    }
}
