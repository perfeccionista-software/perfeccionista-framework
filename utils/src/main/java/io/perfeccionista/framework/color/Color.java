package io.perfeccionista.framework.color;

import com.fasterxml.jackson.databind.JsonNode;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

/**
 * Интерфейс, предназначенный для определения цвета, которым будет выделен аннотированный метод
 */
public class Color {

    private final int r;
    private final int g;
    private final int b;
    private final double alpha;

    private Color(int r, int g, int b, double alpha) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.alpha = alpha;
    }

    public static Color of(int r, int g, int b, double alpha) {
        return new Color(r, g, b, alpha);
    }

    public int getR() {
        return this.r;
    }

    public int getG() {
        return this.g;
    }

    public int getB() {
        return this.b;
    }

    public double getAlpha() {
        return this.alpha;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Color that = (Color) other;
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
