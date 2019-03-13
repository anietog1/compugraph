package utilities2d;

public class BidirectionalTransformation2D extends UnidirectionalTransformation2D {
    public Matrix3x3 undoMatrix;

    public BidirectionalTransformation2D(double doMatrix[][], double undoMatrix[][]) {
        super(doMatrix);
        this.undoMatrix = new Matrix3x3(undoMatrix);
    }

    public BidirectionalTransformation2D(Matrix3x3 doMatrix, Matrix3x3 undoMatrix) {
        this(doMatrix.matrix, undoMatrix.matrix);
    }

    public BidirectionalTransformation2D(BidirectionalTransformation2D t) {
        this(t.doMatrix, t.undoMatrix);
    }

    public BidirectionalTransformation2D(UnidirectionalTransformation2D doTrans, UnidirectionalTransformation2D undoTrans) {
        this(doTrans.doMatrix, undoTrans.doMatrix);
    }

    public static BidirectionalTransformation2D identity() {
        return new BidirectionalTransformation2D(Matrix3x3.identity(), Matrix3x3.identity());
    }

    public static BidirectionalTransformation2D translationX(double dx) {
        return translation(dx, 0);
    }

    public static BidirectionalTransformation2D translationY(double dy) {
        return translation(0, dy);
    }

    public static BidirectionalTransformation2D translation(double dx, double dy) {
        UnidirectionalTransformation2D doTrans = UnidirectionalTransformation2D.translation(dx, dy);
        UnidirectionalTransformation2D undoTrans = UnidirectionalTransformation2D.translation(-dx, -dy);
        return new BidirectionalTransformation2D(doTrans, undoTrans);
    }

    public static BidirectionalTransformation2D scalationX(double sx) {
        return scalation(sx, 1, 1);
    }

    public static BidirectionalTransformation2D scalationY(double sy) {
        return scalation(1, sy, 1);
    }

    public static BidirectionalTransformation2D scalation(double sx, double sy, double sz) {
        UnidirectionalTransformation2D doTrans = UnidirectionalTransformation2D.scalation(sx, sy);
        UnidirectionalTransformation2D undoTrans = UnidirectionalTransformation2D.scalation(1 / sx, 1 / sy);
        return new BidirectionalTransformation2D(doTrans, undoTrans);
    }

    public static BidirectionalTransformation2D rotation(double theta) {
        UnidirectionalTransformation2D doTrans = UnidirectionalTransformation2D.rotation(theta);
        UnidirectionalTransformation2D undoTrans = UnidirectionalTransformation2D.rotation(-theta);
        return new BidirectionalTransformation2D(doTrans, undoTrans);
    }

    public BidirectionalTransformation2D compose(BidirectionalTransformation2D t) {
        super.compose(t);
        undoMatrix.multiply(t.undoMatrix);
        return this;
    }

    public BidirectionalTransformation2D composed(BidirectionalTransformation2D t) {
        return new BidirectionalTransformation2D(this).compose(t);
    }

    public BidirectionalTransformation2D invert() {
        Matrix3x3 tmp = doMatrix;
        doMatrix = undoMatrix;
        undoMatrix = tmp;
        return this;
    }

    public BidirectionalTransformation2D inverse() {
        return new BidirectionalTransformation2D(this).invert();
    }

    public Point2D undoFrom(Point2D p) {
        return p.undo(this);
    }

    public Point2D undoneFrom(Point2D p) {
        return undoMatrix.multiply(p);
    }
}
