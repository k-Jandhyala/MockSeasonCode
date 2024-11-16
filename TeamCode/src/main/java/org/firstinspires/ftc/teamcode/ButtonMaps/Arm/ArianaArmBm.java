package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

public class ArianaArmBm extends AbstractButtonMap {
    public static double intakePower = 0.5;
    public static double linearSlidesDownMultiplier = 0.35;
    public static double linearSlidesUpMultiplier = 0.5;

    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private double intakeOutTime = 0;

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        //Linear Slides (on triggers)
        //Copy+Paste from last year lol
        if (opMode.gamepad2.left_trigger > 0.1) {
            if (robot.bucketMotor1.getCurrentPosition() < -5 || robot.bucketMotor2.getCurrentPosition() < -5) {
                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
                robot.bucketMotor1.setPower(0);
                robot.bucketMotor2.setPower(0);
            } else {
                opMode.telemetry.addData("LS Direction", "DOWN");
                robot.bucketMotor1.setPower(-linearSlidesDownMultiplier * opMode.gamepad2.left_trigger);
                robot.bucketMotor2.setPower(-linearSlidesDownMultiplier * opMode.gamepad2.left_trigger);
            }
        } else if (opMode.gamepad2.right_trigger > 0.1) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.bucketMotor1.setPower(linearSlidesUpMultiplier * opMode.gamepad2.right_trigger);
            robot.bucketMotor2.setPower(linearSlidesUpMultiplier * opMode.gamepad2.right_trigger);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF");
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor2.setPower(0);
        }

        if(opMode.gamepad2.a){
            //TODO: Implement Magic Numbers and test Elbow toggle
        }

        if(opMode.gamepad2.b){
            //TODO: Test Magic numbers to toggle exted the horizantal slides
        }

        if(opMode.gamepad2.x){
            //TODO: specimen toggle
        }

    }
}