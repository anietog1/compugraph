package utilities3d;

public class BidirectionalTransformation3D extends UnidirectionalTransformation3D {
    public Matrix4x4 undoMatrix;

    public BidirectionalTransformation3D(double doMatrix[][], double undoMatrix[][]) {
        super(doMatrix);
        this.undoMatrix = new Matrix4x4(undoMatrix);
    }

    public BidirectionalTransformation3D(Matrix4x4 doMatrix, Matrix4x4 undoMatrix) {
        this(doMatrix.matrix, undoMatrix.matrix);
    }

    public BidirectionalTransformation3D(BidirectionalTransformation3D t) {
        this(t.doMatrix, t.undoMatrix);
    }

    public BidirectionalTransformation3D(UnidirectionalTransformation3D doTrans, UnidirectionalTransformation3D undoTrans) {
        this(doTrans.doMatrix, undoTrans.doMatrix);
    }

    public static BidirectionalTransformation3D identity() {
        return new BidirectionalTransformation3D(Matrix4x4.identity(), Matrix4x4.identity());
    }

    public static BidirectionalTransformation3D translationX(double dx) {
        return translation(dx, 0, 0);
    }

    public static BidirectionalTransformation3D translationY(double dy) {
        return translation(0, dy, 0);
    }

    public static BidirectionalTransformation3D translationZ(double dz) {
        return translation(0, 0, dz);
    }

    public static BidirectionalTransformation3D translation(double dx, double dy, double dz) {
        UnidirectionalTransformation3D doTrans = UnidirectionalTransformation3D.translation(dx, dy, dz);
        UnidirectionalTransformation3D undoTrans = UnidirectionalTransformation3D.translation(-dx, -dy, -dz);
        return new BidirectionalTransformation3D(doTrans, undoTrans);
    }

    public static BidirectionalTransformation3D scalationX(double sx) {
        return scalation(sx, 1, 1);
    }

    public static BidirectionalTransformation3D scalationY(double sy) {
        return scalation(1, sy, 1);
    }

    public static BidirectionalTransformation3D scalationZ(double sz) {
        return scalation(1, 1, sz);
    }

    public static BidirectionalTransformation3D scalation(double sx, double sy, double sz) {
        UnidirectionalTransformation3D doTrans = UnidirectionalTransformation3D.scalation(sx, sy, sz);
        UnidirectionalTransformation3D undoTrans = UnidirectionalTransformation3D.scalation(1 / sx, 1 / sy, 1 / sz);
        return new BidirectionalTransformation3D(doTrans, undoTrans);
    }

    public static BidirectionalTransformation3D rotationX(double thetax) {
        UnidirectionalTransformation3D doTrans = UnidirectionalTransformation3D.rotationX(thetax);
        UnidirectionalTransformation3D undoTrans = UnidirectionalTransformation3D.rotationX(thetax);
        return new BidirectionalTransformation3D(doTrans, undoTrans);
    }

    public static BidirectionalTransformation3D rotationY(double thetay) {
        UnidirectionalTransformation3D doTrans = UnidirectionalTransformation3D.rotationY(thetay);
        UnidirectionalTransformation3D undoTrans = UnidirectionalTransformation3D.rotationY(thetay);
        return new BidirectionalTransformation3D(doTrans, undoTrans);
    }

    public static BidirectionalTransformation3D rotationZ(double thetaz) {
        UnidirectionalTransformation3D doMatrix = UnidirectionalTransformation3D.rotationZ(thetaz);
        UnidirectionalTransformation3D undoMatrix = UnidirectionalTransformation3D.rotationZ(thetaz);
        return new BidirectionalTransformation3D(doMatrix, undoMatrix);
    }

    public static BidirectionalTransformation3D rotation(double thetax, double thetay, double thetaz) {
        BidirectionalTransformation3D t = identity();

        if (thetax != 0) {
            t.compose(rotationX(thetax));
        }

        if (thetay != 0) {
            t.compose(rotationY(thetay));
        }

        if (thetaz != 0) {
            t.compose(rotationZ(thetaz));
        }

        return t;
    }

    public BidirectionalTransformation3D compose(BidirectionalTransformation3D t) {
        doMatrix = t.doMatrix.multiplied(doMatrix);
        undoMatrix.multiply(t.undoMatrix);
        return this;
    }

    public BidirectionalTransformation3D composed(BidirectionalTransformation3D t) {
        return new BidirectionalTransformation3D(this).compose(t);
    }

    public BidirectionalTransformation3D invert() {
        Matrix4x4 tmp = doMatrix;
        doMatrix = undoMatrix;
        undoMatrix = tmp;
        return this;
    }

    public BidirectionalTransformation3D inverse() {
        return new BidirectionalTransformation3D(this).invert();
    }

    public Point3D undoFrom(Point3D p) {
        return p.undo(this);
    }

    public Point3D undoneFrom(Point3D p) {
        return undoMatrix.multiply(p);
    }
}
