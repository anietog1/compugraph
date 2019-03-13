package utilities3d;

public class UnidirectionalTransformation3D {
    public Matrix4x4 doMatrix;

    public UnidirectionalTransformation3D(double doMatrix[][]) {
        this.doMatrix = new Matrix4x4(doMatrix);
    }

    public UnidirectionalTransformation3D(Matrix4x4 doMatrix) {
        this(doMatrix.matrix);
    }

    public UnidirectionalTransformation3D(UnidirectionalTransformation3D t) {
        this(t.doMatrix);
    }

    public static UnidirectionalTransformation3D identity() {
        return new UnidirectionalTransformation3D(Matrix4x4.identity());
    }

    public static UnidirectionalTransformation3D translationX(double dx) {
        return translation(dx, 0, 0);
    }

    public static UnidirectionalTransformation3D translationY(double dy) {
        return translation(0, dy, 0);
    }

    public static UnidirectionalTransformation3D translationZ(double dz) {
        return translation(0, 0, dz);
    }

    public static UnidirectionalTransformation3D translation(double dx, double dy, double dz) {
        double doMatrix[][] = {
            {1, 0, 0, dx},
            {0, 1, 0, dy},
            {0, 0, 1, dz},
            {0, 0, 0, 1}
        };

        return new UnidirectionalTransformation3D(doMatrix);
    }

    public static UnidirectionalTransformation3D scalationX(double sx) {
        return scalation(sx, 1, 1);
    }

    public static UnidirectionalTransformation3D scalationY(double sy) {
        return scalation(1, sy, 1);
    }

    public static UnidirectionalTransformation3D scalationZ(double sz) {
        return scalation(1, 1, sz);
    }

    public static UnidirectionalTransformation3D scalation(double sx, double sy, double sz) {
        double doMatrix[][] = {
            {sx, 0, 0, 0},
            {0, sy, 0, 0},
            {0, 0, sz, 0},
            {0, 0, 0, 1}
        };

        return new UnidirectionalTransformation3D(doMatrix);
    }

    public static UnidirectionalTransformation3D rotationX(double dtheta) {
        double doMatrix[][] = {
            {1, 0, 0, 0},
            {0, Math.cos(dtheta), -Math.sin(dtheta), 0},
            {0, Math.sin(dtheta), Math.cos(dtheta), 0},
            {0, 0, 0, 1}
        };

        return new UnidirectionalTransformation3D(doMatrix);
    }

    public static UnidirectionalTransformation3D rotationY(double dtheta) {
        double doMatrix[][] = {
            {Math.cos(dtheta), 0, Math.sin(dtheta), 0},
            {0, 1, 0, 0},
            {-Math.sin(dtheta), 0, Math.cos(dtheta), 0},
            {0, 0, 0, 1}
        };

        return new UnidirectionalTransformation3D(doMatrix);
    }

    public static UnidirectionalTransformation3D rotationZ(double dtheta) {
        double doMatrix[][] = {
            {Math.cos(dtheta), -Math.sin(dtheta), 0, 0},
            {Math.sin(dtheta), Math.cos(dtheta), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };

        return new UnidirectionalTransformation3D(doMatrix);
    }

    public static UnidirectionalTransformation3D rotation(double dthetax, double dthetay, double dthetaz) {
        UnidirectionalTransformation3D t = identity();

        if (dthetax != 0) {
            t.compose(rotationX(dthetax));
        }

        if (dthetay != 0) {
            t.compose(rotationY(dthetay));
        }

        if (dthetaz != 0) {
            t.compose(rotationZ(dthetaz));
        }

        return t;
    }

    public UnidirectionalTransformation3D compose(UnidirectionalTransformation3D t) {
        doMatrix = t.doMatrix.multiplied(doMatrix);
        return this;
    }

    public UnidirectionalTransformation3D composed(UnidirectionalTransformation3D t) {
        return new UnidirectionalTransformation3D(this).compose(t);
    }

    public Point3D applyTo(Point3D p) {
        return p.apply(this);
    }

    public Point3D appliedTo(Point3D p) {
        return doMatrix.multiply(p);
    }
}
