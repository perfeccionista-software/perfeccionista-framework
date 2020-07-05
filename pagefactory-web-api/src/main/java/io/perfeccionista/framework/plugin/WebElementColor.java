package io.perfeccionista.framework.plugin;

import com.fasterxml.jackson.databind.JsonNode;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class WebElementColor implements Color {

    private final int r;
    private final int g;
    private final int b;
    private final double alpha;

    private WebElementColor(int r, int g, int b, double alpha) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
    }

    public static WebElementColor of(int r, int g, int b, double alpha) {
        return new WebElementColor(r, g, b, alpha);
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

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        WebElementColor that = (WebElementColor) other;
        if (r != that.r) {
            return false;
        }
        if (g != that.g) {
            return false;
        }
        if (b != that.b) {
            return false;
        }
        return Double.compare(that.alpha, alpha) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = r;
        result = 31 * result + g;
        result = 31 * result + b;
        temp = Double.doubleToLongBits(alpha);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public JsonNode toJson() {
        return createObjectNode()
                .put("r", r)
                .put("g", g)
                .put("b", b)
                .put("alpha", alpha);
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
