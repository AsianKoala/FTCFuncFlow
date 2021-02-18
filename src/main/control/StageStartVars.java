package main.control;
import main.util.Pose;

import static main.util.GlobalVars.*;

public class StageStartVars {
    public double stageStartX;
    public double stageStartY;
    public double stageStartHeading;
    public Pose stageStartPose;
    public double stageStartTime;

    public void initialize() {
        stageStartX = 0;
        stageStartY = currentPosition.y;
        stageStartHeading = currentPosition.heading;
        stageStartPose = new main.util.Pose(stageStartX, stageStartY, stageStartHeading);
        stageStartTime = System.currentTimeMillis();
    }
}
