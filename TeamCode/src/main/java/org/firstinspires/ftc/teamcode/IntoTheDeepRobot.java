package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.*;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.HolonomicController;
import com.acmerobotics.roadrunner.MecanumKinematics;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.MotorFeedforward;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.TimeTrajectory;
import com.acmerobotics.roadrunner.TimeTurn;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TurnConstraints;
import com.acmerobotics.roadrunner.Twist2dDual;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.DownsampledWriter;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.FlightRecorder;
import com.acmerobotics.roadrunner.ftc.LazyImu;
import com.acmerobotics.roadrunner.ftc.LynxFirmware;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.messages.DriveCommandMessage;
import org.firstinspires.ftc.teamcode.messages.MecanumCommandMessage;
import org.firstinspires.ftc.teamcode.messages.MecanumLocalizerInputsMessage;
import org.firstinspires.ftc.teamcode.messages.PoseMessage;

import java.lang.Math;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntoTheDeepRobot extends MecanumDrive {
    public final DcMotorEx linerSlideMotor;
    public final DcMotorEx bucketMotor1;
    public final DcMotorEx bucketMotor2;
    public final Servo brushServo1;
    public final Servo brushServo2;

    public IntoTheDeepRobot(HardwareMap hardwareMap, Pose2d pose, OpMode opmode) {
        super(hardwareMap, pose, opMode);
        //Liner Slide Motor
        linerSlideMotor = hardwareMap.get(DcMotorEx.class, "LinerSlideMotor");

        //Setup
        linerSlideMotor.setDirection(DcMotor.Direction.REVERSE);
        linerSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linerSlideMotor.setTargetPositionTolerance(15);
        linerSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linerSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Bucket Motor
        bucketMotor1 = hardwareMap.get(DcMotorEx.class, "LinerSlideMotor");

        bucketMotor2 = hardwareMap.get(DcMotorEx.class, "LinerSlideMotor");

        //Setup
        bucketMotor1.setDirection(DcMotor.Direction.REVERSE);
        bucketMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucketMotor1.setTargetPositionTolerance(15);
        bucketMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bucketMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bucketMotor2.setDirection(DcMotor.Direction.REVERSE);
        bucketMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucketMotor2.setTargetPositionTolerance(15);
        bucketMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bucketMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Brush Servos
        brushServo1 = hardwareMap.get(Servo.class, "brushServo1");
        brushServo2 = hardwareMap.get(Servo.class, "brushServo2");
    }
}
