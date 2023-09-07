package dev.kason.vectors;

// vector parameterized line 2D
// p + v * t
public class VPLine2D {

    public final Point2D p;
    public final Vector2D v;

    public VPLine2D(Point2D p, Vector2D v) {
        this.p = p;
        this.v = v;
    }

    public VPLine2D(Point2D p1, Point2D p2) {
        this.p = p1;
        this.v = p1.to(p2);
    }

    public boolean contains(Point2D p) {
        Vector2D distance = this.p.to(p);
        return distance.parallel(v);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VPLine2D vpLine2D)) {
            return false;
        }
        if (!v.parallel(vpLine2D.v)) {
            return false;
        }
        Vector2D distance = p.to(vpLine2D.p);
        return distance.parallel(v);
    }

    public String toString() {
        return p + " + " + v + " * t";
    }
}
