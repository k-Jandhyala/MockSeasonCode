package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
    private long sampleServoTime = System.currentTimeMillis();

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

    int bucketMotorsAvgPostiion;

    //    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        bucketMotorsAvgPostiion = (robot.bucketMotor1.getCurrentPosition() + robot.bucketMotor2.getCurrentPosition())/2;

        //Wrist Servo (elbow)
        if (opMode.gamepad2.a && !aIsPressed) {
            robot.elbowServo.setPosition(.8);
            aIsPressed = !aIsPressed;
        } else if (opMode.gamepad2.a && aIsPressed) {
            robot.elbowServo.setPosition(.5);
            aIsPressed = !aIsPressed;
        }
        opMode.telemetry.addData("ES Position: ", robot.elbowServo.getPosition());
        //Bucket Motors (on triggers)
        opMode.telemetry.addData("Bucket Encoder Avg: ", (robot.bucketMotor1.getCurrentPosition() + robot.bucketMotor2.getCurrentPosition())/2);
        if (opMode.gamepad2.left_trigger > 0.1) {
            if(bucketMotorsAvgPostiion < 275) {
                robot.bucketMotor1.setPower(-opMode.gamepad2.left_trigger * linearSlidesUpMultiplier * 1);
                robot.bucketMotor2.setPower(opMode.gamepad2.left_trigger * linearSlidesUpMultiplier * 1);
            } else {
                robot.bucketMotor1.setPower(0.05);
                robot.bucketMotor2.setPower(0.05);
            }
        } else if (opMode.gamepad2.right_trigger > 0.1) {
            if(bucketMotorsAvgPostiion > -10) {
                robot.bucketMotor1.setPower(opMode.gamepad2.right_trigger * linearSlidesDownMultiplier * 1);
                robot.bucketMotor2.setPower(-opMode.gamepad2.right_trigger * linearSlidesDownMultiplier * 1);
            }
        } else {
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor2.setPower(0);
        }
        //Brush Servos
        if (opMode.gamepad2.y && yIsPressed && ((System.currentTimeMillis() - startTime) > timeDelay)) {
            //one servo to spin brush one servo to angle brush - the other other a elbow servo
            robot.brushServo.setPower(-1);
            yIsPressed = !yIsPressed;
            sampleServoTime = System.currentTimeMillis();
        } else if (opMode.gamepad2.y && !yIsPressed && ((System.currentTimeMillis() - sampleServoTime) > timeDelay)) {
            robot.brushServo.setPower(1);
            yIsPressed = !yIsPressed;
            startTime = System.currentTimeMillis();
        }
        else {
            robot.brushServo.setPower(0);
        }


//            Horizontal Slides Motor
//        if (opMode.gamepad2.b && !bIsPressed && ((System.currentTimeMillis()- startTime) > timeDelay)) {
//            robot.setMotorTo(robot.horizontalSlideMotor, -10, linearSlidesUpMultiplier);
//            bIsPressed = !bIsPressed;
//            horizontalSlideTime = System.currentTimeMillis();
//        } else if (opMode.gamepad2.b && bIsPressed && ((System.currentTimeMillis()- horizontalSlideTime) > timeDelay)) {
//            robot.setMotorTo(robot.horizontalSlideMotor, -2340, linearSlidesUpMultiplier);
//            bIsPressed = !bIsPressed;
//            startTime = System.currentTimeMillis();
//        } else {
//            robot.horizontalSlideMotor.setPower(0);
//        }
        // if (contact switch is not pressed)
//        if (opMode.gamepad2.b && !bIsPressed) {
//            robot.setMotorTo(robot.horizontalSlideMotor, 0, 1 * linearSlidesUpMultiplier);
//            bIsPressed = !bIsPressed;
//        } else if (/*opMode.gamepad2.left_stick_button*/opMode.gamepad2.b && bIsPressed) {
//            robot.horizontalSlideMotor.setPower(-1 * linearSlidesDownMultiplier);
//            bIsPressed = !bIsPressed;
//        }

        if(opMode.gamepad2.dpad_up) {
            robot.horizontalSlideMotor.setPower(-.5);

        }
         else if(opMode.gamepad2.dpad_down) {
            robot.horizontalSlideMotor.setPower(.5);
        } else {
             robot.horizontalSlideMotor.setPower(0);
        }
//        opMode.telemetry.addLine("Horizontal Motor Encoder: ");
        opMode.telemetry.addData("Horz. Motor Encoder: ", robot.horizontalSlideMotor.getCurrentPosition());

            //Bucket Servo
        if (opMode.gamepad2.left_bumper) {
              robot.bucketServo.setPosition(1);
              robot.specimenClaw.setPosition(0.5);
        } else if (opMode.gamepad2.right_bumper) {
              robot.bucketServo.setPosition(0);
              robot.specimenClaw.setPosition(0.5);
        }

//SpecimenClaw
        if (opMode.gamepad2.x && !xIsPressed && ((System.currentTimeMillis() - startTime) > timeDelay)) {
            robot.specimenClaw.setPosition(0.5);
            xIsPressed = !xIsPressed;
            specimenTime = System.currentTimeMillis();
            opMode.telemetry.addLine("Servo Closed");
        } else if (opMode.gamepad2.x && xIsPressed && ((System.currentTimeMillis() - specimenTime) > timeDelay)) {
            robot.specimenClaw.setPosition(1.1);
            xIsPressed = !xIsPressed;
            opMode.telemetry.addLine("Servo Open");
            startTime = System.currentTimeMillis();
        }

        opMode.telemetry.update();
    }
}