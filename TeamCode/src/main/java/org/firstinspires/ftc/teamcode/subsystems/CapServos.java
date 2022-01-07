package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.HardwareMap;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class CapServos extends SubsystemBase {

    public static double SERVO_POSITION_AUTO_LOW = 0.68;
    public static double SERVO_POSITION_AUTO_MID = 0.4;
    public static double SERVO_POSITION_AUTO_HIGH = 0;
    public static double SERVO_POSITION_AUTO_HOME = 0.1;
    public static double SERVO_POSITION_AUTO_OPEN = 0.75;
    public static double SERVO_POSITION_AUTO_CLOSE = 0.85;

    private Telemetry telemetry;
    private TelemetryPacket packet;

    private ServoEx capArmServo;
    private ServoEx clawServo;

    public CapServos(HardwareMap hw, Telemetry tl) {
        this.clawServo = clawServo;
        this.capArmServo = capArmServo;

        //this.clawServo = new ServoEx(hw, "clawServo");

        this.telemetry = tl;
        this.packet = packet;
    }

    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Claw Servo Position: ", clawServo.getPosition());
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
        setclawServo(SERVO_POSITION_AUTO_CLOSE);
    }
}