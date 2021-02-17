package control;

public class Results {
    public static class baseResult {
        public boolean done;
    }

//    public static class movementResult extends baseResult {
//        public double turnDelta_rad;
//        public movementResult(double targetX, double targetY, double targetAngle, double moveThresh, double angleThresh){
//            turnDelta_rad = MathUtil.angleWrap(targetAngle - currentPosition.heading);
//            done = Math.hypot(targetX - currentPosition.x, targetY - currentPosition.y) < moveThresh && MathUtil.subtractAngleBool(currentPosition.heading, targetAngle, angleThresh);
//        }
//    }

    public static class testResult extends baseResult {
        public testResult() {
            done =  Math.random() > 0.5;
        }
    }

    public static class simpleResult extends baseResult {
        public simpleResult(double current, double target, double thresh)  {
            done = Math.abs(current - target) < thresh;
        }
    }
}
