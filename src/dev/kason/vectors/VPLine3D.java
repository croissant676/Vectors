package dev.kason.vectors;

// vector parametric line in 3D space
public class VPLine3D {

    public final Point3D p;
    public final Vector3D v;

    public VPLine3D(Point3D p, Vector3D v) {
        this.p = p;
        this.v = v;
    }

    public VPLine3D(Point3D p1, Point3D p2) {
        this.p = p1;
        this.v = p1.to(p2);
    }

    public boolean contains(Point3D p) {
        Vector3D distance = this.p.to(p);
        return distance.parallel(v);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VPLine3D vpLine3D)) {
            return false;
        }
        if (!v.parallel(vpLine3D.v)) {
            return false;
        }
        Vector3D distance = p.to(vpLine3D.p);
        return distance.parallel(v);
    }

    public String toString() {
        return p + " + " + v + " * t";
    }

    // returns a string[] of the parametric equations of the line
    // such as {"x = 1 + 2 * t", "y = 3 + 4 * t", "z = 5 + 6 * t"}
    public String[] parametricEquations() {
        return new String[] {
            "x = " + p.x + " + " + v.x + " * t",
            "y = " + p.y + " + " + v.y + " * t",
            "z = " + p.z + " + " + v.z + " * t"
        };
    }

    // returns a string[]  of the symmetric equations of the line
    // such as {"x = (x - 3) / 5", "y = (y - 4) / 6", "z = (z - 5) / 7"}
    public String[] symmetricEquations() {
        return new String[] {
            (v.x == 0 ? "x = " + p.x : "(x - " + p.x + ") / " + v.x) + " = "
                    + (v.y == 0 ? "y = " + p.y : "(y - " + p.y + ") / " + v.y) + " = "
                    + (v.z == 0 ? "z = " + p.z : "(z - " + p.z + ") / " + v.z)
        };
    }

}
