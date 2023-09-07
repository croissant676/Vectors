public class Vector3D {
    public final double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D fromString(String input) {
        return Point3D.ORIGIN.to(Point3D.fromString(input)); // point3d doesn't care about first, last char
    }

    // <u1 + v1, u2 + v2, u3 + v3>
    public Vector3D add(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }

    // d * <u1, u2, u3>
    public Vector3D multiply(double d) {
        return new Vector3D(x * d, y * d, z * d);
    }

    // d . <u1, u2, u3> -> d * u1 + d * u2 + d * u3
    public double dotProduct(Vector3D v) {
        return x * v.x + y * v.y + z * v.z;
    }

    //
    // -------- = u2 * v3 - u3 * v2
    // u1 u2 u3 - u1 * v3 - u3 * v1
    // v1 v2 v3 + u1 * v2 - u2 * v1
    // <u2 * v3 - u3 * v2, u3 * v1 - u1 * v3, u1 * v2 - u2 * v1>
    public Vector3D crossProduct(Vector3D v) {
        return new Vector3D(y * v.z - z * v.y,
                            z * v.x - x * v.z,
                            x * v.y - y * v.x);
    }

    // |<u1, u2, u3>| = sqrt(u1^2 + u2^2 + u3^2)
    public double magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public boolean isParallel(Vector3D v) {
        double factor = x / v.x;
        // possible rounding error
        return factor == (y / v.y) && factor == (z / v.z);
    }

    // cos(theta) = u . v / (|u| * |v|)
    // theta = arccos(u . v / (|u| * |v|))
    public double angle(Vector3D v) {
        return Math.acos(dotProduct(v) / (magnitude() * v.magnitude()));
    }

    public Vector3D projection(Vector3D v) {
        return v.multiply(magnitudeOfProjection(v));
    }

    public double magnitudeOfProjection(Vector3D v) {
        return dotProduct(v.unitVector());
    }

    // <u1, u2, u3> / |<u1, u2, u3>|
    public Vector3D unitVector() {
        return multiply(1 / magnitude());
    }

    public String toString() {
        return "<" + x + ", " + y + ", " + z + ">";
    }
}
