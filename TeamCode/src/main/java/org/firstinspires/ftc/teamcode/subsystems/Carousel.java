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

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;

import java.util.logging.Level;

@Config
public class Carousel extends SubsystemBase {
    public static double CAROUSEL_LEFT_POWER = -1;
    public static double CAROUSEL_RIGHT_POWER = 1;

    Telemetry telemetry;
    private CRServo carouselServo;

    public Carousel(HardwareMap hw, Telemetry tl) {
        this.carouselServo = new CRServo(hw, "duckServo");
        this.telemetry = tl;
        carouselServo.setInverted(true);
    }

    @Override
    public void periodic() {
        Util.logger(this, telemetry, Level.INFO, "Carousel Servo Speed: ", carouselServo.get());
    }

    public void set(double power) {carouselServo.set(power); }

    public void carouselLeft() {carouselServo.set(CAROUSEL_LEFT_POWER);}

    public void carouselRight() {carouselServo.set(CAROUSEL_RIGHT_POWER);}

    public void stop() {carouselServo.stopMotor();}
}
