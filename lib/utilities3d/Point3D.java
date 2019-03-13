package utilities3d;

public final class Point3D {
    public double x, y, z;

    public Point3D(double x, double y, double z, double w) {
        this.x = x / w;
        this.y = y / w;
        this.z = z / w;
    }

    public Point3D(double x, double y, double z) {
        this(x, y, z, 1);
    }

    public Point3D(Point3D p) {
        this(p.x, p.y, p.z);
    }

    public static Point3D x() {
        return new Point3D(1, 0, 0);
    }

    public static Point3D y() {
        return new Point3D(0, 1, 0);

    }

    public static Point3D z() {
        return new Point3D(0, 0, 1);
    }

    public static Point3D zero() {
        return new Point3D(0, 0, 0);
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public Point3D normalize() {
        double magnitude = magnitude();
        x /= magnitude;
        y /= magnitude;
        z /= magnitude;
        return this;
    }

    public Point3D normalized() {
        return new Point3D(this).normalize();
    }

    public Point3D cross(Point3D p) {
        double tmpX = y * p.z - z * p.y;
        double tmpY = x * p.z - z * p.x;
        double tmpZ = x * p.y - y * p.x;

        x = tmpX;
        y = tmpY;
        z = tmpZ;

        return this;
    }

    public Point3D crossed(Point3D p) {
        return new Point3D(this).cross(p);
    }

    public double dot(Point3D p) {
        return x * p.x + y * p.y + z * p.z;
    }

    public Point3D negate() {
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    public Point3D negated() {
        return new Point3D(this).negate();
    }

    public Point3D add(Point3D p) {
        x += p.x;
        y += p.y;
        z += p.z;
        return this;
    }

    public Point3D added(Point3D p) {
        return new Point3D(this).add(p);
    }

    public Point3D substract(Point3D p) {
        x -= p.x;
        y -= p.y;
        z -= p.z;
        return this;
    }

    public Point3D substracted(Point3D p) {
        return new Point3D(this).substract(p);
    }

    public Point3D multiply(double n) {
        x *= n;
        y *= n;
        z *= n;
        return this;
    }

    public Point3D multiplied(double n) {
        return new Point3D(this).multiply(n);
    }

    public Point3D apply(UnidirectionalTransformation3D t) {
        Point3D tmp = this.applied(t);
        x = tmp.x;
        y = tmp.y;
        z = tmp.z;
        return this;
    }

    public Point3D applied(UnidirectionalTransformation3D t) {
        return t.appliedTo(this);
    }

    public Point3D undo(BidirectionalTransformation3D t) {
        Point3D tmp = this.undone(t);
        x = tmp.x;
        y = tmp.y;
        z = tmp.z;
        return this;
    }

    public Point3D undone(BidirectionalTransformation3D t) {
        return t.undoneFrom(this);
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
