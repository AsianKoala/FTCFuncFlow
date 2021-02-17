
class StageStartVars {
    public double stageStartX;
    public double stageStartY;
    public double stageStartHeading;
    public double stageStartTime;

    public void initialize() {
//        stageStartX = currentPosition.x;
//        stageStartY = currentPosition.y;
//        stageStartHeading = currentPosition.heading;
//        stageStartPose = new Pose(stageStartX, stageStartY, stageStartHeading);
        stageStartTime = System.currentTimeMillis();
    }
}
