package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class ArmServos extends SubsystemBase {

    public static double SERVO_POSITION_ARM_HOME =0.68;
    public static double SERVO_POSITION_ARM_UP = 0.4;
    public static double SERVO_POSITION_ARM_DROP = 0;
    public static double SERVO_POSITION_BOX_OPEN = 0.75;
    public static double SERVO_POSITION_BOX_CLOSE = 0.85;

    public static boolean boxCanMove;
    public static boolean freightInBox;


    private Telemetry telemetry;
    private TelemetryPacket packet;

    private ServoEx armServo;
    private ServoEx dropServo;

    public ArmServos(ServoEx armServo, ServoEx dropServo, Telemetry tl, HardwareMap hw) {
        this.armServo = armServo;
        this.dropServo = dropServo;

        this.armServo = new SimpleServo(hw,"arm", 0, 360);
        this.dropServo = new SimpleServo(hw, "drop",0,360);
        this.telemetry = tl;
        this.packet = packet;

        this.armServo.setPosition(SERVO_POSITION_ARM_HOME);
    }
    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Arm Servo Position: ", armServo.getPosition());
        Util.logger(this, telemetry, Level.INFO, "Drop Servo Position: ", dropServo.getPosition());
    }

    public void setArmServo(double armServoPosition) {
        armServo.setPosition(armServoPosition);
    }
    public void setDropServo(double dropServoPosition){ dropServo.setPosition(dropServoPosition);}

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
    public void boxOpen() { setDropServo(SERVO_POSITION_BOX_CLOSE); }
    public void boxClose() { setDropServo(SERVO_POSITION_BOX_OPEN); }

    public void reset()
    {
        setArmServo(SERVO_POSITION_ARM_HOME);
        setDropServo(SERVO_POSITION_BOX_OPEN);
    }
}