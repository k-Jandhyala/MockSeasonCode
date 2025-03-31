package org.firstinspires.ftc.teamcode.ButtonMaps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ComplexRobots.MockSeasonRobot;
//
////public class FieldOrientedDrive {
//    //thanks game manual 0!
//    //https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html
//    public static MotorPowers fieldOrientedDrive(Gamepad gamepad, IMU imu, double maxMotorPower){
//
//        //Provide a deadzone of +-0.1
//        double x = gamepad.left_stick_x > 0.1 || gamepad.left_stick_x < -0.1 ? gamepad.left_stick_x : 0;
//        double y = gamepad.left_stick_y > 0.1 || gamepad.left_stick_y < -0.1 ? -gamepad.left_stick_y : 0;
//        double rotate = gamepad.right_stick_x > 0.1 || gamepad.right_stick_x < -0.1 ? gamepad.right_stick_x : 0;
//        double botHeading = imu.getRobotYawPitchRollAngles().getRoll(AngleUnit.RADIANS);
//        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
//        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
//        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotate), 1);
//        double frontLeftPower = maxMotorPower*((rotY + rotX + rotate) / denominator);
//        double backLeftPower = maxMotorPower*((rotY - rotX + rotate) / denominator);
//        double frontRightPower = maxMotorPower*((rotY - rotX - rotate) / denominator);
//        double backRightPower = maxMotorPower*((rotY + rotX - rotate) / denominator);
////        frontLeftPower *= .5; backLeftPower *= .5; frontRightPower *= .5; backRightPower *= .5;
//        return new MotorPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
//    }


public  class FieldOrientedDrive extends LinearOpMode{
    private double currentMotorPower;
    MockSeasonRobot robot;
    IMU imu;
    {
        imu = hardwareMap.get(IMU.class, "imu");
    }
    public static double slowStrafeMultiplier;

    static {
        slowStrafeMultiplier = .5;
    }

    public FieldOrientedDrive(double currentMotorPower) {
        this.currentMotorPower = currentMotorPower;
    }
    private MotorPowers mp;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        //        //Provide a deadzone of +-0.1
        //        double x = gamepad.left_stick_x > 0.1 || gamepad.left_stick_x < -0.1 ? gamepad.left_stick_x : 0;
        //        double y = (gamepad.left_stick_y > 0.1 || gamepad.left_stick_y < -0.1 ? -gamepad.left_stick_y : 0);
        //        double rotate = gamepad.right_stick_x > 0.1 || gamepad.right_stick_x < -0.1 ? gamepad.right_stick_x : 0;
        //
        //        double botLateral = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        //        double rotY = y * Math.cos(botLateral) + x * Math.sin(botLateral);
        //        double rotX = -y  * Math.sin(botLateral) + x * Math.cos(botLateral);
        ////        double botHeading = imu.getRobotYawPitchRollAngles().getPitch(AngleUnit.RADIANS);
        ////        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        ////        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
        //        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rotate), 1);
        //        double frontLeftPower = -maxMotorPower*((rotY + rotX + rotate) / denominator);
        //        double backLeftPower = maxMotorPower*((rotY - rotX + rotate) / denominator);
        //        double frontRightPower = maxMotorPower*((rotY - rotX - rotate) / denominator);
        //        double backRightPower = -maxMotorPower*((rotY + rotX - rotate) / denominator);
        //
        //        return new MotorPowers(frontLeftPower, -frontRightPower, -backLeftPower, backRightPower);

        // Declare our motors
        // Make sure your ID's match your configuration

        // Reverse the right side motors. This may be wrong for your setup.
        // If your robot moves backwards when commanded to go forwards,
        // reverse the left side instead.
        // See the note about this earlier on this page.

        // Retrieve the IMU from the hardware map
        // Adjust the orientation parameters to match your robot


        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // This button choice was made so that it is hard to hit on accident,
            // it can be freely changed based on preference.
            // The equivalent button is start on Xbox-style controllers.
            if (gamepad1.start) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // Rotate the movement direction counter to the bot's rotation
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;  // Counteract imperfect strafing

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            robot.leftFront.setPower(frontLeftPower);
            robot.leftBack.setPower(backLeftPower);
            robot.rightFront.setPower(frontRightPower);
            robot.rightBack.setPower(backRightPower);
            if (Math.abs(gamepad1.right_stick_x) > 0.1) {
                MotorPowers joystickPivotTurnMotorPowers = robot.pivotTurn(currentMotorPower * (Math.abs(gamepad1.right_stick_x)), gamepad1.right_stick_x > 0.1, gamepad1.right_stick_x < -0.1);
                mp = joystickPivotTurnMotorPowers;
            }

            //Pivot Turn Using bumpers
            if (gamepad1.right_bumper || gamepad1.left_bumper) {
                MotorPowers bumperPivotTurnMotorPowers = robot.pivotTurn(currentMotorPower, gamepad1.right_bumper, gamepad1.left_bumper);
                telemetry.addLine("Bumper Pivot Turn Active!");
                mp = bumperPivotTurnMotorPowers;
            }
            if (gamepad1.y) {
                robot.setAllMotorPowers(0);
                robot.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                robot.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                robot.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                robot.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                telemetry.addLine("Brake!!");
            }
            if (gamepad1.x) {
                mp = new MotorPowers(mp.leftFront * slowStrafeMultiplier,
                        mp.rightFront * slowStrafeMultiplier,
                        mp.leftBack * slowStrafeMultiplier,
                        mp.rightBack * slowStrafeMultiplier);
                telemetry.addLine("Slow Multiplier Active!");
            }
        }



    }

    public MotorPowers getMp() {
        return mp;
    }

    public void setMp(MotorPowers mp) {
        this.mp = mp;
    }
}

