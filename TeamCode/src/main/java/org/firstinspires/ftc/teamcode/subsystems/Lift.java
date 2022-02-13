package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class Lift extends SubsystemBase {
    private Telemetry telemetry;
    private MotorEx liftMotor;
    private MotorEx liftMotor2;

    public static PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0.0005, 0.0008, 0, 0);

    //public static double ARM_OFFSET = 0;
    private PIDFController controller;
    private PIDFController controller2;
    private boolean automatic;

    public static double CPR = 384.5;
    public static double UP_SPEED = -0.25;
    public static double DOWN_SPEED = 0.25;

    private double encoderOffset = 0;
    private double encoderOffset2 = 0;

    public static int RESTING_POSITION = 0;
    public static int LOW_POSITION = -350;
    public static int MID_POSITION = -700;
    public static int HIGH_POSITION = -1100;
    public static int CAP_POSITION = 0;

    public static int RESTING_POSITION2 = 0;
    public static int LOW_POSITION2 = 350;
    public static int MID_POSITION2 = 700;
    public static int HIGH_POSITION2 = 1100;
    public static int CAP_POSITION2 = 0;

    private int liftPosition = 0;

    public Lift(MotorEx liftMotor, MotorEx liftMotor2, Telemetry tl, HardwareMap hw) {
        this.liftMotor = liftMotor;
        this.liftMotor2 = liftMotor2;

        this.liftMotor = new MotorEx(hw, "lift");
        this.liftMotor2 = new MotorEx(hw, "lift2");

        //Reverse lift motor
        this.liftMotor2.setInverted(true);

        this.liftMotor.setDistancePerPulse(360 / CPR);
        this.liftMotor2.setDistancePerPulse(360 / CPR);

        controller = new PIDFController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f, getAngle(), getAngle());
        controller.setTolerance(10);

        controller2 = new PIDFController(pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f, getAngle2(), getAngle2());
        controller2.setTolerance(10);

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


            controller2.setF(pidfCoefficients.f * Math.cos(Math.toRadians(controller2.getSetPoint())));

            double output = controller.calculate(getAngle());
            double output2 = controller2.calculate(getAngle2());

            liftMotor.set(output);
            liftMotor2.set(output2);
        }
        Util.logger(this, telemetry, Level.INFO, "lift encoder pos 1: ", liftMotor.getCurrentPosition());
        Util.logger(this, telemetry, Level.INFO, "lift encoder pos 2: ", liftMotor2.getCurrentPosition());
    }

    private double getEncoderDistance() {
        return liftMotor.getDistance() - encoderOffset;
    }

    private double getEncoderDistance2(){
        return liftMotor2.getDistance() -encoderOffset2;
    }

    public void liftManual() {
        automatic = false;
        liftMotor.set(UP_SPEED);
        liftMotor2.set(UP_SPEED);
    }

    public void lowerLiftManual() {
        automatic = false;
        liftMotor.set(DOWN_SPEED);
        liftMotor2.set(DOWN_SPEED);
    }

    public void stopLift() {
        liftMotor.stopMotor();
        controller.setSetPoint(getAngle());
        liftMotor2.stopMotor();
        controller2.setSetPoint(getAngle2());
        automatic = false;
    }

    public void setAutomatic(boolean auto) {
        this.automatic = auto;
    }

    public void resetEncoder() {
        liftEncoderReset();
    }

    public double getAngle() {
        return getEncoderDistance();
    }

    public double getAngle2(){
        return getEncoderDistance2();
    }

    /****************************************************************************************/

    public void liftResting() {
        automatic = true;
        controller.setSetPoint(RESTING_POSITION);
        controller2.setSetPoint(RESTING_POSITION);
        liftPosition = 0;
    }

    public void liftLow() {
        automatic = true;
        controller.setSetPoint(LOW_POSITION);
        controller2.setSetPoint(LOW_POSITION);
        liftPosition = 1;
    }

    public void liftMid() {
        automatic = true;
        controller.setSetPoint(MID_POSITION);
        controller2.setSetPoint(MID_POSITION);
        liftPosition = 2;
    }

    public void liftHigh() {
        automatic = true;
        controller.setSetPoint(HIGH_POSITION);
        controller2.setSetPoint(HIGH_POSITION);
        liftPosition = 3;
    }

    public void liftEncoderReset() {
        liftPosition = 0;
    }

    public void setLift(double angle) {
        automatic = true;
        controller.setSetPoint(angle);
        controller2.setSetPoint(angle);
    }

    public boolean atTargetAngle() {
        return controller.atSetPoint();
    }
    public boolean atTargetAngle2(){
        return controller2.atSetPoint();
    }


    public void setOffset() {
        resetEncoder();
        controller.setSetPoint(getAngle());
        controller2.setSetPoint(getAngle2());
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
        }
    }
}