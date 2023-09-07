import java.util.Objects;

public class Plane {
    // for a plane ax + by + cz + d = 0
    public final double a, b, c, d;
    private final Point3D basisPoint;

    public Plane(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.basisPoint = getClosestPoint(Point3D.ORIGIN);
    }

    // takes in input in the form 3x + 4y + 5z = 6
    // --OR--
    // 3x - y + 2z - 6 = 0
    public static Plane fromString(String input) {
        int xIndex = input.indexOf('x'), yIndex = input.indexOf('y'), zIndex = input.indexOf('z');
        double a = parseCoefficient(input.substring(0, xIndex));
        double b = parseCoefficient(input.substring(xIndex + 1, yIndex));
        double c = parseCoefficient(input.substring(yIndex + 1, zIndex));
        double d;
        if (input.endsWith("= 0")) {
            d = parseCoefficient(input.substring(zIndex + 1, input.length() - 3));
        } else {
            d = -parseCoefficient(input.substring(zIndex + 3));
        }
        return new Plane(a, b, c, d);
    }

    private static double parseCoefficient(String input) {
        input = input.replace(" ", "");
        if (input.length() == 0) {
            return 1;
        } else if (input.equals("-")) {
            return -1;
        }
        return Double.parseDouble(input);
    }

    public Point3D getClosestPoint(Point3D target) {
        // x, y, z = normal * e + <x0, y0, z0> = <a, b, c> * e + <x0, y0, z0>
        // x = a * e + x0, y = b * e + y0, z = c * e + z0
        // a * x + b * y + c * z + d = 0
        // a * (a * e + x0) + b * (b * e + y0) + c * (c * e + z0) + d = 0
        // a^2 * e + b^2 * e + c^2 * e = -d - a * x0 - b * y0 - c * z0
        // e = (-d - a * x0 - b * y0 - c * z0) / (a^2 + b^2 + c^2)
        double e = (-d - a * target.x - b * target.y - c * target.z) / (a * a + b * b + c * c);
        return new Point3D(a * e + target.x, b * e + target.y, c * e + target.z);
    }

    public Plane(Point3D a, Point3D b, Point3D c) {
        Vector3D ab = a.to(b);
        Vector3D ac = a.to(c);
        Vector3D normal = ab.crossProduct(ac);
        // using point a
        // n1 (x - a1) + n2 (y - a2) + n3 (z - a3) = 0
        // = n1 x + n2 y + n3 z - (n1 a1 + n2 a2 + n3 a3) = 0
        this.a = normal.x;
        this.b = normal.y;
        this.c = normal.z;
        this.d = -(normal.x * a.x + normal.y * a.y + normal.z * a.z);

        this.basisPoint = a;
    }

    public boolean contains(Point3D p) {
        return a * p.x + b * p.y + c * p.z + d == 0;
    }

    public double distanceTo(Point3D q) {
//        // using theorem 11.13
//        Vector3D pq = basisPoint.to(q);
//        return Math.abs(normal().dotProduct(pq) / normal().magnitude());
        // we could also use this.to(q).magnitude()
        return normal().magnitudeOfProjection(basisPoint.to(q));
    }

    public Vector3D to(Point3D p) {
        return getClosestPoint(p).to(p);
    }

    public Vector3D normal() {
        return new Vector3D(a, b, c);
    }

    private void addString(double number, char letter, StringBuilder builder) {
        if (number == 0) {
            return;
        }
        if (number == 1) {
            builder.append(" + ").append(letter);
        } else if (number == -1) {
            builder.append(" - ").append(letter);
        } else {
            String operator = number > 0 ? " + " : " - ";
            builder.append(operator).append(Math.abs(number)).append(letter);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return Double.compare(plane.a, a) == 0 && Double.compare(plane.b, b) == 0
                && Double.compare(plane.c, c) == 0 && Double.compare(plane.d, d) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, d);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (a != 0) {
            if (a == 1) {
                sb.append('x');
            } else if (a == -1) {
                sb.append("-x");
            } else {
                sb.append(a).append('x');
            }
        }
        addString(b, 'y', sb);
        addString(c, 'z', sb);
        if (d != 0) {
            sb.append(d > 0 ? " + " : " - ").append(Math.abs(d));
        }
        sb.append(" = 0");
        return sb.toString();
    }

}
