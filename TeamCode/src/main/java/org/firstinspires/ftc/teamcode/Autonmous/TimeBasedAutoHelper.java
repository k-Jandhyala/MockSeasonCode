package org.firstinspires.ftc.teamcode.Autonmous;

import org.firstinspires.ftc.teamcode.ComplexRobots.IntoTheDeepRobot;
import org.firstinspires.ftc.teamcode.Enums.Direction;

import com.qualcomm.hardware.bosch.BNO055IMU;

/*
  TODO: The TimeBasedAutoHelper is a class that is designed to help with programming an autonomous that runs solely based
        on time and encoder positions. This is a very unstable autonomous system as any collision or anything like that will
        cause the robot to veer of course. However this is a simple plug and play method you will be able simply call this
        class and run the robot in autonomous.

  TODO: To implement the this helper method
        1) Setup the
*/
public class TimeBasedAutoHelper {

    public void strafeMotorsFor(Direction direction, int millisDelay, long startTime, double motorPower, IntoTheDeepRobot robot) {
        if (direction == Direction.LEFT) {
            while ((System.currentTimeMillis() - startTime) < millisDelay) {
                robot.setMotorPower(-motorPower, motorPower, motorPower, -motorPower);
            }
        } else if (direction == Direction.RIGHT) {
            while ((System.currentTimeMillis() - startTime) < millisDelay) {
                robot.setMotorPower(motorPower, -motorPower, -motorPower, motorPower);
            }
        }
    }

    public void driveAllMotorsFor(Direction direction, int millisDelay, long startTime, double motorPower, IntoTheDeepRobot robot) {
        if (direction == Direction.FORWARD) {
            while ((System.currentTimeMillis() - startTime) < millisDelay) {
                robot.setAllMotorPowers(motorPower);
            }
        } else if (direction == Direction.BACKWARD) {
            while ((System.currentTimeMillis() - startTime) < millisDelay) {
                robot.setAllMotorPowers(-motorPower);
            }
        }
    }

    public void pivotTurnDeg(int targetPivotAngle, double motorPower, IntoTheDeepRobot robot) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        BNO055IMU imu = robot.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
        double currentAngle = imu.getAngularOrientation().firstAngle;

        while (Math.abs(currentAngle - targetPivotAngle) >= 0) {
            currentAngle = imu.getAngularOrientation().firstAngle;

            if (currentAngle < targetPivotAngle) {
                robot.pivotTurn(motorPower, true, false);
            } else {
                robot.pivotTurn(motorPower, false, true);
            }
        }
    }

    public void ParallelAction(Runnable action1, Runnable action2) {
        Thread thread1 = new Thread(action1);
        Thread thread2 = new Thread(action2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runSlidesTo(Direction direction, int targetPosition, double motorPower, IntoTheDeepRobot robot){
        if(direction == Direction.DOWN){
            motorPower *= -1;
        }
        double finalMotorPower = motorPower; //Variables used in the lambda expression should be final or effectively final
        ParallelAction(
                () -> robot.setMotorTo(robot.bucketMotor1, targetPosition, finalMotorPower),
                () -> robot.setMotorTo(robot.bucketMotor2, targetPosition, finalMotorPower)
        );
    }
    public void combineStrafeAndDrive(Direction strafeDirection, Direction driveDirection, int millisDelay, long startTime, double strafePower, double drivePower, IntoTheDeepRobot robot){
        if (strafeDirection == Direction.LEFT) {
            strafePower *= -1;
        }
        if (driveDirection == Direction.BACKWARD) {
            drivePower *= -1;
        }
        while ((System.currentTimeMillis() - startTime) < millisDelay) {
            robot.setMotorPower(drivePower + strafePower, drivePower - strafePower, drivePower - strafePower, drivePower + strafePower);
        }
    }
}
