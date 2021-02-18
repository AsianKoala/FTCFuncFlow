package main.control;

public class Stages {
    public interface basicStage {
        String name();
        boolean finishState();
        Functions.function startFunction();
        Functions.function mainFunction();
        Functions.function endFunction();
        StageStartVars AUTO_START_VARS = new StageStartVars();
    }

}
