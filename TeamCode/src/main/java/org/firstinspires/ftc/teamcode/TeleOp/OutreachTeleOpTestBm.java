package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.Arm.OutreachArmBMTestTeleOp;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.OutReachBM;
import org.firstinspires.ftc.teamcode.ComplexRobots.MockSeasonRobot;

@TeleOp(name="OutReach TeleOp")
public class OutreachTeleOpTestBm extends OpMode {
    //Global Variables
    MockSeasonRobot robot;

    //Button Maps
    AbstractButtonMap driveButtonMap;
    AbstractButtonMap slidesButtonMap;

    @Override
    public void init() {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new MockSeasonRobot(hardwareMap, new Pose2d(0,0,0));
        driveButtonMap = new OutReachBM();
        slidesButtonMap = new OutreachArmBMTestTeleOp();
        telemetry.addLine("Ready.");
        telemetry.update();
    }

    @Override
    public void loop() {
        driveButtonMap.loop(robot, this);
        slidesButtonMap.loop(robot, this);
        telemetry.update();
    }
}
