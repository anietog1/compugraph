package utilities3d;

public class Camera3D {
    public Point3D P, N, V;
    public double d;

    public Camera3D(Point3D position, Point3D N, Point3D orientation, double d) {
        this.P = new Point3D(position);
        this.N = new Point3D(N);
        this.V = new Point3D(orientation);
        this.d = d;
    }

    public Camera3D(Camera3D c) {
        this(c.P, c.N, c.V, c.d);
    }

    public Camera3D(double d) {
        this(Point3D.zero(), Point3D.z().negate(), Point3D.y(), d);
    }

    public void lookAt(Point3D p) {
        N = P.substracted(p).negate();
    }

    public UnidirectionalTransformation3D projectionTransformation() {
        Point3D n = N.normalized();
        Point3D u = V.crossed(n).normalize();
        Point3D v = n.crossed(u);

        UnidirectionalTransformation3D translation = UnidirectionalTransformation3D.translation(-P.x, -P.y, -P.z);

        double doRotationMatrix[][] = {
            {u.x, u.y, u.z, 0},
            {v.x, v.y, v.z, 0},
            {n.x, n.y, n.z, 0},
            {0, 0, 0, 1}
        };

        UnidirectionalTransformation3D rotation = new UnidirectionalTransformation3D(doRotationMatrix);

        double doProjectionMatrix[][] = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 1 / d, 0}
        };

        UnidirectionalTransformation3D projection = new UnidirectionalTransformation3D(doProjectionMatrix);

        return translation.compose(rotation).compose(projection);
    }
}
