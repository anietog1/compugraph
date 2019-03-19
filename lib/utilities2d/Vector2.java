package utilities2d;

public final class Vector2 {
    public final double x, y;

    public Vector2(double x, double y, double w) {
        this.x = x / w;
        this.y = y / w;
    }

    public Vector2(double x, double y) {
        this(x, y, 1);
    }

    public static Vector2 zero() {
        return new Vector2(0, 0);
    }

    public static Vector2 x() {
        return new Vector2(1, 0);
    }

    public static Vector2 y() {
        return new Vector2(0, 1);
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public Vector2 normalized() {
        double mag = magnitude();
        return new Vector2(x / mag, y / mag);
    }

    public Vector2 plus(Vector2 p) {
        return new Vector2(x + p.x, y + p.y);
    }

    public Vector2 times(double n) {
        return new Vector2(x * n, y * n);
    }

    public double dot(Vector2 p) {
        return x * p.x + y * p.y;
    }

    @Override
    public String toString() {
        return "Vector2{" + "x=" + x + ", y=" + y + '}';
    }
}
