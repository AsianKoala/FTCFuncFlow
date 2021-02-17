import control.Stages;
import control.StateMachine;
import robot.CurvePoint;
import robot.Robot;
import util.GlobalVars;
import util.Pose;

import java.util.ArrayList;

public class Main {
    public static final int TIME_BETWEEN_LOOPS = 500;

    public static StateMachine stateMachine;
    public static Robot robot;

    public static void init() {
        GlobalVars.currentPosition = new Pose(0, 0, 0);

        stateMachine = new StateMachine();
    }

    public static void waitForLoop() {
        try {
            Thread.sleep(TIME_BETWEEN_LOOPS);
        } catch (Exception ignored) {}
    }


    public static void main(String[] args) {
//        init();
//        while(stateMachine.running()) {
//
//            stateMachine.loop();
//            System.out.println("Current stage: " + stateMachine.currStage.name());
//            waitForLoop();
//        }

        ArrayList<CurvePoint> allPoints = new ArrayList<>();


    }




}
