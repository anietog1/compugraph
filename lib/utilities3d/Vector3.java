package utilities3d;

public final class Vector3 {
    public final double x, y, z;

    public Vector3(double x, double y, double z, double w) {
        this.x = x / w;
        this.y = y / w;
        this.z = z / w;
    }

    public Vector3(double x, double y, double z) {
        this(x, y, z, 1);
    }

    public static Vector3 zero() {
        return new Vector3(0, 0, 0);
    }

    public static Vector3 x() {
        return new Vector3(1, 0, 0);
    }

    public static Vector3 y() {
        return new Vector3(0, 1, 0);
    }

    public static Vector3 z() {
        return new Vector3(0, 0, 1);
    }

    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }

    public Vector3 normalized() {
        double mag = magnitude();
        return new Vector3(x / mag, y / mag, z / mag);
    }

    public Vector3 plus(Vector3 p) {
        return new Vector3(x + p.x, y + p.y, z + p.z);
    }

    public Vector3 times(double n) {
        return new Vector3(x * n, y * n, z * n);
    }

    public double dot(Vector3 p) {
        return x * p.x + y * p.y + z * p.z;
    }

    public Vector3 cross(Vector3 p) {
        double tmpX = y * p.z - z * p.y;
        double tmpY = x * p.z - z * p.x;
        double tmpZ = x * p.y - y * p.x;

        return new Vector3(tmpX, tmpY, tmpZ);
    }

    @Override
    public String toString() {
        return "Vector3{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
