import control.StateMachine;
import util.GlobalVars;
import util.Pose;

public class Main {
    public static final int TIME_BETWEEN_LOOPS = 500;

    public static StateMachine stateMachine;
    public static void main(String[] args) {
        init();
        while(stateMachine.running()) {
            stateMachine.loop();
            System.out.println("Current stage: " + stateMachine.currStage.name());
            waitForLoop();
        }
    }

    public static void init() {
        GlobalVars.currentPosition = new Pose(0, 0, 0);

        stateMachine = new StateMachine();
    }

    public static void waitForLoop() {
        try {
            Thread.sleep(TIME_BETWEEN_LOOPS);
        } catch (Exception ignored) {

        }
    }
}
