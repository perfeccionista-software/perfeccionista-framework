package io.perfeccionista.framework.measurements;

import com.fasterxml.jackson.databind.JsonNode;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class Dimensions2D {

    private final double width;
    private final double height;
    private Double inaccuracy = null;

    private Dimensions2D(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public static Dimensions2D of(double width, double height) {
        return new Dimensions2D(width, height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public int getWidthAsInt() {
        return Double.valueOf(width).intValue();
    }

    public int getHeightAsInt() {
        return Double.valueOf(height).intValue();
    }

    public Point2D getCenter() {
        return Point2D.of(width/2, height/2);
    }

    /**
     * @param inaccuracy - допустимая погрешность при сравнении размеров
     */
    public Dimensions2D setInaccuracy(double inaccuracy) {
        this.inaccuracy = Math.abs(inaccuracy);
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Dimensions2D that = (Dimensions2D) other;
        if (inaccuracy == null) {
            return (this.width - that.width == 0) && (this.height - that.height == 0);
        }
        double widthDifference = this.width - that.width;
        double heightDifference = this.height - that.height;
        if (Math.abs(widthDifference) > inaccuracy || Math.abs(heightDifference) > inaccuracy) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(width);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (inaccuracy != null ? inaccuracy.hashCode() : 0);
        return result;
    }

    public JsonNode toJson() {
        return createObjectNode()
                .put("width", width)
                .put("height", height)
                .put("inaccuracy", inaccuracy);
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
