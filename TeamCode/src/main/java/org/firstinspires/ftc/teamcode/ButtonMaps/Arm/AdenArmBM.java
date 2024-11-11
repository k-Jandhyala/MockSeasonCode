package org.firstinspires.ftc.teamcode.ButtonMaps.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ButtonMaps.AbstractButtonMap;
import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;

@Config
public class AndrewArmBM extends AbstractButtonMap {
    public static double slideUpPower = 0.5;
    public static double slideDownPower = 0.35;
    public static double holdPower = 0.04;

    //TODO: Magic Numbers!!!
    private ElapsedTime et = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

    @Override
    public void loop(IntoTheDeepRobot robot, OpMode opMode) {
        /*
            Button Map
            Y - Extend the Linear Slides (DONE)
            A - Detract the Linear Slides (DONE)
            B - Release Pixel (Maybe close as well)
            D-Pad - Release airplane (single press) (DONE)

         */

        /* Linear Slides */
        if (opMode.gamepad2.a) {
            if (robot.linearSlideMotor1.getCurrentPosition() < -5 || robot.linearSlideMotor2.getCurrentPosition() < -5) {
                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
                robot.linearSlideMotor.setPower(.5);
                robot.linearSlideMotor.setPosition(.5);
            } else {
                opMode.telemetry.addData("LS Direction", "DOWN");
                robot.linearSlideMotor.setPower(0);
                robot.linearSlideMotor.setPosition(0);
            }
        } else if (opMode.gamepad2.y) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.brushServo1.setPower(1);
            robot.brushServo2.setPower(1);
        } else {
            opMode.telemetry.addData("LS Direction", "OFF+HOLD");
            //Small amount of power for hold mode
            robot.brushServo1.setPower(0);
            robot.brushServo2.setPower(0);
        }
        
        if (opMode.gamepad2.b) {
        // Toggle Elbow
        }
        
        if (opMode.gamepad2.right_bumper) {
            robot.clawServo.setPower(1);
            robot.clawServo.setPosition(x);
        } else (opMode.gamepad2.left_bumper) {
            robot.clawServo.setPower(0);
            robot.clawServo.setPosition(y);
        }

        if (opMode.gamepad2.right_trigger) {
            robot.bucketMotor1.setPower(.5);
            robot.bucketMotor2.setPower(.5);
            robot.bucketMotor1.setPosition(x);
            robot.bucketMotor2.setPosition(x);
        } else if (opMode.gamepad2.left_trigger) {
            robot.bucketMotor1.setPosition(y);
            robot.bucketMotor2.setPosition(y);
            robot.bucketMotor1.setPower(0);
            robot.bucketMotor.setPower(0);
        }
            
            
        //Plane Servo (dpad)
        //TODO: find position
        if(opMode.gamepad2.dpad_up || opMode.gamepad2.dpad_down || opMode.gamepad2.dpad_left || opMode.gamepad2.dpad_right){
            robot.airplaneServo.setPosition(0);
        }
    }
}
