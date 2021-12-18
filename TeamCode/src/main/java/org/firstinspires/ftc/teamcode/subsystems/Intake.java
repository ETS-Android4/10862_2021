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
    public static double OUTTAKE_SPEED = -.75;


    Telemetry telemetry;
    private MotorEx intakeMotor;

    public Intake(MotorEx intakeMotor, Telemetry tl) {
        this.intakeMotor = intakeMotor;
        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        //Util.logger(this, telemetry, Level.INFO, "Current Intake Speed", intake.get());
    }

    private void set(double speed) {
        intakeMotor.set(speed);
    }

    public void intake() {
        set(INTAKE_SPEED);
    }

    public void outtake() {
        set(OUTTAKE_SPEED);
    }

    public void stop() {
        intakeMotor.stopMotor();
    }


    //Intake Servo
    public static double UP_POS= 0.60;
    public static double MID_POS = 0.5;
    public static double DOWN_POS= 0.75;

    private ServoEx intakeServo;

    public Intake(ServoEx intakeServo) {
        this.intakeServo = intakeServo;
    }

    public void setIntakeServo(double intakeServoPosition) {intakeServo.setPosition(intakeServoPosition);}

    public void servoUp() {setIntakeServo(UP_POS);}
    public void servoMid() {setIntakeServo(MID_POS); }
    public void servoDown() {setIntakeServo(DOWN_POS); }
}
