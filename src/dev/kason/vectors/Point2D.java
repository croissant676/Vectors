package dev.kason.vectors;

public class Point2D {
    public final double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static final Point2D ORIGIN = new Point2D(0, 0);

    // (x, y)
    public static Point2D from(String input) {
        int commaIndex = input.indexOf(',');
        double x = Double.parseDouble(input.substring(1, commaIndex));
        double y = Double.parseDouble(input.substring(commaIndex + 2, input.length() - 1));
        return new Point2D(x, y);
    }

    public Point2D add(Vector2D vector2D) {
        return new Point2D(x + vector2D.x, y + vector2D.y);
    }

    public Point2D addX(double x) {
        return new Point2D(this.x + x, y);
    }

    public Point2D addY(double y) {
        return new Point2D(x, this.y + y);
    }

    // returned vector + this = point2D
    public Vector2D to(Point2D point2D) {
        return new Vector2D(point2D.x - x, point2D.y - y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
