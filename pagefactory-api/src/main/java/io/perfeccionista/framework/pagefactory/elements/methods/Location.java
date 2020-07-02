package io.perfeccionista.framework.pagefactory.elements.methods;

// TODO: Сделать в конструкторе допустимую погрешность в размерах,
//  которая будет учитываться при сравнении с другим местоположением
// TODO: Здесь или в Dimensions добавить запрос точки центра элемента
public class Location {

    private final double pageX;
    private final double pageY;

    private final double absolutePageX;
    private final double absolutePageY;

    public Location(double pageX, double pageY, double absolutePageX, double absolutePageY) {
        this.pageX = pageX;
        this.pageY = pageY;
        this.absolutePageX = absolutePageX;
        this.absolutePageY = absolutePageY;
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

    public Location offset(double x, double y) {
        return new Location(pageX + x, pageY + y, absolutePageX + x, absolutePageY + y);
    }

    @Override
    public String toString() {
        return "Location : {pageX : " + pageX + ", pageY : " + pageY + ", absolutePageX : " + absolutePageX + ", absolutePageY = " + absolutePageY + '}';
    }

}
