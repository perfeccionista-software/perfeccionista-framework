package io.perfeccionista.framework.measurements;

public class Margins2D {

    private final double screenTop;
    private final double screenRight;
    private final double screenBottom;
    private final double screenLeft;

    private Margins2D(double screenTop, double screenRight, double screenBottom, double screenLeft) {
        this.screenTop = screenTop;
        this.screenRight = screenRight;
        this.screenBottom = screenBottom;
        this.screenLeft = screenLeft;
    }

    public static Margins2D of(double screenTop, double screenRight, double screenBottom, double screenLeft) {
        return new Margins2D(screenTop, screenRight, screenBottom, screenLeft);
    }

}
