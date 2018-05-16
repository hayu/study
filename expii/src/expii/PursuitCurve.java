package expii;

public class PursuitCurve {

    public static void main(String[] args) {
        double c = 0;
        double a = 0;
        double yp = 0;
        double x = 0;

    }

    private double dogY(double c, double ax, double yp0, double x) {
        double rslt = c - 0.5 * ax * (((yp0 + Math.sqrt(yp0 * yp0 + 1)) * Math.pow(1 - x / ax, -9) / -9.0)
                - Math.pow(1 - x / ax, 11) / ((yp0 + Math.sqrt(yp0 * yp0 + 1)) * 11));
        return rslt;
    }

}
