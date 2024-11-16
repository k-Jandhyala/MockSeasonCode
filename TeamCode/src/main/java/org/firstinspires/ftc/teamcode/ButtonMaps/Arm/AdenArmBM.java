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

    // Servo Positions
    double e =
    double f =
    double a =
    double b =
    double x =
    double y =

    //Boolean Toggle Memory
    boolean yIsPressed = false;
    boolean bIsPressed = false;
    boolean aIsPressed = false;

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
        if (opMode.gamepad2.a && aIsPressed) {
            aIsPressed = !aIsPressed;
            if (robot.linearSlideMotor1.getCurrentPosition() < -5 || robot.linearSlideMotor2.getCurrentPosition() < -5) {
                opMode.telemetry.addData("LS Direction", "INHIBIT DOWN");
                robot.linearSlideMotor.setPower(1);
            } else if (opMode.gamepad2.a && aIsPressed) {
                opMode.telemetry.addData("LS Direction", "DOWN");
                robot.linearSlideMotor.setPower(-1);
            }
        } else if {

        }
            // Brush Servos
        if (opMode.gamepad2.y && yIsPressed) {
            opMode.telemetry.addData("LS Direction", "UP");
            robot.brushServo1.setPower(1);
            robot.brushServo2.setPower(1);
            robot.brushServo1.setPosition(a);
            robot.brushServo2.setPosition(a);
            yIsPressed = !yIsPressed;
        } else if (opMode.gamepad2.y && yIsPressed) {
            opMode.telemetry.addData("LS Direction", "OFF+HOLD");
            //Small amount of power for hold mode
            robot.brushServo1.setPower(-1);
            robot.brushServo2.setPower(-1);
            robot.brushServo1.setPosition(b);
            robot.brushServo2.setPosition(b);
            yIsPressed = !yIsPressed;
        }
        //Wrist Servo (elbow)
        if (opMode.gamepad2.b && bIsPressed) {
            robot.wristServo.setPower(1);
            robot.wristServo.setPosition(x);
            bIsPressed = !bIsPressed;
        } else {
            robot.wristServo.setPower(-1);
            robot.wristServo.setPosition(y);
            bIsPressed = !bIsPressed;
        }
        //Claw Servos (ROSE)
        if (opMode.gamepad2.right_bumper) {
            robot.clawServo.setPower(opMode.gamepad2.right_bumper);
            robot.clawServo.setPosition(e);
        } else (opMode.gamepad2.left_bumper) {
            robot.clawServo.setPower(-1);
            robot.clawServo.setPosition(f);
        }
        //Bucket Motors
        if (opMode.gamepad2.right_trigger) {
            robot.bucketMotor1.setPower(opMode.gamepad2.right_trigger);
            robot.bucketMotor2.setPower(opMode.gamepad2.right_trigger);
        } else if (opMode.gamepad2.left_trigger) {
            robot.bucketMotor1.setPower(-1);
            robot.bucketMotor2.setPower(-1);
        }
    }
}
