package main.robot;


import main.control.Functions;
import main.util.Point;


public class CurvePoint {
    public double x;
    public double y;
    public Functions.function function;

    public CurvePoint(double x, double y, Functions.function function) {
        this.x = x;
        this.y = y;
        this.function = function;
    }


    public Point toPoint() {
        return new Point(x, y);
    }

    public void setPoint(Point p) {
        x = p.x;
        y = p.y;
    }


    public static class hardwareCurvePoint extends CurvePoint {
        public hardwareCurvePoint(double x, double y, Functions.hardwareFunction hardwareFunction) {
            super(x, y, hardwareFunction);
        }
    }

    public static class pointToPointCurvePoint extends CurvePoint {
        public pointToPointCurvePoint(double x, double y, Functions.pointToPointFunction pointToPointFunction) {
            super(x, y, pointToPointFunction);
        }
    }
}
