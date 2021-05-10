package io.perfeccionista.framework.measurements;

public class Orientation3D {

    private final double x;
    private final double y;
    private final double z;

    private Orientation3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Orientation3D of(double x, double y, double z) {
        return new Orientation3D(x, y, z);
    }

}
