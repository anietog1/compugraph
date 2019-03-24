package utilities;

public abstract class Bezier {
    public double BEZ(int k, int n, double u) {
        return C(n, k) * Math.pow(u, k) * Math.pow(1 - u, n - k);
    }

    public int C(int n, int k) {
        return fact(n) / (fact(k) * fact(n - k));
    }

    public int fact(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }
}
