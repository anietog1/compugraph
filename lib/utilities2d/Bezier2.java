package utilities2d;

import utilities.Bezier;

public class Bezier2 extends Bezier {
    public final Vector2 points[];

    public Bezier2(Vector2 controlPoints[]) {
        points = controlPoints;
    }

    Vector2 P(double u) {
        Vector2 result = Vector2.zero();

        int n = points.length;
        for (int k = 0; k < n; ++k) {
            result = result.plus(points[k].times(BEZ(k, n - 1, u)));
        }

        return result;
    }
}
