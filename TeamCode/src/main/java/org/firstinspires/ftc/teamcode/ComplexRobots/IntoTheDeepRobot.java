package org.firstinspires.ftc.teamcode.ComplexRobots;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.ButtonMaps.MotorPowers;


@Config
public class IntoTheDeepRobot extends MecanumDrive {

    public final DcMotorEx horizontalSlideMotor;
    public final DcMotorEx bucketMotor1;
    public final DcMotorEx bucketMotor2;
    public final CRServo brushServo;
    public final Servo elbowServo;
    public final Servo bucketServo;
    public final Servo specimenClaw;
    public HardwareMap hardwareMap;

    public IntoTheDeepRobot(HardwareMap hardwareMap, Pose2d pose) {
        super(hardwareMap, pose);
        hardwareMap = this.hardwareMap;
        //Linear Slide Motor
        horizontalSlideMotor = hardwareMap.get(DcMotorEx.class, "horizontalSlideMotor");

        //Setup
        horizontalSlideMotor.setDirection(DcMotor.Direction.REVERSE);
        horizontalSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalSlideMotor.setTargetPositionTolerance(15);
        horizontalSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Bucket Motor
        bucketMotor1 = hardwareMap.get(DcMotorEx.class, "bucketMotor1");
        bucketMotor2 = hardwareMap.get(DcMotorEx.class, "bucketMotor2");

        //Setup
        bucketMotor1.setDirection(DcMotor.Direction.FORWARD);
        bucketMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucketMotor1.setTargetPositionTolerance(15);
        bucketMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bucketMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bucketMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bucketMotor2.setDirection(DcMotor.Direction.FORWARD);
        bucketMotor2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bucketMotor2.setTargetPositionTolerance(15);
        bucketMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bucketMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Servos
        brushServo = hardwareMap.get(CRServo.class, "brushServo");
        elbowServo = hardwareMap.get(Servo.class, "elbowServo");
        specimenClaw = hardwareMap.get(Servo.class, "specimenClaw");
        bucketServo = hardwareMap.get(Servo.class, "bucketServo");

        //Initialize Output Servo
        bucketServo.scaleRange(0,0.35);
        bucketServo.setPosition(0);
        specimenClaw.scaleRange(-1,1);
        specimenClaw.setPosition(1);
//        elbowServo.setPosition(0.8);

    }


    //TODO: Linear slide helper methods for auto (later)
//    public Action setSlideHeightAction(int targetPosition){
//        return new SlideHeight(targetPosition);
//    }

    public MotorPowers setAllMotorPowers(int i) {
        return new MotorPowers(0,0,0,0);
    }

    public MotorPowers pivotTurn(double currentMotorPower, boolean rightBumper, boolean leftBumper) {
        double rightTopMotorPower = -currentMotorPower;
        double rightBotMotorPower = -currentMotorPower;
        double leftTopMotorPower = -currentMotorPower;
        double leftBotMotorPower = -currentMotorPower;
        if (leftBumper) {

            rightBotMotorPower *= -1;
            rightTopMotorPower *= -1;
        }
        else if (rightBumper) {
            leftBotMotorPower *= -1;
            leftTopMotorPower *= -1;
        }
        return new MotorPowers(leftTopMotorPower,-rightTopMotorPower,-leftBotMotorPower,rightBotMotorPower);
    }

    public void setMotorTo(DcMotorEx motor, int targetPos, double power) {
        if (motor.getCurrentPosition() < targetPos) {
            while (motor.getCurrentPosition() <= targetPos) {
                motor.setPower(power);
            }
        }
        else if (motor.getCurrentPosition() > targetPos) {
            while (motor.getCurrentPosition() >= targetPos) {
                motor.setPower(-power);
            }
        }
    }
    //Possible method, no use

//    class SlideHeight implements Action {
//        private boolean initialized;
//        private int targetPosition;
//        private boolean raise;
//        SlideHeight(int targetPosition){
//            initialized = false;
//            this.targetPosition = targetPosition;
//            if(linearSlidesMotor1.getCurrentPosition() < targetPosition) raise = true;
//            else raise = false;
//        }
//
//
//        @Override
//        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//            if(!initialized){
//                if(raise){
//                    linearSlidesMotor1.setPower(0.5);
//                    linearSlidesMotor1.setPower(0.5);
//                }else{
//                    linearSlidesMotor1.setPower(-0.3);
//                    linearSlidesMotor1.setPower(-0.3);
//                }
//                initialized = true;
//                return true;
//            }else{
//                int avgCurrentPosition = (linearSlidesMotor1.getCurrentPosition()+linearSlidesMotor2.getCurrentPosition())/2;
//                int difference;
//                if(raise){
//                    difference = targetPosition - avgCurrentPosition;
//                    if(difference <= 7 && difference >= -7){
//                        //STOP
//                        linearSlidesMotor1.setPower(0);
//                        linearSlidesMotor2.setPower(0);
//                        //Exit out, we're done
//                        return false;
//                    }else if (difference < -7) {
//                        //Reverse
//                        linearSlidesMotor1.setPower(-0.15);
//                        linearSlidesMotor2.setPower(-0.15);
//                    }else if(difference < 100 && difference > 7){
//                        //Soft stop
//                        linearSlidesMotor1.setPower(0.25);
//                        linearSlidesMotor2.setPower(0.25);
//                    }
//                }else {
//                    difference = avgCurrentPosition - targetPosition;
//                    if (difference <= 7 && difference >= -7) {
//                        //STOP
//                        linearSlidesMotor1.setPower(0);
//                        linearSlidesMotor2.setPower(0);
//                        //Exit out, we're done
//                        return false;
//                    } else if (difference < -7) {
//                        //Reverse
//                        linearSlidesMotor1.setPower(0.25);
//                        linearSlidesMotor2.setPower(0.25);
//                    } else if (difference < 100 && difference > 7) {
//                        //Soft stop
//                        linearSlidesMotor1.setPower(-0.15);
//                        linearSlidesMotor2.setPower(-0.15);
//                    }
//                }
//                return true;
//            }
//        }
    }
