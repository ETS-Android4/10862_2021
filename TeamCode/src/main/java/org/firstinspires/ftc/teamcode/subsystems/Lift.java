package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class Lift extends SubsystemBase {
    private Telemetry telemetry;
    private MotorEx liftMotorOne;
    private MotorEx liftMotorTwo;

    public static PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0.005, 0.0008, 0, 0);

    //public static double ARM_OFFSET = 0;
    private PIDFController controller;
    private boolean automatic;

    public static double CPR = 384.5;
    public static double UP_SPEED = -0.75;
    public static double DOWN_SPEED = 0.75;

    private double encoderOffset = 0;

    public static int RESTING_POSITION = 0;
    public static int LOW_POSITION = -350;
    public static int MID_POSITION = -700;
    public static int HIGH_POSITION = -1100;
    public static int CAP_POSITION = 0;

    private int liftPosition = 0;

    public Lift(MotorEx liftMotor, Telemetry tl, HardwareMap hw) {
        //Lift Motor One
        this.liftMotorOne = liftMotor;
        this.liftMotorOne = new MotorEx(hw, "lift");

        this.liftMotorOne.setDistancePerPulse(360 / CPR);
        controller = new PIDFController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f, getAngle(), getAngle());
        controller.setTolerance(10);

        this.telemetry = tl;
        automatic = false;
        setOffset();

        //Lift Motor Two
        this.liftMotorTwo = liftMotor;
        this.liftMotorTwo = new MotorEx(hw, "lift");

        this.liftMotorTwo.setDistancePerPulse(360 / CPR);
        controller = new PIDFController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f, getAngle(), getAngle());
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
            liftMotorOne.set(output);
        }
        Util.logger(this, telemetry, Level.INFO, "lift encoder pos 1: ", liftMotorOne.getCurrentPosition());

        if (automatic) {
            controller.setF(pidfCoefficients.f * Math.cos(Math.toRadians(controller.getSetPoint())));
            double output = controller.calculate(getAngle());
            liftMotorTwo.set(output);
        }
        Util.logger(this, telemetry, Level.INFO, "lift encoder pos 2: ", liftMotorOne.getCurrentPosition());
    }


    private double getEncoderDistance() {
        return liftMotorOne.getDistance() - encoderOffset;
        return liftMotorTwo.getDistance() - encoderOffset;
    }

    public void liftManual() {
        automatic = false;
        liftMotorOne.set(UP_SPEED);
        liftMotorTwo.set(UP_SPEED);
    }

    public void lowerLiftManual() {
        automatic = false;
        liftMotorOne.set(DOWN_SPEED);
        liftMotorOne.set(DOWN_SPEED);
    }

    public void stopLift() {
        liftMotorOne.stopMotor();
        liftMotorTwo.stopMotor();
        controller.setSetPoint(getAngle());
        automatic = false;
    }

    public void setAutomatic(boolean auto) {
        this.automatic = auto;
    }

    public void resetEncoder() {
        encoderOffset = liftMotorOne.getDistance();
    }

    public double getAngle() {
        return getEncoderDistance();
    }

    /************************************************************************************************/
    public void liftResting() {
        automatic = true;
        controller.setSetPoint(RESTING_POSITION);
    }

    public void liftLow() {
        automatic = true;
        controller.setSetPoint(LOW_POSITION);

        liftPosition = 1;
    }

    public void liftMid() {
        automatic = true;
        controller.setSetPoint(MID_POSITION);

        liftPosition = 2;
    }

    public void liftHigh() {
        automatic = true;
        controller.setSetPoint(HIGH_POSITION);

        liftPosition = 3;
    }

    public void liftCap() {
        // TODO: CHANGE
        automatic = true;
        controller.setSetPoint(CAP_POSITION);

        liftPosition = 4;
    }

    public void liftEncoderReset() {
        RESTING_POSITION = 0;
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