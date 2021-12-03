package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.util.Util;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Carousel extends SubsystemBase {
    public static double CAROUSEL_LEFT_SPEED = 0.75;
    public static double CAROSUEL_RIGHT_SPEED = -0.75;

    Telemetry telemetry;
    private MotorEx carouselMotor;

    public Carousel(MotorEx carouselMotor, Telemetry tl) {
        carouselMotor.setInverted(false);
        this.carouselMotor = carouselMotor;
        this.telemetry = tl;
    }

    @Override
    public void periodic() {
        //Util.logger(this, telemetry, Level.INFO, "Current Carousel Speed", carousel.get());
    }

    private void set(double speed) {
        carouselMotor.set(speed);
    }

    public void carouselLeft() {
        set(CAROSUEL_RIGHT_SPEED);
    }

    public void carouselRight() {
        set(CAROUSEL_LEFT_SPEED);
    }

    public void stop() {
        carouselMotor.stopMotor();
    }
}
