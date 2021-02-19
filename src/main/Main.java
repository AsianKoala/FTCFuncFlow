package main;

import static main.util.GlobalVars.*;
import main.control.Results;
import main.control.Stages;
import main.control.StateMachine;
import main.util.Pose;
import main.util.Util;

import java.util.ArrayList;

public class Main {
    public static final int TIME_BETWEEN_LOOPS = 500;

    public static StateMachine stateMachine;

    public static void waitForLoop() {
        try {
            Thread.sleep(TIME_BETWEEN_LOOPS);
        } catch (Exception ignored) {}
    }


    public static void main(String[] args) {
        currentPosition = new Pose(100,100,Math.toRadians(30));
        stateMachine = new StateMachine();
        stateMachine.addStage(new Stages.basicStage() {
            @Override
            public String name() {
                return "hardware counting up 10";
            }


            @Override
            public void startFunction() {
                System.out.println("started stage: " + name());
            }

            @Override
            public Results.baseResult mainFunction() {
                currentPosition.x++;
                currentPosition.y++;
                System.out.println("currxy: " + currentPosition.x + ", " + currentPosition.y);
                return new Results.movementResult(110, 110, Math.toRadians(30), 0.5, 0.5);
            }

            @Override
            public void endFunction() {
                System.out.println("completed stage: " + name());
            }
        });

        stateMachine.addStage(new Stages.basicStage() {

            @Override
            public String name() {
                return "curvePoint iteration";
            }

            @Override
            public void startFunction() {
                System.out.println("\n stage started");
            }

            @Override
            public Results.baseResult mainFunction() {
                ArrayList<curvepoint> allPoints = new ArrayList<>();
                allPoints.add(new curvepoint(115, 115, new simplefunction() {
                    @Override
                    void method() {
                        System.out.println("simple function");
                    }
                }));
                allPoints.add(new curvepoint(120, 120, new simplefunction() {
                    @Override
                    void method() {
                        System.out.println("2nd function");
                    }
                }));
                allPoints.add(new curvepoint(125, 125, new simplefunction() {
                    @Override
                    void method() {
                        System.out.println("3rd function");
                    }
                }));
                allPoints.add(new curvepoint(130, 130, new simplefunction() {
                    @Override
                    void method() {
                        System.out.println("4th function");
                    }
                }));

                return followCurve(allPoints);
            }

            @Override
            public void endFunction() {
                System.out.println("state machine has finished");
            }
        });

        while(stateMachine.running()) {
            waitForLoop();
            stateMachine.loop();
        }
    }

    static curvepoint getFollowPoint(ArrayList<curvepoint> allPoints) {
        curvepoint followMe = new curvepoint(allPoints.get(0));
        double closest = 10000000;
        for(curvepoint c : allPoints) {
            if(Math.hypot(currentPosition.x - c.x, currentPosition.y - c.y) < closest) {
                followMe = new curvepoint(c);
            }
        }
        return followMe;
    }

    static Results.movementResult followCurve(ArrayList<curvepoint> allPoints) {
        curvepoint followme = getFollowPoint(allPoints);
        System.out.println("current follow point: " + followme.toString());
        System.out.println("current cords: " + currentPosition.x + ", " + currentPosition.y);
        currentPosition.x += Util.clip(followme.x - currentPosition.x, -1, 1);
        currentPosition.y += Util.clip(followme.x - currentPosition.x, -1, 1);
        return new Results.movementResult(allPoints.get(allPoints.size()-1).x, allPoints.get(allPoints.size()-1).y, Math.toRadians(30), 0.5, Math.toRadians(3));
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

    public String toString() {
        return "(" + x + ", " + y + ")";
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
