package io.perfeccionista.framework.pagefactory.elements.methods;

import com.fasterxml.jackson.databind.JsonNode;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: Здесь или в Dimensions добавить запрос точки центра элемента
public class Location {

    private final double pageX;
    private final double pageY;

    private final double absolutePageX;
    private final double absolutePageY;

    private Double inaccuracy = null;

    private Location(double pageX, double pageY, double absolutePageX, double absolutePageY) {
        this.pageX = pageX;
        this.pageY = pageY;
        this.absolutePageX = absolutePageX;
        this.absolutePageY = absolutePageY;
    }

    public static Location of(double pageX, double pageY, double absolutePageX, double absolutePageY) {
        return new Location(pageX, pageY, absolutePageX, absolutePageY);
    }

    public double getPageX() {
        return pageX;
    }

    public double getPageY() {
        return pageY;
    }

    public double getAbsolutePageX() {
        return absolutePageX;
    }

    public double getAbsolutePageY() {
        return absolutePageY;
    }

    public Location setInaccuracy(double inaccuracy) {
        this.inaccuracy = Math.abs(inaccuracy);
        return this;
    }

    public Location offset(double x, double y) {
        return new Location(pageX + x, pageY + y, absolutePageX + x, absolutePageY + y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Location that = (Location) other;
        if (inaccuracy == null) {
            return (this.pageX - that.pageX == 0)
                    && (this.pageY - that.pageY == 0)
                    && (this.absolutePageX - that.absolutePageX == 0)
                    && (this.absolutePageY - that.absolutePageY == 0);
        }
        double pageXDifference = this.pageX - that.pageX;
        double pageYDifference = this.pageY - that.pageY;
        double absolutePageXDifference = this.absolutePageX - that.absolutePageX;
        double absolutePageYDifference = this.absolutePageY - that.absolutePageY;
        if (Math.abs(pageXDifference) > inaccuracy
                || Math.abs(pageYDifference) > inaccuracy
                || Math.abs(absolutePageXDifference) > inaccuracy
                || Math.abs(absolutePageYDifference) > inaccuracy) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(pageX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pageY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(absolutePageX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(absolutePageY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (inaccuracy != null ? inaccuracy.hashCode() : 0);
        return result;
    }

    public JsonNode toJson() {
        return createObjectNode()
                .put("pageX", pageX)
                .put("pageY", pageY)
                .put("absolutePageX", absolutePageX)
                .put("absolutePageY", absolutePageY)
                .put("inaccuracy", inaccuracy);
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
