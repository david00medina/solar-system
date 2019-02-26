package galaxy.utils;

import processing.core.PVector;

public class PVectorUV extends PVector {
    public float u;
    public float v;

    public PVectorUV(float x, float y, float z, float u, float v) {
        super(x, y, z);
        this.u = u;
        this.v = v;
    }

    public PVectorUV(float x, float y, float z, float u) {
        super(x, y, z);
        this.u = u;
    }
}
