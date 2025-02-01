package org.firstinspires.ftc.teamcode.ButtonMaps;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@TeleOp()
public class NoamHolonomicDrive extends LinearOpMode {
    IntoTheDeepRobot robot;

    public void runOpMode() throws InterruptedException {
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(0,0,0));

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            // Read joystick values
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;

            double denominator = Math.max(Math.abs(y) + Math.abs(x), 1);

            double frontLeftPower = (y + x) / denominator;
            double backLeftPower = (y - x) / denominator;
            double frontRightPower = (y - x) / denominator;
            double backRightPower = (y + x) / denominator;

            robot.setMotorPower(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
        }
    }

}
