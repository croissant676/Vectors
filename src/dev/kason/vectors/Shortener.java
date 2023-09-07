public class Utilities {

    public static Point3D p3(String input) {
        return Point3D.fromString(input);
    }

    public static Point3D p3(double x, double y, double z) {
        return new Point3D(x, y, z);
    }

    public static Point2D p2(String input) {
        return Point2D.fromString(input);
    }

    public static Point2D p2(double x, double y) {
        return new Point2D(x, y);
    }

    public static Vector3D v3(String input) {
        return Vector3D.fromString(input);
    }

    public static Vector3D v3(double x, double y, double z) {
        return new Vector3D(x, y, z);
    }

    public static Vector2D v2(String input) {
        return Vector2D.fromString(input);
    }

    public static Vector2D v2(double x, double y) {
        return new Vector2D(x, y);
    }

    public static Plane p(String input) {
        return Plane.fromString(input);
    }

    public static Plane p(double a, double b, double c, double d) {
        return new Plane(a, b, c, d);
    }

    public static Plane p(Point3D p1, Point3D p2, Point3D p3) {
        return new Plane(p1, p2, p3);
    }
}
