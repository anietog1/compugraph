package utilities3d;

public class Bezier3 {
    public final Vector3[][] points;

    public Bezier3(Vector3 points[][]) {
        this.points = points;
    }

    Vector3 P(double u, double v) {
        Vector3 result = Vector3.zero();

        int m = points.length;
        for (int j = 0; j < m; ++j) {
            int n = points[j].length;
            for (int k = 0; k < n; ++k) {
                double BEZjmu = BEZ(j, m - 1, u);
                double BEZknv = BEZ(k, n - 1, v);
                result = result.plus(points[j][k].times(BEZjmu * BEZknv));
            }
        }

        return result;
    }

    double BEZ(int k, int n, double u) {
        return C(n, k) * Math.pow(u, k) * Math.pow(1 - u, n - k);
    }

    int C(int n, int k) {
        return fact(n) / (fact(k) * fact(n - k));
    }

    int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }
}
