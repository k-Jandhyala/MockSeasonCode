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

@Autonomous(name = "testingSpecimenFirstAuto")
public class testingSpecimenFirst extends LinearOpMode {

    IntoTheDeepRobot robot;
    int bucketMotorsAvgPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.update();
        // INITIALIZATION
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(new Vector2d(0,0), 0));
        bucketMotorsAvgPosition = (robot.bucketMotor1.getCurrentPosition() + robot.bucketMotor2.getCurrentPosition())/2;
        waitForStart();
        robot.specimenClaw.setPosition(.5);
        // go forward to sub
        robot.driveSlidesTo(122,0.5,1);
        //raise slides
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(-34,0)).build()
        );
        sleep(1250);
        //lower slides
        robot.driveSlidesTo(50, 0.7,-1);
        // back up from sub
        sleep(500);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(-2,0)).build()
        );
        //lower slides completely
        robot.driveSlidesTo(-10,0.7,-1);
        //open the claw
        robot.specimenClaw.setPosition(0.8);
        // big donut around the sub to the preset samples
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-65,64.5), -1*Math.PI).build()
        );
        // curve to get closer to sample
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-70,72.5), -0.8*Math.PI).build()
        );
        // move sample to observation zone
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-18,84), -1*Math.PI).build()
        );
        // pick up specimen
        sleep(500);
        robot.specimenClaw.setPosition(0.4);
        sleep(750);
        // small donut to the sub
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-36,-30), -2*Math.PI).build()
        );
        //back up to sub completely
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(-45,-30)).build()
        );
        //raise slides
        robot.driveSlidesTo(150,0.7,1);
        sleep(500);
        robot.specimenClaw.setPosition(0.8);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-18,84), -1*Math.PI).build()
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
