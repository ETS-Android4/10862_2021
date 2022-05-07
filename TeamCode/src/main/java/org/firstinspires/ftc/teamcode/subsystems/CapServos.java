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
public class CapServos extends SubsystemBase {

    public static double SERVO_POSITION_AUTO_LOW = 0.95;
    public static double SERVO_POSITION_AUTO_MID = 0.82;
    public static double SERVO_POSITION_AUTO_HIGH = 0.64;
    public static double SERVO_POSITION_AUTO_HOME = 0.195;

    public static double SERVO_POSITION_AUTO_OPEN = 0.38;
    public static double SERVO_POSITION_AUTO_CLOSE = 0.84;

    public static double SERVO_HOME_POS = 0.01;
    public static double SERVO_MID_POS = 0.048;
    public static double SERVO_LOW_POS = 0.24;





    private Telemetry telemetry;
    private TelemetryPacket packet;

    private ServoEx capArmServo;
    private ServoEx realCapArmServo;
    private ServoEx clawServo;

    private double realCapServoPos = 0;

    public CapServos(ServoEx capArmServo, ServoEx clawServo, ServoEx realCapArmServo, Telemetry tl, HardwareMap hw) {
        this.clawServo = clawServo;
        this.capArmServo = capArmServo;
        this.realCapArmServo = realCapArmServo;

        this.capArmServo = new SimpleServo(hw,"capArmServo", 0, 270);
        this.clawServo = new SimpleServo(hw, "clawServoGrabber",0,270);
        this.realCapArmServo = new SimpleServo(hw,"realCapArmServo", 0, 270);


        this.telemetry = tl;
        this.packet = packet;

        this.capArmServo.setPosition(SERVO_POSITION_AUTO_HOME);
        this.clawServo.setPosition(SERVO_POSITION_AUTO_CLOSE);

        this.realCapArmServo.setPosition(SERVO_HOME_POS);
    }

    @Override
    public void periodic() {
        realCapServoPos = realCapArmServo.getPosition();
        Util.logger(this, telemetry, Level.INFO, "Claw Grabber Servo Position: ", clawServo.getPosition());
        Util.logger(this, telemetry, Level.INFO, "Cap Arm Servo Position: ", capArmServo.getPosition());
        Util.logger(this, telemetry, Level.INFO, "Real Cap Arm Servo Position: ", realCapArmServo.getPosition());

    }

    public void setclawServo(double clawServoPosition) { clawServo.setPosition(clawServoPosition);}
    public void setcapArmServo(double capArmServoPosition){ capArmServo.setPosition(capArmServoPosition);}
    public void setrealCapArmServo(double realCapArmServoPosition){ realCapArmServo.setPosition(realCapArmServoPosition);}

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

    public void realCapHome() {
        setrealCapArmServo(SERVO_HOME_POS);
    }

    public void realCapLow() {
        setrealCapArmServo(SERVO_LOW_POS);//0.23
    }

    public void realCapMid() {
        setrealCapArmServo(SERVO_MID_POS);//0.1
    }

    public void addToCap() { setrealCapArmServo(realCapServoPos+0.05); }
    public void subtractToCap() { setrealCapArmServo(realCapServoPos-0.05); }



}