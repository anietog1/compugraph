package utilities2d;

public class Bezier2 {
    public final Vector2 points[];

    public Bezier2(Vector2 points[]) {
        this.points = points;
    }

    Vector2 P(double u) {
        Vector2 result = new Vector2(0, 0);

        int n = points.length;
        for (int k = 0; k < n; ++k) {
            double BEZknu = BEZ(k, n - 1, u);
            result = result.plus(points[k].times(BEZknu));
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
