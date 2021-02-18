package main;

import main.control.Functions;
import main.control.Stages;
import main.control.StateMachine;
import main.robot.Robot;
import main.util.GlobalVars;
import main.util.Pose;

import java.util.ArrayList;

public class Main {
    public static final int TIME_BETWEEN_LOOPS = 500;

    public static StateMachine stateMachine;
    public static Robot robot;

    public static void waitForLoop() {
        try {
            Thread.sleep(TIME_BETWEEN_LOOPS);
        } catch (Exception ignored) {}
    }


    public static void main(String[] args) {
        stateMachine = new StateMachine();
        int i=0;
        stateMachine.addStage(new Stages.basicStage() {
            @Override
            public String name() {
                return "hardware counting up i";
            }

            @Override
            public boolean finishState() {
                return AUTO_START_VARS.stageStartX == 10;
            }

            @Override
            public void startFunction() {
                
            }

            @Override
            public void mainFunction() {

            }

            @Override
            public void endFunction() {

            }


        });

        double marker = System.currentTimeMillis();
        while(stateMachine.running()) {
            waitForLoop();
            stateMachine.loop();
        }
    }

    static curvepoint getFollowPoint(curvepoint c) {
        return new curvepoint(c.x+1000, c.y+1000, c.function);
    }

}


class curvepoint {
    double x, y;
    function function;
    curvepoint(double x, double y, function function) {
        this.x = x;
        this.y = y;
        this.function = function;
    }

    curvepoint(curvepoint c) {
        this(c.x,c.y,c.function);
    }
}

abstract class function {
    abstract void method();
}

abstract class hardwarefunction extends function {
    @Override
    abstract void method();
}

abstract class simplefunction extends function {
    @Override
    abstract void method();
}
