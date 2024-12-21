package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

public class ArianaArmBm extends AbstractButtonMap {
    public static double intakePower = 0.5;
    public static double linearSlidesDownMultiplier = .4;
    public static double linearSlidesUpMultiplier = .4;
    public static double bucketMotor1Multiplier = 0.85;
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private long specimenTime;
    private long startTime = System.currentTimeMillis();
    private long horizontalSlideTime;
    private int timeDelay = 500;
    private double intakeOutTime = 0;

    //Servo Positions
//    double x =
//    double y =
//    double a =
//    double b =
//    double e =
//    double f =

    //Boolean Toggle Memory
    boolean aIsPressed = false;
    boolean yIsPressed = false;
    boolean bIsPressed = false;
    boolean xIsPressed = false;

    //    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        //Wrist Servo (elbow)
        if (opMode.gamepad2.a && !aIsPressed) {
//            robot.elbowServo.setPosition(e);
            aIsPressed = !aIsPressed;
        } else if (opMode.gamepad2.a && aIsPressed) {
//            robot.elbowServo.setPosition(f);
            aIsPressed = !aIsPressed;
        }
        //Bucket Motors (on triggers)      
        if (opMode.gamepad2.left_trigger > 0.1) {
//            if (robot.bucketMotor1.getCurrentPosition() < -5 || robot.bucketMotor2.getCurrentPosition() < -5) {
//                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
//                robot.bucketMotor1.setPower(0);
//                robot.bucketMotor2.setPower(0);
//            } else {
//                opMode.telemetry.addData("LS Direction", "DOWN");
//                robot.bucketMotor1.setPower(opMode.gamepad2.left_trigger);
//                robot.bucketMotor2.setPower(opMode.gamepad2.left_trigger);

            opMode.telemetry.addLine("Down: Code updated");
            robot.bucketMotor1.setPower(-opMode.gamepad2.left_trigger * linearSlidesUpMultiplier * 1);
            robot.bucketMotor2.setPower(opMode.gamepad2.left_trigger * linearSlidesUpMultiplier * 1);
        } else if (opMode.gamepad2.right_trigger > 0.1) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.bucketMotor1.setPower(opMode.gamepad2.right_trigger * linearSlidesDownMultiplier * 1);
            robot.bucketMotor2.setPower(-opMode.gamepad2.right_trigger * linearSlidesDownMultiplier * 1);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF");
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor2.setPower(0);
        }
        //Brush Servos
        if (opMode.gamepad2.y && !yIsPressed) {
            robot.brushServo1.setPosition(1);
            robot.brushServo2.setPosition(1);
            yIsPressed = !yIsPressed;
        } else if (opMode.gamepad2.y && yIsPressed) {
            robot.brushServo1.setPosition(-1);
            robot.brushServo2.setPosition(-1);
            yIsPressed = !yIsPressed;
        }

            //Horizontal Slides Motor
//            if (opMode.gamepad2.b && !bIsPressed) {
//                robot.horizontalSlideMotor.setPower(1 * linearSlidesUpMultiplier);
//                bIsPressed = !bIsPressed;
//                horizontalSlideTime = System.currentTimeMillis();
//            } else if (opMode.gamepad2.b && bIsPressed && ((System.currentTimeMillis()- horizontalSlideTime) > timeDelay)) {
//                robot.horizontalSlideMotor.setPower(-1 * linearSlidesDownMultiplier);
//                bIsPressed = !bIsPressed;
//            } else {
//                robot.horizontalSlideMotor.setPower(0);
//            }
        // if (contact switch is not pressed)
//        if (opMode.gamepad2.b && !bIsPressed) {
//            robot.setMotorTo(robot.horizontalSlideMotor, 0, 1 * linearSlidesUpMultiplier);
//            bIsPressed = !bIsPressed;
//        } else if (/*opMode.gamepad2.left_stick_button*/opMode.gamepad2.b && bIsPressed) {
//            robot.horizontalSlideMotor.setPower(-1 * linearSlidesDownMultiplier);
//            bIsPressed = !bIsPressed;
//        }

        if(opMode.gamepad2.left_stick_button) {
            robot.horizontalSlideMotor.setPower(.5);
        }
        if(opMode.gamepad2.right_stick_button) {
            robot.horizontalSlideMotor.setPower(-.5);
        }
//        opMode.telemetry.addLine("Horizontal Motor Encoder: ");
        opMode.telemetry.addData("Horz. Motor Encoder: ", robot.horizontalSlideMotor.getCurrentPosition());

            //Claw Servo (ROSE)
//        if (opMode.gamepad2.left_bumper) {
//              robot.clawServo.setPower(opMode.gamepad2.left_bumper);
//              robot.clawServo.setPosition(a);
//        } else if (opMode.gamepad2.right_bumper) {
//              robot.clawServo.setPower(-1);
//              robot.clawServo.setPosition(b);
//        }

//SpecimenClaw
        if (opMode.gamepad2.x && !xIsPressed && ((System.currentTimeMillis() - startTime) > timeDelay)) {
            robot.specimenClaw.setPosition(1);
            xIsPressed = !xIsPressed;
            specimenTime = System.currentTimeMillis();
            opMode.telemetry.addLine("Servo Open");
        } else if (opMode.gamepad2.x && xIsPressed && ((System.currentTimeMillis() - specimenTime) > timeDelay)) {
            robot.specimenClaw.setPosition(-1);
            xIsPressed = !xIsPressed;
            opMode.telemetry.addLine("Servo Closed");
            startTime = System.currentTimeMillis();
        }

        opMode.telemetry.update();
    }
}