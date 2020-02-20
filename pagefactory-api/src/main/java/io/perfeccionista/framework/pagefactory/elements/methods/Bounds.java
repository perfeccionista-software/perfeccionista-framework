package io.perfeccionista.framework.pagefactory.elements.methods;

/**
 * Ширина, высота, положение относительно видимой области и всей страницы
 */
public final class Bounds {

    private final double width;
    private final double height;

    private final double screenX;
    private final double screenY;

    private final double webPageAbsoluteX;
    private final double webPageAbsoluteY;

    public Bounds(double width, double height, double screenX, double screenY, double webPageAbsoluteX, double webPageAbsoluteY) {
        this.width = width;
        this.height = height;
        this.screenX = screenX;
        this.screenY = screenY;
        this.webPageAbsoluteX = webPageAbsoluteX;
        this.webPageAbsoluteY = webPageAbsoluteY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getScreenX() {
        return screenX;
    }

    public double getScreenY() {
        return screenY;
    }

    public double getWebPageAbsoluteX() {
        return webPageAbsoluteX;
    }

    public double getWebPageAbsoluteY() {
        return webPageAbsoluteY;
    }

    // TODO: Сделать описание
    @Override
    public String toString() {
        return "";
    }

}
