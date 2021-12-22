package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class ArmServos extends SubsystemBase {

    public static double SERVO_POSITION_ARM_HOME = .9;
    public static double SERVO_POSITION_ARM_UP = 0.60;
    public static double SERVO_POSITION_ARM_DROP = 0.1;
    public static double SERVO_POSITION_BOX_OPEN = 0.75;
    public static double SERVO_POSITION_BOX_CLOSE = 0.85;

    private Telemetry telemetry;
    private TelemetryPacket packet;

    private ServoEx armServo;
    private ServoEx dropServo;

    public ArmServos(ServoEx armServo, ServoEx dropServo, Telemetry tl) {
        this.armServo = armServo;
        this.telemetry = tl;
        this.packet = packet;

        this.dropServo = dropServo;

    }

    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Arm Servo Position", armServo.getPosition());
        Util.logger(this, telemetry, Level.INFO, "Drop Servo Position", dropServo.getPosition());
    }

    public void setArmServo(double armServoPosition) {
        armServo.setPosition(armServoPosition);
    }
    public void setDropServo(double dropServoPosition){ dropServo.setPosition(dropServoPosition);
    }

    public void armHome() {
        setArmServo(SERVO_POSITION_ARM_HOME);
    }
    public void armUp(){setArmServo(SERVO_POSITION_ARM_UP );}
    public void armDrop() {
        setArmServo(SERVO_POSITION_ARM_DROP);
    }
    public void boxOpen() {
        setDropServo(SERVO_POSITION_BOX_CLOSE);
    }
    public void boxClose() {
        setDropServo(SERVO_POSITION_BOX_OPEN);
    }
    public void reset() {
        setArmServo(SERVO_POSITION_ARM_HOME);
        setDropServo(SERVO_POSITION_BOX_OPEN);
    }
}