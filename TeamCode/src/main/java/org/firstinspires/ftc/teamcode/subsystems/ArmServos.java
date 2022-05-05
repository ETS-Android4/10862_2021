package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class ArmServos extends SubsystemBase {

    public static double SERVO_POSITION_ARM_HOME =0.19;
    public static double SERVO_POSITION_ARM_UP = 0.3;
    public static double SERVO_POSITION_ARM_AUTO_DROP = 0.6;
    public static double SERVO_POSITION_ARM_HALF_DROP = 0.85;
    public static double SERVO_POSITION_ARM_DROP = 0.87;
    public static double SERVO_POSITION_ARM_SHARED = 1;

    public static double SERVO_POSITION_BOX_OPEN = 0.45;
    public static double SERVO_POSITION_BOX_AUTO_PUSH = 0.12;
    public static double SERVO_POSITION_BOX_PUSH = 0.1;
    public static double SERVO_POSITION_BOX_CLOSE = 0.624;

    public static double SERVO_POSITION_BOX_CLOSE_BALL = 0.7;
    public static double SERVO_POSITION_BOX_CLOSE_CUBE = 0.8;

    public static boolean boxCanMove;
    public static boolean freightInBox;

    private Telemetry telemetry;
    private TelemetryPacket packet;

    private ServoEx armServo;
    private ServoEx dropServo;

    public ArmServos(ServoEx armServo, ServoEx dropServo, Telemetry tl, HardwareMap hw) {
        this.armServo = armServo;
        this.dropServo = dropServo;

        this.armServo = new SimpleServo(hw,"arm", 0, 270);
        this.dropServo = new SimpleServo(hw, "drop",0,360);
        this.telemetry = tl;
        this.packet = packet;

        this.armServo.setPosition(SERVO_POSITION_ARM_HOME);
        this.dropServo.setPosition(SERVO_POSITION_BOX_OPEN);
    }
    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Arm Servo Position: ", armServo.getPosition());
        Util.logger(this, telemetry, Level.INFO, "Drop Servo Position: ", dropServo.getPosition());
    }

    public void setArmServo(double armServoPosition) {
        armServo.setPosition(armServoPosition);}
    public void setDropServo(double dropServoPosition){
        dropServo.setPosition(dropServoPosition);}

    public void armHome() {
        boxCanMove = true;
        freightInBox = false;
        setArmServo(SERVO_POSITION_ARM_HOME);
    }
    public void armUp() {
        boxCanMove = false;
        freightInBox = true;
        setArmServo(SERVO_POSITION_ARM_UP);
    }
    public void armDrop() {
        boxCanMove = false;
        freightInBox = false;
        setArmServo(SERVO_POSITION_ARM_DROP);
    }
    public void armHalfDrop() {
        boxCanMove = false;
        freightInBox = false;
        setArmServo(SERVO_POSITION_ARM_HALF_DROP);
    }

    public void armAutoDrop() {
        boxCanMove = false;
        freightInBox = false;
        setArmServo(SERVO_POSITION_ARM_AUTO_DROP);
    }

    public void armShared() {
        setArmServo(SERVO_POSITION_ARM_SHARED);
    }

    public void boxOpen() { setDropServo(SERVO_POSITION_BOX_OPEN); }
    public void boxClose() { setDropServo(SERVO_POSITION_BOX_CLOSE); }
    public void boxPush() { setDropServo(SERVO_POSITION_BOX_PUSH); }
    public void boxAutoPush() { setDropServo(SERVO_POSITION_BOX_AUTO_PUSH); }

    public void boxBall() { setDropServo(SERVO_POSITION_BOX_CLOSE_BALL); }
    public void boxCube() { setDropServo(SERVO_POSITION_BOX_CLOSE_CUBE); }

    public void boxUp() { setDropServo(dropServo.getPosition()+0.025); }
    public void boxDown() { setDropServo(dropServo.getPosition()-0.025); }


    public void reset()
    {
        setArmServo(SERVO_POSITION_ARM_HOME);
        setDropServo(SERVO_POSITION_BOX_OPEN);
    }
}