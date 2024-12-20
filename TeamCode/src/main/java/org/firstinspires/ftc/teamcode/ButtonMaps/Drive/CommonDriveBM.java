package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.LazyImu;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.DPadControl;
import org.firstinspires.ftc.teamcode.ButtonMaps.FieldOrientedDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class CommonDriveBM extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    public static double triggerMultipler = 1;
    public static double dpadBumperMultiplier = 0.65;
    public static double fodMultiplier = 0.85;
    public static double slowStrafeMultiplier = .8;
    public static double basePower = .9;

    private boolean buttonPressed = false;
    private boolean combineWithPivotTurn = false;

    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        mp = new MotorPowers(0);
        currentMotorPower = basePower;

//        if(opMode.gamepad1.a){
//            // extend climb assistants
//            return;
//        }


        //Dpad strafe using dpad
        MotorPowers dpadMotorPowers = DPadControl.dpadStrafe(opMode.gamepad1, currentMotorPower);
        if(dpadMotorPowers.isNotZero()){
            mp = dpadMotorPowers;
            opMode.telemetry.addLine("DPad Active!");
            opMode.telemetry.update();
        }

        //Field-Oriented Driving using left joystick
        MotorPowers fodMotorPowers = FieldOrientedDrive.fieldOrientedDrive(opMode.gamepad1, robot.lazyImu.get(), opMode.gamepad1.b ? fodMultiplier*slowStrafeMultiplier : fodMultiplier);
        if (fodMotorPowers.isNotZero()) {
            mp = fodMotorPowers;
            opMode.telemetry.addLine("FOD Active!");
            opMode.telemetry.update();
        }

        /*
         * Pivot turn methods
         */
//        //Pivot Turn using joystick
//        if(Math.abs(opMode.gamepad1.right_stick_x) > 0.1){
//            MotorPowers joystickPivotTurnMotorPowers = robot.pivotTurn(currentMotorPower*(Math.abs(opMode.gamepad1.right_stick_x)), opMode.gamepad1.right_stick_x > 0.1, opMode.gamepad1.right_stick_x < -0.1);
//            mp = joystickPivotTurnMotorPowers;
//        }

        //Pivot Turn Using bumpers
        if(opMode.gamepad1.right_bumper || opMode.gamepad1.left_bumper){
            MotorPowers bumperPivotTurnMotorPowers = robot.pivotTurn(currentMotorPower, opMode.gamepad1.right_bumper, opMode.gamepad1.left_bumper);
            opMode.telemetry.addLine("Bumper Pivot Turn Active!");
            mp = bumperPivotTurnMotorPowers;
        }

        /*
         * Normal Drive
         */
        MotorPowers triggerMotorPowers;
        //Forward
        if (opMode.gamepad1.right_trigger > 0.1) {
            mp = new MotorPowers(-opMode.gamepad1.right_trigger * triggerMultipler,
                    -opMode.gamepad1.right_trigger * triggerMultipler,
                    opMode.gamepad1.right_trigger * triggerMultipler,
                    opMode.gamepad1.right_trigger * triggerMultipler);
            opMode.telemetry.addLine("Trigger Right (forward) active!");
            opMode.telemetry.addData("Trigger Right: ", opMode.gamepad1.right_trigger);
        }
        //Backward
        else if (opMode.gamepad1.left_trigger > 0.1) {
            //Backward
            mp = new MotorPowers(opMode.gamepad1.left_trigger * triggerMultipler,
                    opMode.gamepad1.left_trigger * triggerMultipler,
                    -opMode.gamepad1.left_trigger * triggerMultipler,
                    -opMode.gamepad1.left_trigger * triggerMultipler);
            opMode.telemetry.addLine("Trigger Left (backward) active!");
            opMode.telemetry.addData("Trigger left: ", opMode.gamepad1.left_trigger);
        }

        /*
         * Button Y - Complete break
         */
        if (opMode.gamepad1.y) {
            robot.leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            opMode.telemetry.addLine("Break!!");
        }
        //Slow Strafe Button

        //test by putting at beginning
        if (opMode.gamepad1.x) {
            currentMotorPower *= slowStrafeMultiplier;
            opMode.telemetry.addLine("Slow Multiplier Active!");
        }


        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
