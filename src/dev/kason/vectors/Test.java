package dev.kason.vectors;

import static dev.kason.vectors.Shortener.*;

public class Test {
    public static void main(String[] args) {
        var p = p("3x - y + 2z = 6");
        print(p);
        print(p.normal());
        print(p.closestPoint(Point3D.ORIGIN));
        var target = p3(1, 5, -4);
        print(p.closestPoint(target));
    }
}
