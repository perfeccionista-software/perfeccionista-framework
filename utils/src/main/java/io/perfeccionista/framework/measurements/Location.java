package io.perfeccionista.framework.measurements;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

public class Location {

    private final Double pageX;
    private final Double pageY;

    private final Double absolutePageX;
    private final Double absolutePageY;

    private final Double elementCenterX;
    private final Double elementCenterY;

    private Double inaccuracy = null;

    private Location(Double pageX,
                     Double pageY,
                     Double absolutePageX,
                     Double absolutePageY,
                     Double elementCenterX,
                     Double elementCenterY) {
        this.pageX = pageX;
        this.pageY = pageY;
        this.absolutePageX = absolutePageX;
        this.absolutePageY = absolutePageY;
        this.elementCenterX = elementCenterX;
        this.elementCenterY = elementCenterY;

    }

    public static Location of(double pageX,
                              double pageY,
                              double absolutePageX,
                              double absolutePageY,
                              double elementCenterX,
                              double elementCenterY) {
        return new Location(pageX, pageY, absolutePageX, absolutePageY, elementCenterX, elementCenterY);
    }

    public static Location relative(double pageX, double pageY) {
        return new Location(pageX, pageY, null, null, null, null);
    }

    public static Location absolute(double absolutePageX, double absolutePageY) {
        return new Location(null, null, absolutePageX, absolutePageY, null, null);
    }

    public static Location center(double elementCenterX, double elementCenterY) {
        return new Location(null, null, null, null, elementCenterX, elementCenterY);
    }

    public @Nullable Double getPageX() {
        return pageX;
    }

    public @Nullable Double getPageY() {
        return pageY;
    }

    public @Nullable Double getAbsolutePageX() {
        return absolutePageX;
    }

    public @Nullable Double getAbsolutePageY() {
        return absolutePageY;
    }

    public @Nullable Point getCenter() {
        if (elementCenterX != null && elementCenterY != null) {
            return Point.of(elementCenterX, elementCenterY);
        }
        return null;
    }

    public Location setInaccuracy(double inaccuracy) {
        this.inaccuracy = Math.abs(inaccuracy);
        return this;
    }

    public Location offset(double x, double y) {
        Double newPageX = pageX == null ? null : pageX + x;
        Double newPageY = pageY == null ? null : pageY + y;
        Double newAbsolutePageX = absolutePageX == null ? null : absolutePageX + x;
        Double newAbsolutePageY = absolutePageY == null ? null : absolutePageY + y;
        Double newElementCenterX = elementCenterX == null ? null : elementCenterX + x;
        Double newElementCenterY = elementCenterY == null ? null : elementCenterY + y;
        return new Location(newPageX, newPageY, newAbsolutePageX, newAbsolutePageY, newElementCenterX, newElementCenterY);
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
            boolean pageXEquals = this.pageX == null || this.pageX - that.pageX == 0;
            boolean pageYEquals = this.pageY == null || this.pageY - that.pageY == 0;
            boolean absolutePageXEquals = this.absolutePageX == null || this.absolutePageX - that.absolutePageX == 0;
            boolean absolutePageYEquals = this.absolutePageY == null || this.absolutePageY - that.absolutePageY == 0;
            boolean elementCenterXEquals = this.elementCenterX == null || this.elementCenterX - that.elementCenterX == 0;
            boolean elementCenterYEquals = this.elementCenterY == null || this.elementCenterY - that.elementCenterY == 0;
            return pageXEquals && pageYEquals && absolutePageXEquals && absolutePageYEquals && elementCenterXEquals && elementCenterYEquals;
        }
        double pageXDifference = this.pageX == null ? 0 : this.pageX - that.pageX;
        double pageYDifference = this.pageY == null ? 0 : this.pageY - that.pageY;
        double absolutePageXDifference = this.absolutePageX == null ? 0 : this.absolutePageX - that.absolutePageX;
        double absolutePageYDifference = this.absolutePageY == null ? 0 : this.absolutePageY - that.absolutePageY;
        double elementCenterXDifference = this.elementCenterX == null ? 0 : this.elementCenterX - that.elementCenterX;
        double elementCenterYDifference = this.elementCenterY == null ? 0 : this.elementCenterY - that.elementCenterY;
        if (Math.abs(pageXDifference) > inaccuracy
                || Math.abs(pageYDifference) > inaccuracy
                || Math.abs(absolutePageXDifference) > inaccuracy
                || Math.abs(absolutePageYDifference) > inaccuracy
                || Math.abs(elementCenterXDifference) > inaccuracy
                || Math.abs(elementCenterYDifference) > inaccuracy) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(pageX == null ? 0 : pageX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pageY == null ? 0 : pageY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(absolutePageX == null ? 0 : absolutePageX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(absolutePageY == null ? 0 : absolutePageY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(elementCenterX == null ? 0 : elementCenterX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(elementCenterY == null ? 0 : elementCenterY);
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
                .put("elementCenterX", elementCenterX)
                .put("elementCenterY", elementCenterY)
                .put("inaccuracy", inaccuracy);
    }

    @Override
    public String toString() {
        return toJson().toPrettyString();
    }

}
