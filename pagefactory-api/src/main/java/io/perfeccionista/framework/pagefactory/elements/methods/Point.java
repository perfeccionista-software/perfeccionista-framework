package io.perfeccionista.framework.pagefactory.elements.methods;

import com.fasterxml.jackson.databind.JsonNode;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class Point {

    private final double x;
    private final double y;
    private Double inaccuracy = null;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Point of(double x, double y) {
        return new Point(x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * @param inaccuracy - допустимая погрешность при сравнении размеров
     */
    public Point setInaccuracy(double inaccuracy) {
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
        Point that = (Point) other;
        if (inaccuracy == null) {
            return (this.x - that.x == 0) && (this.y - that.y == 0);
        }
        double xDifference = this.x - that.x;
        double yDifference = this.y - that.y;
        if (Math.abs(xDifference) > inaccuracy || Math.abs(yDifference) > inaccuracy) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (inaccuracy != null ? inaccuracy.hashCode() : 0);
        return result;
    }

    public JsonNode toJson() {
        return createObjectNode()
                .put("x", x)
                .put("y", y)
                .put("inaccuracy", inaccuracy);
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
