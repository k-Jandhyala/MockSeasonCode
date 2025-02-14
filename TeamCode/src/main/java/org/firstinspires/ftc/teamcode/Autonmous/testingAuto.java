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
    int bucketMotorsAvgPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.update();
        // INITIALIZATION
        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(new Vector2d(0,0), 0));
        bucketMotorsAvgPosition = (robot.bucketMotor1.getCurrentPosition() + robot.bucketMotor2.getCurrentPosition())/2;
        waitForStart();
        robot.specimenClaw.setPosition(.5);
        sleep(750);
        // go forward to sub
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(-24)).build()
        );
        if (bucketMotorsAvgPosition<1000) {
            robot.bucketMotor1.setPower(.5);
            robot.bucketMotor2.setPower(-.5);
        } else {
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor2.setPower(0);
        }
        sleep(1000);
        if (bucketMotorsAvgPosition>200) {
            robot.bucketMotor1.setPower(-.5);
            robot.bucketMotor2.setPower(.5);
        } else {
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor2.setPower(0);
        }
        // back up from sub
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeTo(new Vector2d(-5,0)).build()
        );
        // big donut around the sub to the preset samples
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-55,70), -1*Math.PI).build()
        );
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-60,85), -0.8*Math.PI).build()
        );
        sleep(750);
        Actions.runBlocking(
                //add more x and maybe y
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-12,95), -1*Math.PI).build()
        );
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-60,80), -0.8*Math.PI).build()
        );
        sleep(750);
        Actions.runBlocking(
                robot.actionBuilder(robot.pose).strafeToLinearHeading(new Vector2d(-8,85), -1*Math.PI).build()
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
