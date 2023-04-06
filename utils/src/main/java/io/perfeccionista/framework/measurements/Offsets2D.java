package io.perfeccionista.framework.measurements;

public class Offsets2D {

    private final double x;
    private final double y;

    private Offsets2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Offsets2D of(double x, double y) {
        return new Offsets2D(x, y);
    }

}
