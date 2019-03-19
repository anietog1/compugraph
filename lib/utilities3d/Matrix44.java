package utilities3d;

import java.util.Arrays;

public class Matrix44 {
    public final double matrix[][];

    public Matrix44(double matrix[][]) {
        this.matrix = matrix;
    }

    public static Matrix44 zero() {
        double matrix[][] = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        return new Matrix44(matrix);
    }

    public static Matrix44 identity() {
        double matrix[][] = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };

        return new Matrix44(matrix);
    }

    public Matrix44 plus(Matrix44 m) {
        Matrix44 result = Matrix44.zero();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                result.matrix[i][j] = matrix[i][j] + m.matrix[i][j];
            }
        }

        return result;
    }

    public Vector3 times(Vector3 p) {
        double x = matrix[0][0] * p.x + matrix[0][1] * p.y + matrix[0][2] * p.z + matrix[0][3];
        double y = matrix[1][0] * p.x + matrix[1][1] * p.y + matrix[1][2] * p.z + matrix[1][3];
        double z = matrix[2][0] * p.x + matrix[2][1] * p.y + matrix[2][2] * p.z + matrix[2][3];
        double w = matrix[3][0] * p.x + matrix[3][1] * p.y + matrix[3][2] * p.z + matrix[3][3];

        return new Vector3(x, y, z, w);
    }

    public Matrix44 times(Matrix44 m) {
        Matrix44 result = Matrix44.zero();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    result.matrix[i][j] += matrix[i][k] * m.matrix[k][j];
                }
            }
        }

        return result;
    }

    public Matrix44 times(double n) {
        Matrix44 result = Matrix44.zero();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                result.matrix[i][j] = matrix[i][j] * n;
            }
        }

        return result;
    }

    public static Matrix44 translate(double dx, double dy, double dz) {
        double matrix[][] = {
            {1, 0, 0, dx},
            {0, 1, 0, dy},
            {0, 0, 1, dz},
            {0, 0, 0, 1}
        };

        return new Matrix44(matrix);
    }

    public static Matrix44 scale(double sx, double sy, double sz) {
        double matrix[][] = {
            {sx, 0, 0, 0},
            {0, sy, 0, 0},
            {0, 0, sz, 0},
            {0, 0, 0, 1}
        };

        return new Matrix44(matrix);
    }

    public static Matrix44 rotateX(double dtheta) {
        double matrix[][] = {
            {1, 0, 0, 0},
            {0, Math.cos(dtheta), -Math.sin(dtheta), 0},
            {0, Math.sin(dtheta), Math.cos(dtheta), 0},
            {0, 0, 0, 1}
        };

        return new Matrix44(matrix);
    }

    public static Matrix44 rotateY(double dtheta) {
        double matrix[][] = {
            {Math.cos(dtheta), 0, Math.sin(dtheta), 0},
            {0, 1, 0, 0},
            {-Math.sin(dtheta), 0, Math.cos(dtheta), 0},
            {0, 0, 0, 1}
        };

        return new Matrix44(matrix);
    }

    public static Matrix44 rotateZ(double dtheta) {
        double matrix[][] = {
            {Math.cos(dtheta), -Math.sin(dtheta), 0, 0},
            {Math.sin(dtheta), Math.cos(dtheta), 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };

        return new Matrix44(matrix);
    }

    public static Matrix44 perspective(double d) {
        double matrix[][] = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 1 / d, 0}
        };

        return new Matrix44(matrix);
    }

    public static Matrix44 lookAt(Vector3 from, Vector3 to, Vector3 orientation) {
        Vector3 n = to.plus(from.times(-1)).normalized();
        Vector3 u = orientation.cross(n).normalized();
        Vector3 v = n.cross(u);

        double matrix[][] = {
            {u.x, u.y, u.z, -u.dot(from)},
            {v.x, v.y, v.z, -v.dot(from)},
            {n.x, n.y, n.z, -n.dot(from)},
            {0, 0, 0, 1}
        };

        return new Matrix44(matrix);
    }

    @Override
    public String toString() {
        return "Matrix44{" + "matrix=" + Arrays.deepToString(matrix) + '}';
    }
}
