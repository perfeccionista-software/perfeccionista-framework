package io.perfeccionista.framework.plugin;

public class WebElementColor implements Color {

    private final int r;
    private final int g;
    private final int b;
    private final double alpha;

    public WebElementColor(int r, int g, int b, double alpha) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
    }

    @Override
    public int getR() {
        return this.r;
    }

    @Override
    public int getG() {
        return this.g;
    }

    @Override
    public int getB() {
        return this.b;
    }

    @Override
    public double getAlpha() {
        return this.alpha;
    }

}
