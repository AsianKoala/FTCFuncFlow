package control;
import util.Pose;

import static util.GlobalVars.*;

class StageStartVars {
    public double stageStartX;
    public double stageStartY;
    public double stageStartHeading;
    public Pose stageStartPose;
    public double stageStartTime;

    public void initialize() {
        stageStartX = currentPosition.x;
        stageStartY = currentPosition.y;
        stageStartHeading = currentPosition.heading;
        stageStartPose = new util.Pose(stageStartX, stageStartY, stageStartHeading);
        stageStartTime = System.currentTimeMillis();
    }
}
