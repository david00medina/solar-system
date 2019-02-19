package galaxy.utils;

import processing.core.PVector;

public class PVectorUV extends PVector {
    public float u;
    public float v;

    public PVectorUV(float x, float y, float z, float u, float v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
    }
}
