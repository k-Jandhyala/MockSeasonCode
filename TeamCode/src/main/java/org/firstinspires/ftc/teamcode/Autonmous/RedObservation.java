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


@Autonomous()
public class RedObservation extends LinearOpMode {
    public IntoTheDeepRobot robot;
    public Encoder par;

    @Override
    public void runOpMode() throws InterruptedException {
//        robot = new IntoTheDeepRobot(hardwareMap, new Pose2d(new Vector2d(0, 0), 0));
//
//        robot.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        robot.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        robot.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        robot.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        waitForStart();
//
//        par = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "leftBack")));
//        int initialTick = par.getPositionAndVelocity().rawPosition;
//        int currentTick = 0;
//        telemetry.addData("init:", initialTick);
//        telemetry.update();
//
//        MecanumDrive drive = new MecanumDrive(hardwareMap, robot.pose);
//
//        Trajectory myTrajectory = drive.trajectoryBuilder(new Pose2d())
//                .strafeRight(10)
//                .forward(5)
//                .build();
//
//        waitForStart();
//
//        if(isStopRequested()) return;
//
//        drive.followTrajectory(myTrajectory);
//
//
//            currentTick = par.getPositionAndVelocity().rawPosition - initialTick;
//
//            telemetry.addData("Encoder Value: ", currentTick);
//            telemetry.update();
//        }
//
//        robot.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        robot.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        robot.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        robot.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
   }
//

}