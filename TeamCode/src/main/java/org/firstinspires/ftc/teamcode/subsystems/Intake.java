package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.util.Util;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Intake extends SubsystemBase {
    public static double INTAKE_SPEED = 1;
    public static double OUTTAKE_SPEED = -1;
    public static double UP_POSITION= 1;
    public static double MID_POSITION = 0.88;
    public static double DOWN_POSITION= 0.67;


    Telemetry telemetry;
    private MotorEx intakeMotor;
    private ServoEx intakeServo;


    public Intake(MotorEx intakeMotor, ServoEx intakeServo, Telemetry tl) {
        this.intakeMotor = intakeMotor;
        this.intakeServo = intakeServo;
        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        //Util.logger(this, telemetry, Level.INFO, "Current Intake Speed", intake.get());
    }

    private void set(double speed) {intakeMotor.set(speed); }

    public void intake() {set(INTAKE_SPEED); }
    public void outtake() {set(OUTTAKE_SPEED); }
    public void stop() {intakeMotor.stopMotor(); }

    public void setIntakeServo(double intakeServoPosition) {intakeServo.setPosition(intakeServoPosition);}

    public void servoUp() {setIntakeServo(UP_POSITION);}
    public void servoMid() {setIntakeServo(MID_POSITION); }
    public void servoDown() {setIntakeServo(DOWN_POSITION); }

}
