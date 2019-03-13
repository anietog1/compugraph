package utilities2d;

import java.util.Arrays;

public class Matrix3x3 {
    public double matrix[][];

    public Matrix3x3(double matrix[][]) {
        this.matrix = new double[3][3];

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    public Matrix3x3(Matrix3x3 m) {
        this(m.matrix);
    }

    public static Matrix3x3 zero() {
        double matrix[][] = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        return new Matrix3x3(matrix);
    }

    public static Matrix3x3 identity() {
        double matrix[][] = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        return new Matrix3x3(matrix);
    }

    public Point2D multiply(Point2D p) {
        double x = matrix[0][0] * p.x + matrix[0][1] * p.y + matrix[0][2];
        double y = matrix[1][0] * p.x + matrix[1][1] * p.y + matrix[1][2];
        double w = matrix[3][0] * p.x + matrix[3][1] * p.y + matrix[3][2];

        return new Point2D(x, y, w);
    }

    public Matrix3x3 multiply(Matrix3x3 m) {
        this.matrix = this.multiplied(m).matrix;
        return this;
    }

    public Matrix3x3 multiplied(Matrix3x3 m) {
        Matrix3x3 result = Matrix3x3.zero();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    result.matrix[i][j] += matrix[i][k] * m.matrix[k][j];
                }
            }
        }

        return result;
    }

    public Matrix3x3 add(Matrix3x3 m) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                matrix[i][j] += m.matrix[i][j];
            }
        }

        return this;
    }

    public Matrix3x3 added(Matrix3x3 m) {
        return new Matrix3x3(this).add(m);
    }

    public Matrix3x3 multiply(double x) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                matrix[i][j] *= x;
            }
        }

        return this;
    }

    public Matrix3x3 multiplied(double x) {
        return new Matrix3x3(this).multiply(x);
    }

    @Override
    public String toString() {
        return "Matrix{" + "matrix=" + Arrays.deepToString(matrix) + '}';
    }
}
