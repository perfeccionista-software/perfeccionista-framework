package io.perfeccionista.framework.pagefactory.dispatcher.screen;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MobileScreenBounds {

    private final Double width;
    private final Double height;

    private Double topBarHeight = null;
    private Double rightBarHeight = null;
    private Double bottomBarHeight = null;
    private Double leftBarHeight = null;

    private Double topBound = null;
    private Double rightBound = null;
    private Double bottomBound = null;
    private Double leftBound = null;

    private MobileScreenBounds(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public static MobileScreenBounds of(double width, double height) {
        return new MobileScreenBounds(width, height);
    }

    public MobileScreenBounds setTopBarHeight(double topBarHeight) {
        this.topBarHeight = topBarHeight;
        return this;
    }

    public MobileScreenBounds setRightBarHeight(double rightBarHeight) {
        this.rightBarHeight = rightBarHeight;
        return this;
    }

    public MobileScreenBounds setBottomBarHeight(double bottomBarHeight) {
        this.bottomBarHeight = bottomBarHeight;
        return this;
    }

    public MobileScreenBounds setLeftBarHeight(double leftBarHeight) {
        this.leftBarHeight = leftBarHeight;
        return this;
    }

    public @NotNull double getWidth() {
        return width;
    }

    public @NotNull double getHeight() {
        return height;
    }

    public @NotNull double getTopBarHeight() {
        return Objects.isNull(topBarHeight) ? 0 : topBarHeight;
    }

    public @NotNull double getRightBarHeight() {
        return Objects.isNull(rightBarHeight) ? 0 : rightBarHeight;
    }

    public @NotNull double getBottomBarHeight() {
        return Objects.isNull(bottomBarHeight) ? 0 : bottomBarHeight;
    }

    public @NotNull double getLeftBarHeight() {
        return Objects.isNull(leftBarHeight) ? 0 : leftBarHeight;
    }

    public @NotNull double getTopBound() {
        return getTopBarHeight();
    }

    public @NotNull double getRightBound() {
        return width - getRightBarHeight();
    }

    public @NotNull double getBottomBound() {
        return height - getBottomBarHeight();
    }

    public @NotNull double getLeftBound() {
        return getLeftBarHeight();
    }

    // TODO: Equals & HashCode


    @Override
    public String toString() {
        return "MobileScreenBounds{" +
                "width=" + width +
                ", height=" + height +
                ", topBound=" + topBound +
                ", rightBound=" + rightBound +
                ", bottomBound=" + bottomBound +
                ", leftBound=" + leftBound +
                '}';
    }
}
