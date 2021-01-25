package io.perfeccionista.framework.measurements;

public class Rotation3D {

    private final double x;
    private final double y;
    private final double z;

    private Rotation3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Rotation3D of(double x, double y, double z) {
        return new Rotation3D(x, y, z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

}
