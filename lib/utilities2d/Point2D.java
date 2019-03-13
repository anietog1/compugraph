package utilities2d;

public class Point2D {
    public double x, y;

    public Point2D(double x, double y, double w) {
        this.x = x / w;
        this.y = y / w;
    }

    public Point2D(double x, double y) {
        this(x, y, 1);
    }

    public Point2D(Point2D p) {
        this(p.x, p.y);
    }

    public static Point2D x() {
        return new Point2D(1, 0);
    }

    public static Point2D y() {
        return new Point2D(0, 1);

    }

    public static Point2D zero() {
        return new Point2D(0, 0);
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public Point2D normalize() {
        double magnitude = magnitude();
        x /= magnitude;
        y /= magnitude;
        return this;
    }

    public Point2D normalized() {
        return new Point2D(this).normalize();
    }

    public double dot(Point2D p) {
        return x * p.x + y * p.y;
    }

    public Point2D negate() {
        x = -x;
        y = -y;
        return this;
    }

    public Point2D negated() {
        return new Point2D(this).negate();
    }

    public Point2D add(Point2D p) {
        x += p.x;
        y += p.y;
        return this;
    }

    public Point2D added(Point2D p) {
        return new Point2D(this).add(p);
    }

    public Point2D substract(Point2D p) {
        x -= p.x;
        y -= p.y;
        return this;
    }

    public Point2D substracted(Point2D p) {
        return new Point2D(this).substract(p);
    }

    public Point2D multiply(double n) {
        x *= n;
        y *= n;
        return this;
    }

    public Point2D multiplied(double n) {
        return new Point2D(this).multiply(n);
    }

    public Point2D apply(UnidirectionalTransformation2D t) {
        Point2D tmp = this.applied(t);
        x = tmp.x;
        y = tmp.y;
        return this;
    }

    public Point2D applied(UnidirectionalTransformation2D t) {
        return t.appliedTo(this);
    }

    public Point2D undo(BidirectionalTransformation2D t) {
        Point2D tmp = this.undone(t);
        x = tmp.x;
        y = tmp.y;
        return this;
    }

    public Point2D undone(BidirectionalTransformation2D t) {
        return t.undoneFrom(this);
    }

    @Override
    public String toString() {
        return "Point2D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
