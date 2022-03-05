package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class CapServos extends SubsystemBase {

    public static double SERVO_POSITION_AUTO_LOW = 0.88;
    public static double SERVO_POSITION_AUTO_MID = 0.77;
    public static double SERVO_POSITION_AUTO_HIGH = 0.65;
    public static double SERVO_POSITION_AUTO_HOME = 0.16;

    public static double SERVO_POSITION_AUTO_OPEN = 0.38;
    public static double SERVO_POSITION_AUTO_CLOSE = 0.84;

    private Telemetry telemetry;
    private TelemetryPacket packet;

    private ServoEx capArmServo;
    private ServoEx clawServo;

    public CapServos(ServoEx capArmServo, ServoEx clawServo, Telemetry tl, HardwareMap hw) {
        this.clawServo = clawServo;
        this.capArmServo = capArmServo;

        this.capArmServo = new SimpleServo(hw,"capArmServo", 0, 270);
        this.clawServo = new SimpleServo(hw, "clawServoGrabber",0,270);

        this.telemetry = tl;
        this.packet = packet;

        this.capArmServo.setPosition(SERVO_POSITION_AUTO_HOME);
        this.clawServo.setPosition(SERVO_POSITION_AUTO_CLOSE);
    }

    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Claw Grabber Servo Position: ", clawServo.getPosition());
        Util.logger(this, telemetry, Level.INFO, "Cap Arm Servo Position: ", capArmServo.getPosition());
    }

    public void setclawServo(double clawServoPosition) { clawServo.setPosition(clawServoPosition);}
    public void setcapArmServo(double capArmServoPosition){ capArmServo.setPosition(capArmServoPosition);}

    public void clawOpen() {
        setclawServo(SERVO_POSITION_AUTO_OPEN);
    }

    public void clawClose() {
        setclawServo(SERVO_POSITION_AUTO_CLOSE);
    }

    public void autoLow() {
        setcapArmServo(SERVO_POSITION_AUTO_LOW);
    }

    public void autoMid() {
        setcapArmServo(SERVO_POSITION_AUTO_MID);
    }

    public void autoHigh() {
        setcapArmServo(SERVO_POSITION_AUTO_HIGH);
    }

    public void capReset() {
        setcapArmServo(SERVO_POSITION_AUTO_HOME);
        setclawServo(SERVO_POSITION_AUTO_OPEN);
    }
}