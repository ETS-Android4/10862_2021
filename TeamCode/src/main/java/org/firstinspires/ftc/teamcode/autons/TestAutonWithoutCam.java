package org.firstinspires.ftc.teamcode.autons;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.driveTrain.MatchOpMode;
import org.firstinspires.ftc.teamcode.driveTrain.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

@Autonomous(name = "TestAutonWithoutCam", group = "RED/BLUE")
public class TestAutonWithoutCam extends MatchOpMode {
public static double startPoseX = 0;
public static double startPoseY = 0;
public static double startPoseHeading = 0;

// Motors
private MotorEx leftFront, leftRear, rightRear, rightFront;
private MotorEx intakeMotor;
private ServoEx intakeServo;
private MotorEx liftMotor;
private CRServo carouselServo;
private ServoEx dropServo, armServo;

// Gamepad
private GamepadEx driverGamepad;

// Subsystems
private Drivetrain drivetrain;
private Intake intake;
private Lift lift;
private ArmServos armServos;
private Carousel carousel;

@Override
public void robotInit() {
    drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
    drivetrain.init();
    drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));
    liftMotor = new MotorEx(hardwareMap, "lift", Motor.GoBILDA.RPM_117);
    armServo = new SimpleServo(hardwareMap,"arm", 0, 360);
    dropServo = new SimpleServo(hardwareMap, "drop",0,360);
    lift = new Lift(liftMotor, telemetry, hardwareMap);
}
@Override
    /*public void matchStart() {
        schedule(
                new SequentialCommandGroup(
                        new CBlueCarouselCommandR(drivetrain, intake, lift, armServos, carousel)));
    }};*/

public void matchStart() {
    schedule(
            new SequentialCommandGroup(
                   // new CBlueWarehouseCommandR(drivetrain, intake, lift, armServos
            ));}};