package robot;

import control.Results;

import static util.Util.*;
import static util.GlobalVars.*;

public class MovementController {
    public static Results.movementResult followCurve() { // just gonna be some super basic gotoposition shit cant be bothered to write pure pursuit for this xd
        return null; // todo
    }

    public static Results.movementResult goToPosition(double targetX, double targetY, double angle) { //  literally only for moving to a position
        double distance = Math.hypot(targetX - currentPosition.x, targetY - currentPosition.y);

        double absoluteAngleToTargetPoint = Math.atan2(targetY - currentPosition.y, targetX - currentPosition.x);
        double relativeAngleToTargetPoint = angleWrap(absoluteAngleToTargetPoint - (currentPosition.heading - Math.toRadians(90)));

        double relativeXToPoint = Math.cos(relativeAngleToTargetPoint) * distance;
        double relativeYToPoint = Math.sin(relativeAngleToTargetPoint) * distance;
        double relativeAbsXToPoint = Math.abs(relativeXToPoint);
        double relativeAbsYToPoint = Math.abs(relativeYToPoint);

        double v = relativeAbsXToPoint + relativeAbsYToPoint;
        double movementXPower = relativeXToPoint / v;
        double movementYPower = relativeYToPoint / v;

        movementXPower *= relativeAbsXToPoint / 12;
        movementYPower *= relativeAbsYToPoint / 12;
        double movementX, movementY, movementTurn;


        movementX = clip(movementXPower, -1, 1);
        movementY = clip(movementYPower, -1, 1);



        // turning and smoothing shit
        double relativeTurnAngle = angle - Math.toRadians(90);
        double absolutePointAngle = absoluteAngleToTargetPoint + relativeTurnAngle;
        double relativePointAngle = angleWrap(absolutePointAngle - currentPosition.heading);

        double decelerateAngle = Math.toRadians(40);

        double movementTurnSpeed = (relativePointAngle/decelerateAngle);

        movementTurn = clip(movementTurnSpeed, -1, 1);

        if(distance < 3) {
            movementTurn = 0;
        }



        // smoothing


        //slow down if our point angle is off
        double errorTurnSoScaleDownMovement = clip(1.0-Math.abs(relativePointAngle/Math.toRadians(30)),0.5,1);
        //don't slow down if we aren't trying to turn (distanceToPoint < 10)
        if(Math.abs(movementTurn) < 0.00001){
            errorTurnSoScaleDownMovement = 1;
        }
        movementX *= errorTurnSoScaleDownMovement;
        movementY *= errorTurnSoScaleDownMovement;


        movementPowers.x = movementX;
        movementPowers.y = movementY;
        movementPowers.heading = movementTurn;

        return new Results.movementResult(targetX, targetY, currentPosition.heading + relativeAngleToTargetPoint, 2.5, Math.toRadians(3));
    }
}
