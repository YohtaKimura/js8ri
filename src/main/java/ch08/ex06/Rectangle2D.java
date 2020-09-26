package ch08.ex06;

import java.util.Comparator;

public final class Rectangle2D {

    private final Point2D origin;
    private final double width;
    private final double height;

    public static final Comparator<Rectangle2D> COMPARATOR
            = Comparator.comparing(Rectangle2D::getOrigin, Point2D.COMPARATOR).
            thenComparing(Rectangle2D::getWidth).
            thenComparing(Rectangle2D::getHeight);

    public Rectangle2D(Point2D origin, double width, double height) {
        this.origin = origin;
        this.width = width;
        this.height = height;
    }

    public Point2D getOrigin() {
        return origin;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

}
