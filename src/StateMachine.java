import java.util.LinkedList;


public class StateMachine {
    public LinkedList<Stages.basicStage> stages;
    public Stages.basicStage currStage;
    public int completedStages;

    public StateMachine() {
        stages = new LinkedList<>();
    }

    public void addStage(Stages.basicStage newStage) {
        stages.add(newStage);
        currStage = stages.getFirst();
    }

    public boolean running() {
        return !stages.isEmpty();
    }

    public void skipStages(int newStageIndex) {
        // convert string to int
        for(int i=0; i<newStageIndex-completedStages; i++)
            stages.removeFirst();
        currStage = stages.getFirst();
    }

    public void loop() {
        if(currStage.finishState()) {
            currStage.endFunction();
            stages.removeFirst();
            completedStages++;
            if(running()) {
                currStage = stages.getFirst();
                currStage.AUTO_START_VARS.initialize();
                currStage.startFunction();
            }
        } else {
            currStage.mainFunction();
        }
    }
}













