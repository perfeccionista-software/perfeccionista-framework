package io.perfeccionista.framework.pagefactory.elements.methods;

import com.fasterxml.jackson.databind.JsonNode;
import org.jetbrains.annotations.Nullable;

import static io.perfeccionista.framework.utils.JsonUtils.createObjectNode;

// TODO: Здесь или в Dimensions добавить запрос точки центра элемента
public class Location {

    private final Double pageX;
    private final Double pageY;

    private final Double absolutePageX;
    private final Double absolutePageY;

    private Double inaccuracy = null;

    private Location(Double pageX, Double pageY, Double absolutePageX, Double absolutePageY) {
        this.pageX = pageX;
        this.pageY = pageY;
        this.absolutePageX = absolutePageX;
        this.absolutePageY = absolutePageY;
    }

    public static Location of(double pageX, double pageY, double absolutePageX, double absolutePageY) {
        return new Location(pageX, pageY, absolutePageX, absolutePageY);
    }

    public static Location relative(double pageX, double pageY) {
        return new Location(pageX, pageY, null, null);
    }

    public static Location absolute(double absolutePageX, double absolutePageY) {
        return new Location(null, null, absolutePageX, absolutePageY);
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

    public Location setInaccuracy(double inaccuracy) {
        this.inaccuracy = Math.abs(inaccuracy);
        return this;
    }

    public Location offset(double x, double y) {
        Double newPageX = pageX == null ? null : pageX + x;
        Double newPageY = pageY == null ? null : pageY + y;
        Double newAbsolutePageX = absolutePageX == null ? null : absolutePageX + x;
        Double newAbsolutePageY = absolutePageY == null ? null : absolutePageY + y;
        return new Location(newPageX, newPageY, newAbsolutePageX, newAbsolutePageY);
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
            return pageXEquals && pageYEquals && absolutePageXEquals && absolutePageYEquals;
        }
        double pageXDifference = this.pageX == null ? 0 : this.pageX - that.pageX;
        double pageYDifference = this.pageY == null ? 0 : this.pageY - that.pageY;
        double absolutePageXDifference = this.absolutePageX == null ? 0 : this.absolutePageX - that.absolutePageX;
        double absolutePageYDifference = this.absolutePageY == null ? 0 : this.absolutePageY - that.absolutePageY;
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
        temp = Double.doubleToLongBits(pageX == null ? 0 : pageX);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(pageY == null ? 0 : pageY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(absolutePageX == null ? 0 : absolutePageX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(absolutePageY == null ? 0 : absolutePageY);
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
