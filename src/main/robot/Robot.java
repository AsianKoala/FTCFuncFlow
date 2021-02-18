package main.robot;
import static main.util.GlobalVars.*;
import static main.util.Util.*;

public class Robot {
    public final double MAX_X_SPEED = 10;
    public final double MAX_Y_SPEED = 10; // dir
    public final double MAX_TURN_SPEED = Math.toRadians(90);

    public void update() {
        currentPosition.x += Math.cos(currentPosition.heading) * movementPowers.x * MAX_X_SPEED;
        currentPosition.y += Math.sin(currentPosition.heading) * movementPowers.y * MAX_Y_SPEED;
        currentPosition.heading = angleWrap(currentPosition.heading + movementPowers.heading * MAX_TURN_SPEED);
    }
}
