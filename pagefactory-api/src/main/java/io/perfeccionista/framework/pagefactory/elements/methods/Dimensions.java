package io.perfeccionista.framework.pagefactory.elements.methods;

import com.fasterxml.jackson.databind.JsonNode;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: Здесь или в Dimensions добавить запрос точки центра элемента
public class Dimensions {

    private final double width;
    private final double height;
    private Double inaccuracy = null;

    private Dimensions(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public static Dimensions of(double width, double height) {
        return new Dimensions(width, height);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * @param inaccuracy - допустимая погрешность при сравнении размеров
     */
    public Dimensions setInaccuracy(double inaccuracy) {
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
        Dimensions that = (Dimensions) other;
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
