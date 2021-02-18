package main.control;

import static main.util.GlobalVars.currentPosition;
import static main.util.Util.angleDiffCheck;
import static main.util.Util.angleWrap;

public class Results {
    public static class baseResult {
        public boolean done;
    }

    public static class simpleResult extends baseResult {
        public simpleResult(double current, double target, double thresh)  {
            done = Math.abs(current - target) < thresh;
        }
    }

    public static class movementResult extends baseResult {
        public double turnDelta_rad;
        public movementResult(double targetX, double targetY, double targetAngle, double moveThresh, double angleThresh){
            turnDelta_rad = angleWrap(targetAngle - currentPosition.heading);
            done = Math.hypot(targetX - currentPosition.x, targetY - currentPosition.y) < moveThresh && angleDiffCheck(currentPosition.heading, targetAngle, angleThresh);
        }
    }



}
