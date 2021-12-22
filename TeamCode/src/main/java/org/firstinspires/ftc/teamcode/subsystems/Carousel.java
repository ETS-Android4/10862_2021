package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Util;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.logging.Level;

@Config
public class Carousel extends SubsystemBase {
    public static double CAROUSEL_LEFT_POWER = -0.5;
    public static double CAROUSEL_RIGHT_POWER = 0.5;

    Telemetry telemetry;
    private CRServo carouselServo;

    public Carousel(HardwareMap hw, Telemetry tl) {
        this.carouselServo = new CRServo(hw, "carouselServo");
        this.telemetry = tl;
    }

    public void periodic() {
        //Util.logger(this, telemetry, Level.INFO, "Current Carousel Speed", carouselServo.get());
    }

    public void set(double power) {carouselServo.set(power); }

    public void carouselLeft() {set(CAROUSEL_LEFT_POWER); }

    public void carouselRight() {set(CAROUSEL_RIGHT_POWER); }

    public void stop() {carouselServo.stopMotor(); }
}
