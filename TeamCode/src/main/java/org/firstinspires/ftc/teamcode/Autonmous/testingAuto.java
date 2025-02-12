package org.firstinspires.ftc.teamcode.Autonmous;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;



import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.util.Vector;

@Autonomous(name = "testingAuto")
public class testingAuto extends LinearOpMode {

    IntoTheDeepRobot robot;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.update();
        // INITIALIZATION
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(new Vector2d(0,0), 0));
        waitForStart();
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-28,0), Math.PI/180).build()
        );
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(-5,0)).build()
        );
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-55,42.5), -1*Math.PI).build()
        );
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-60,60), -0.8*Math.PI).build()
        );
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-8,70), -1*Math.PI).build()
        );
//        sleep(750);
//        Actions.runBlocking(
//                robot.actionBuilder(robot.pose).strafeToConstantHeading(new Vector2d(-24,0)).build()
//        );
//        sleep(750);
//        Actions.runBlocking(
//                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(24,0)).build()
//        );
//        sleep(750);
//        Actions.runBlocking(
//                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(0,2),0.5*Math.PI).build()
//        );
//        sleep(750);
//        Actions.runBlocking(
//                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(32,0), Math.PI).build()
//        );
//        robot.specimenClaw.setPosition(0.5);

    }
}
