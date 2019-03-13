package utilities3d;

public class UnidirectionalTransformation2D {
    public Matrix3x3 doMatrix;

    public UnidirectionalTransformation2D(double doMatrix[][]) {
        this.doMatrix = new Matrix3x3(doMatrix);
    }

    public UnidirectionalTransformation2D(Matrix3x3 doMatrix) {
        this(doMatrix.matrix);
    }

    public UnidirectionalTransformation2D(UnidirectionalTransformation2D t) {
        this(t.doMatrix);
    }

    public static UnidirectionalTransformation2D identity() {
        return new UnidirectionalTransformation2D(Matrix3x3.identity());
    }

    public static UnidirectionalTransformation2D translationX(double dx) {
        return translation(dx, 0);
    }

    public static UnidirectionalTransformation2D translationY(double dy) {
        return translation(0, dy);
    }

    public static UnidirectionalTransformation2D translation(double dx, double dy) {
        double doMatrix[][] = {
            {1, 0, dx},
            {0, 1, dy},
            {0, 0, 1}
        };

        return new UnidirectionalTransformation2D(doMatrix);
    }

    public static UnidirectionalTransformation2D scalationX(double sx) {
        return scalation(sx, 1);
    }

    public static UnidirectionalTransformation2D scalationY(double sy) {
        return scalation(1, sy);
    }

    public static UnidirectionalTransformation2D scalation(double sx, double sy) {
        double doMatrix[][] = {
            {sx, 0, 0},
            {0, sy, 0},
            {0, 0, 1}
        };

        return new UnidirectionalTransformation2D(doMatrix);
    }

    public static UnidirectionalTransformation2D rotation(double theta) {
        double doMatrix[][] = {
            {Math.cos(theta), -Math.sin(theta), 0},
            {Math.sin(theta), Math.cos(theta), 0},
            {0, 0, 1}
        };

        return new UnidirectionalTransformation2D(doMatrix);
    }

    public UnidirectionalTransformation2D compose(UnidirectionalTransformation2D t) {
        doMatrix = t.doMatrix.multiplied(doMatrix);
        return this;
    }

    public UnidirectionalTransformation2D composed(UnidirectionalTransformation2D t) {
        return new UnidirectionalTransformation2D(this).compose(t);
    }

    public Point2D applyTo(Point2D p) {
        return p.apply(this);
    }

    public Point2D appliedTo(Point2D p) {
        return doMatrix.multiply(p);
    }
}
