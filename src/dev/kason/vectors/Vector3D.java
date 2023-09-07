package dev.kason.vectors;

public class Vector3D {
    public final double x, y, z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3D from(String input) {
        return Point3D.ORIGIN.to(Point3D.from(input)); // point3d doesn't care about first, last char
    }

    // <u1 + v1, u2 + v2, u3 + v3>
    public Vector3D add(Vector3D v) {
        return new Vector3D(x + v.x, y + v.y, z + v.z);
    }

    // d * <u1, u2, u3>
    public Vector3D times(double d) {
        return new Vector3D(x * d, y * d, z * d);
    }

    // d . <u1, u2, u3> -> d * u1 + d * u2 + d * u3
    public double dot(Vector3D v) {
        return x * v.x + y * v.y + z * v.z;
    }

    //
    // -------- = u2 * v3 - u3 * v2
    // u1 u2 u3 - u1 * v3 - u3 * v1
    // v1 v2 v3 + u1 * v2 - u2 * v1
    // <u2 * v3 - u3 * v2, u3 * v1 - u1 * v3, u1 * v2 - u2 * v1>
    public Vector3D cross(Vector3D v) {
        return new Vector3D(y * v.z - z * v.y,
                            z * v.x - x * v.z,
                            x * v.y - y * v.x);
    }

    // |<u1, u2, u3>| = sqrt(u1^2 + u2^2 + u3^2)
    public double mag() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public boolean parallel(Vector3D v) {
        double factor = x / v.x;
        // possible rounding error
        return factor == (y / v.y) && factor == (z / v.z);
    }

    // cos(theta) = u . v / (|u| * |v|)
    // theta = arccos(u . v / (|u| * |v|))
    public double angle(Vector3D v) {
        return Math.acos(dot(v) / (mag() * v.mag()));
    }

    public Vector3D projection(Vector3D v) {
        return v.times(projMag(v));
    }

    public double projMag(Vector3D v) {
        return dot(v.unit());
    }

    // <u1, u2, u3> / |<u1, u2, u3>|
    public Vector3D unit() {
        return times(1 / mag());
    }

    public String toString() {
        return "<" + x + ", " + y + ", " + z + ">";
    }
}
