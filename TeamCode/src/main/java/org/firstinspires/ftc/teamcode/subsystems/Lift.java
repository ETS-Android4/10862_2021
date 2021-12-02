package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.logging.Level;

@Config
public class Lift extends SubsystemBase {
    private Telemetry telemetry;
    private MotorEx liftMotor;

    public static PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0.01, 0.0001, 0.003, 0);
    //public static double ARM_OFFSET = 0;
    private PIDFController controller;
    private boolean automatic;

    public static double CPR = 384.5; //383.6
    public double UP_SPEED = 0.1;
    public double DOWN_SPEED = -0.1;

    private double encoderOffset = 0;

    public int RESTING_POSITION = 0;
    public static int LOW_POSITION = 80;
    public static int MID_POSITION = 80;
    public static int HIGH_POSITION = 1;
    public static int CAP_POSITION = 1;

    private int liftPosition = 0;

    public Lift(MotorEx liftMotor, Telemetry tl) {
        this.liftMotor = liftMotor;

        this.liftMotor.setDistancePerPulse(360/CPR);
        liftMotor.setInverted(false);

        controller = new PIDFController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f,  getAngle(), getAngle());
        controller.setTolerance(10);

        this.telemetry = tl;
        automatic = false;
        setOffset();
    }


    public void toggleAutomatic() {
        automatic = !automatic;
    }
    public boolean isAutomatic() {
        return automatic;
    }

    @Override
    public void periodic() {
        if (automatic) {
            controller.setF(pidfCoefficients.f * Math.cos(Math.toRadians(controller.getSetPoint())));
            double output = controller.calculate(getAngle());
            liftMotor.set(output);
        }
    }

    private double getEncoderDistance() {
        return liftMotor.getDistance() - encoderOffset;
    }

    public void liftManual() {
        automatic = false;
        liftMotor.set(UP_SPEED);
    }

    public void lowerLiftManual() {
        automatic = false;
        liftMotor.set(DOWN_SPEED);
    }

    public void stopLift() {
        liftMotor.stopMotor();
        controller.setSetPoint(getAngle());
        automatic = false;
    }

    public void setAutomatic(boolean auto) {
        this.automatic = auto;
    }

    public void resetEncoder() {
        encoderOffset = liftMotor.getDistance();
    }

    public double getAngle() {
        return getEncoderDistance();
    }

    /************************************************************************************************/
    public void liftResting() {
        // TODO: CHANGE
        controller.setP(0.015);
        automatic = true;
        controller.setSetPoint(RESTING_POSITION);
    }
    public void liftLow() {
        // TODO: CHANGE
        controller.setP(0.025);
        automatic = true;
        controller.setSetPoint(LOW_POSITION);

        liftPosition = 1;
    }
    public void liftMid() {
        // TODO: CHANGE
        controller.setP(0.025);

        automatic = true;
        controller.setSetPoint(MID_POSITION);

        liftPosition = 2;
    }

    public void liftHigh() {
        // TODO: CHANGE
        controller.setP(0.025);

        automatic = true;
        controller.setSetPoint(HIGH_POSITION);

        liftPosition = 3;
    }

    public void liftCap() {
        // TODO: CHANGE
        controller.setP(0.025);

        automatic = true;
        controller.setSetPoint(CAP_POSITION);

        liftPosition = 4;
    }

    public void setLift(double angle) {
        automatic = true;
        controller.setSetPoint(angle);
    }

    public boolean atTargetAngle() {
        return controller.atSetPoint();
    }


    public void setOffset() {
        resetEncoder();
        controller.setSetPoint(getAngle());
    }

    public void moveUp() {
        liftPosition = liftPosition + 1;
        if(liftPosition > 4) {
            liftPosition = 4;
        }
        moveLiftToCorrectHeight();
    }

    public void moveDown() {
        liftPosition = liftPosition - 1;
        if(liftPosition < 0) {
            liftPosition = 0;
        }
        moveLiftToCorrectHeight();
    }

    public void moveLiftToCorrectHeight() {
        if(liftPosition == 0) {
            liftResting();
        } else if(liftPosition == 1) {
            liftLow();
        } else if(liftPosition == 2) {
            liftMid();
        } else if(liftPosition == 3) {
            liftHigh();
        } else if(liftPosition == 4) {
            liftCap();
        }
    }
}