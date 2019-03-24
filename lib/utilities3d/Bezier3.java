package utilities3d;

import utilities.Bezier;

public class Bezier3 extends Bezier {
    public final Vector3 controlPoints[][];

    public Bezier3(Vector3 controlPoints[][]) {
        this.controlPoints = controlPoints;
    }

    Vector3 P(double u, double v) {
        Vector3 result = Vector3.zero();

        int m = controlPoints.length;
        for (int j = 0; j < m; ++j) {
            int n = controlPoints[j].length;
            for (int k = 0; k < n; ++k) {
                double BEZjmu = BEZ(j, m - 1, u);
                double BEZknv = BEZ(k, n - 1, v);
                result = result.plus(controlPoints[j][k].times(BEZjmu * BEZknv));
            }
        }

        return result;
    }
}
