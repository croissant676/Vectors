package dev.kason.vectors;

public class Vector2D {

    public final double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2D fromDirection(double direction, double magnitude) {
        return new Vector2D(magnitude * Math.cos(direction), magnitude * Math.sin(direction));
    }

    public static Vector2D from(String input) {
        return Point2D.ORIGIN.to(Point2D.from(input));
    }

    // (magnitude @ direction)
    public static Vector2D fromDirectionalString(String input) {
        int atIndex = input.indexOf('@');
        double magnitude = Double.parseDouble(input.substring(1, atIndex - 1));
        double direction = Double.parseDouble(input.substring(atIndex + 2, input.length() - 1));
        return fromDirection(direction, magnitude);
    }

    // because we use this a lot
    private double cachedDirection = Double.NaN;

    public double direction() {
        if (Double.isNaN(cachedDirection)) {
            cachedDirection = Math.atan2(y, x);
        }
        return cachedDirection;
    }

    // <u1 + v1, u2 + v2>
    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.x, y + v.y);
    }

    // d * <u1, u2>
    public Vector2D times(double d) {
        return new Vector2D(x * d, y * d);
    }

    // d . <u1, u2> -> d * u1 + d * u2
    public double dot(Vector2D v) {
        return x * v.x + y * v.y;
    }

    // |<u1, u2>| = sqrt(u1^2 + u2^2)
    public double mag() {
        return Math.sqrt(x * x + y * y);
    }

    // the mag of the project of this vector onto v
    public double projMag(Vector2D v) {
        return dot(v.unit());
    }

    public Vector2D proj(Vector2D v) {
        return Vector2D.fromDirection(direction(), projMag(v));
    }

    public boolean parallel(Vector2D v) {
        double factor = x / v.x;
        return factor == (y / v.y);
    }

    public boolean orthogonal(Vector2D v) {
        return dot(v) == 0;
    }

    // cos(theta) = u . v / (|u| * |v|)
    // theta = arccos(u . v / (|u| * |v|))
    public double angle(Vector2D v) {
        return Math.acos(dot(v) / (mag() * v.mag()));
    }

    // <u1, u2> / |<u1, u2>|
    public Vector2D unit() {
        return Vector2D.fromDirection(direction(), 1);
    }

    public String toString() {
        return "<" + x + ", " + y + "> (" + mag() + " @ " + direction() + ")";
    }


}
