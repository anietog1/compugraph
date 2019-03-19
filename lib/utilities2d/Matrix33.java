package utilities2d;

import java.util.Arrays;

public class Matrix33 {
    public final double matrix[][];

    public Matrix33(double matrix[][]) {
        this.matrix = matrix;
    }

    public static Matrix33 zero() {
        double matrix[][] = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        return new Matrix33(matrix);
    }

    public static Matrix33 identity() {
        double matrix[][] = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        return new Matrix33(matrix);
    }

    public Matrix33 plus(Matrix33 m) {
        Matrix33 result = Matrix33.zero();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                result.matrix[i][j] = matrix[i][j] + m.matrix[i][j];
            }
        }

        return result;
    }

    public Vector2 times(Vector2 p) {
        double x = matrix[0][0] * p.x + matrix[0][1] * p.y + matrix[0][2];
        double y = matrix[1][0] * p.x + matrix[1][1] * p.y + matrix[1][2];
        double w = matrix[2][0] * p.x + matrix[2][1] * p.y + matrix[2][2];

        return new Vector2(x, y, w);
    }

    public Matrix33 times(Matrix33 m) {
        Matrix33 result = Matrix33.zero();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                for (int k = 0; k < 3; ++k) {
                    result.matrix[i][j] += matrix[i][k] * m.matrix[k][j];
                }
            }
        }

        return result;
    }

    public Matrix33 times(double n) {
        Matrix33 result = Matrix33.zero();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                result.matrix[i][j] = matrix[i][j] * n;
            }
        }

        return result;
    }

    public static Matrix33 translate(double dx, double dy) {
        double matrix[][] = {
            {1, 0, dx},
            {0, 1, dy},
            {0, 0, 1}
        };

        return new Matrix33(matrix);
    }

    public static Matrix33 scale(double sx, double sy) {
        double matrix[][] = {
            {sx, 0, 0},
            {0, sy, 0},
            {0, 0, 1}
        };

        return new Matrix33(matrix);
    }

    public static Matrix33 rotate(double theta) {
        double matrix[][] = {
            {Math.cos(dtheta), -Math.sin(dtheta), 0},
            {Math.sin(dtheta), Math.cos(dtheta), 0},
            {0, 0, 1}
        };

        return new Matrix33(matrix);
    }

    @Override
    public String toString() {
        return "Matrix33{" + "matrix=" + Arrays.deepToString(matrix) + '}';
    }
}
