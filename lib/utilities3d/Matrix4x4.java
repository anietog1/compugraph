package utilities3d;

import java.util.Arrays;

public class Matrix4x4 {
    public double matrix[][];

    public Matrix4x4(double matrix[][]) {
        this.matrix = new double[4][4];

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public Matrix4x4(Matrix4x4 m) {
        this(m.matrix);
    }

    public static Matrix4x4 zero() {
        double matrix[][] = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };

        return new Matrix4x4(matrix);
    }

    public static Matrix4x4 identity() {
        double matrix[][] = {
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
        };

        return new Matrix4x4(matrix);
    }

    public Point3D multiply(Point3D p) {
        double x = matrix[0][0] * p.x + matrix[0][1] * p.y + matrix[0][2] * p.z + matrix[0][3];
        double y = matrix[1][0] * p.x + matrix[1][1] * p.y + matrix[1][2] * p.z + matrix[1][3];
        double z = matrix[2][0] * p.x + matrix[2][1] * p.y + matrix[2][2] * p.z + matrix[2][3];
        double w = matrix[3][0] * p.x + matrix[3][1] * p.y + matrix[3][2] * p.z + matrix[3][3];

        return new Point3D(x, y, z, w);
    }

    public Matrix4x4 multiply(Matrix4x4 m) {
        this.matrix = this.multiplied(m).matrix;
        return this;
    }

    public Matrix4x4 multiplied(Matrix4x4 m) {
        Matrix4x4 result = Matrix4x4.zero();

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    result.matrix[i][j] += matrix[i][k] * m.matrix[k][j];
                }
            }
        }

        return result;
    }

    public Matrix4x4 add(Matrix4x4 m) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                matrix[i][j] += m.matrix[i][j];
            }
        }

        return this;
    }

    public Matrix4x4 added(Matrix4x4 m) {
        return new Matrix4x4(this).add(m);
    }

    public Matrix4x4 multiply(double x) {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                matrix[i][j] *= x;
            }
        }

        return this;
    }

    public Matrix4x4 multiplied(double x) {
        return new Matrix4x4(this).multiply(x);
    }

    @Override
    public String toString() {
        return "Matrix{" + "matrix=" + Arrays.deepToString(matrix) + '}';
    }
}
