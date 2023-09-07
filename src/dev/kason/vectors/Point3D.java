public class Point3D {

    public final double x, y, z;

    public static final Point3D ORIGIN = new Point3D(0, 0, 0);

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // takes in input in the form (x, y, z)
    // first and last char are ignored
    public static Point3D fromString(String input) {
        int commaIndex = input.indexOf(',');
        int secondCommaIndex = input.indexOf(',', commaIndex + 1);
        double x = Double.parseDouble(input.substring(1, commaIndex));
        double y = Double.parseDouble(input.substring(commaIndex + 2, secondCommaIndex));
        double z = Double.parseDouble(input.substring(secondCommaIndex + 2, input.length() - 1));
        return new Point3D(x, y, z);
    }

    public Point3D add(Vector3D vector3D) {
        return new Point3D(x + vector3D.x, y + vector3D.y, z + vector3D.z);
    }

    public Point3D addX(double x) {
        return new Point3D(this.x + x, y, z);
    }

    public Point3D addY(double y) {
        return new Point3D(x, this.y + y, z);
    }

    public Point3D addZ(double z) {
        return new Point3D(x, y, this.z + z);
    }

    // returned vector + this = point3D
    public Vector3D to(Point3D point3D) {
        return new Vector3D(point3D.x - x, point3D.y - y, point3D.z - z);
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
