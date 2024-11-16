package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.Arm.ArianaArmBm;
import org.firstinspires.ftc.teamcode.ButtonMaps.Drive.CommonDriveBM;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@TeleOp(name="Slides Test TeleOp")
public class SlidesTest extends OpMode {
    //Global Variables
    IntoTheDeepRobot robot;

    //Button Maps
    AbstractButtonMap buttonMap;
    AbstractButtonMap slidesButtonMap;

    @Override
    public void init() {
        telemetry.addLine("Initializing, please wait...");
        telemetry.update();
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(0,0,0));
        buttonMap = new CommonDriveBM();
        slidesButtonMap = new ArianaArmBm();
        telemetry.addLine("Ready.");
        telemetry.update();
    }

    @Override
    public void loop() {
        buttonMap.loop(robot, this);
        slidesButtonMap.loop(robot, this);
        telemetry.update();
    }
}
