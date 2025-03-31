package org.firstinspires.ftc.teamcode.ButtonMaps.Drive;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ButtonMaps.DPadControl;
import org.firstinspires.ftc.teamcode.ButtonMaps.HolonomicDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;
import org.firstinspires.ftc.teamcode.ComplexRobots.MockSeasonRobot;

@Config
public class OutReachBM extends AbstractButtonMap {
    //TODO: Change back to private final when done with dash
    public static double triggerMultipler = 0.7;
    public static double dpadBumperMultiplier = 0.5;
    public static double hdMultiplier = 0.55;
    public static double slowStrafeMultiplier = 0.8;
    public static double basePower = .6;

    private boolean buttonPressed = false;
    private boolean combineWithPivotTurn = false;

    private double currentMotorPower;
    private MotorPowers mp;// = new MotorPowers(0);

    @Override
    public void loop(MockSeasonRobot robot, OpMode opMode) {
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

//        Field-Oriented Driving using left joystick
        MotorPowers fodMotorPowers = HolonomicDrive.fieldOrientedDrive(opMode.gamepad1, hdMultiplier);
        if (fodMotorPowers.isNotZero()) {
            mp = fodMotorPowers;
            opMode.telemetry.addLine("FOD Active!");
            opMode.telemetry.update();
        }
//
//        /*
//         * Pivot turn methods
//         */
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
//        MotorPowers triggerMotorPowers;
//        //Forward
//        if (opMode.gamepad1.right_trigger > 0.1) {
//            mp = new MotorPowers(-opMode.gamepad1.right_trigger * triggerMultipler,
//                    -opMode.gamepad1.right_trigger * triggerMultipler,
//                    opMode.gamepad1.right_trigger * triggerMultipler,
//                    opMode.gamepad1.right_trigger * triggerMultipler);
//            opMode.telemetry.addLine("Trigger Right (forward) active!");
//            opMode.telemetry.addData("Trigger Right: ", opMode.gamepad1.right_trigger);
//        }
//        //Backward
//        else if (opMode.gamepad1.left_trigger > 0.1) {
//            //Backward
//            mp = new MotorPowers(opMode.gamepad1.left_trigger * triggerMultipler,
//                    opMode.gamepad1.left_trigger * triggerMultipler,
//                    -opMode.gamepad1.left_trigger * triggerMultipler,
//                    -opMode.gamepad1.left_trigger * triggerMultipler);
//            opMode.telemetry.addLine("Trigger Left (backward) active!");
//            opMode.telemetry.addData("Trigger left: ", opMode.gamepad1.left_trigger);
//        }

        /*
         * Button Y - Complete break
         */
        if (opMode.gamepad1.y) {
            mp = robot.setAllMotorPowers(0);
            opMode.telemetry.addLine("Break!!");
        }
        //Slow Strafe Button
        if (opMode.gamepad1.x) {
            currentMotorPower *= slowStrafeMultiplier;
            opMode.telemetry.addLine("Slow Multiplier Active!");
        }


        opMode.telemetry.update();
        robot.setMotorPowers(mp);
    }
}
