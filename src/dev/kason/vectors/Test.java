public class Test {
    public static void main(String[] args) {
        Plane plane = Plane.fromString("3x - y + 2z = 6");
        System.out.println(plane);
        System.out.println(plane.normal());
        System.out.println(plane.getClosestPoint(Point3D.ORIGIN));
        System.out.println(plane.getClosestPoint(new Point3D(1, 5, -4)));

        var p = p("3x - y + 2z");
    }
}
