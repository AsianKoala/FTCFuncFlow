package util;

public class Util {
    public static double angleWrap(double angle) {
        while(angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        while(angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        return angle;
    }

    public static double angleSubtraction(double a1, double a2) {
        return angleWrap(a1-a2);
    }

    public static boolean angleDiffCheck(double a1, double a2, double thresh) {
        return Math.abs(angleSubtraction(a1, a2)) < thresh;
    }
}
