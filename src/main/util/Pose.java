package main.util;

public class Pose extends Point{
    public double heading;
    public Pose(double x, double y, double heading) {
        super(x,y);
        this.heading = heading;
    }
}
