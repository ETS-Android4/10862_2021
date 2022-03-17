package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class Intake extends SubsystemBase {
    public static double INTAKE_SPEED = -0.75;
    public static double AUTO_INTAKE_SPEED = -0.5;
    public static double OUTTAKE_SPEED = 0.5;
    public static double UP_POSITION= 0.85;
    public static double MID_POSITION = 0.85;
    public static double DOWN_POSITION= 0.68;


    Telemetry telemetry;
    private MotorEx intakeMotor;
    private ServoEx intakeServo;


    public Intake(MotorEx intakeMotor, ServoEx intakeServo, Telemetry tl, HardwareMap hw) {
        this.intakeMotor = intakeMotor;
        this.intakeServo = intakeServo;
        this.telemetry = tl;

        this.intakeMotor = new MotorEx(hw, "intake");
        this.intakeServo = new SimpleServo(hw,"intakeServo",0,360);
        this.intakeServo.setPosition(UP_POSITION);
    }

    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Intake Motor Speed: ", intakeMotor.get());
        Util.logger(this, telemetry, Level.INFO, "Intake Servo Position: ", intakeServo.getPosition());
    }

    private void set(double speed) {intakeMotor.set(speed); }

    public void intake() {set(INTAKE_SPEED); }

    public void autoIntake() {set(AUTO_INTAKE_SPEED); }

    public void outtake() {set(OUTTAKE_SPEED); }

    public void stop() {intakeMotor.stopMotor(); }

    public void setIntakeServo(double intakeServoPosition) {intakeServo.setPosition(intakeServoPosition);}

    public void servoUp() {setIntakeServo(UP_POSITION);}

    public void servoMid() {setIntakeServo(MID_POSITION); }

    public void servoDown() {setIntakeServo(DOWN_POSITION); }
}
