package io.perfeccionista.framework.pagefactory.elements;

import io.perfeccionista.framework.measurements.Dimensions2D;
import io.perfeccionista.framework.measurements.Offsets2D;
import io.perfeccionista.framework.measurements.Point2D;

public class ElementBounds {

    private final double screenLeft;
    private final double screenTop;

    private final double width;
    private final double height;

    private final double centerX;
    private final double centerY;

    private Double absoluteLeft;
    private Double absoluteTop;

    private Double inaccuracy = null;

    private ElementBounds(double width, double height,double screenLeft, double screenTop, double centerX, double centerY) {
        this.width = width;
        this.height = height;
        this.screenTop = screenTop;
        this.screenLeft = screenLeft;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public static ElementBounds of(double width, double height, double screenLeft, double screenTop) {
        return new ElementBounds(width, height, screenLeft, screenTop, screenLeft + width / 2, screenTop + height / 2);
    }

    public static ElementBounds of(double width, double height,
                                   double screenLeft, double screenTop,
                                   double centerX, double centerY) {
        return new ElementBounds(width, height, screenLeft, screenTop, centerX, centerY);
    }

    public static ElementBounds of(double width, double height,
                                   double screenLeft, double screenTop,
                                   double absoluteLeft, double absoluteTop,
                                   double centerX, double centerY) {
        return new ElementBounds(width, height, screenLeft, screenTop, centerX, centerY)
                .setAbsoluteBounds(absoluteLeft, absoluteTop);
    }

    public ElementBounds setAbsoluteBounds(double absoluteLeft, double absoluteTop) {
        this.absoluteLeft = absoluteLeft;
        this.absoluteTop = absoluteTop;
        return this;
    }

    /**
     * @param inaccuracy - допустимая погрешность при сравнении размеров
     */
    public ElementBounds setInaccuracy(double inaccuracy) {
        this.inaccuracy = Math.abs(inaccuracy);
        return this;
    }

    public Offsets2D getAbsoluteOffsets(double absoluteWidth, double absoluteHeight) {
        return null;
    }

    public Offsets2D getScreenOffsets(double screenWidth, double screenHeight) {
        return null;
    }

    public Dimensions2D getDimensions() {
        return Dimensions2D.of(width, height);
    }

    public Point2D getScreenLocation() {
        return Point2D.of(screenLeft, screenTop);
    }

    public Point2D getAbsoluteLocation() {
        return Point2D.of(absoluteLeft, absoluteTop);
    }

    public Point2D getCenter() {
        return Point2D.of(centerX, centerY);
    }




}
