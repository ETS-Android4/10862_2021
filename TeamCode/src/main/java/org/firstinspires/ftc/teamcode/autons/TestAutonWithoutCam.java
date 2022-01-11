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

import org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse.CBlueWarehouseCommandR;
import org.firstinspires.ftc.teamcode.driveTrain.MatchOpMode;
import org.firstinspires.ftc.teamcode.driveTrain.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

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
private ServoEx clawServo, capArmServo;

// Gamepad
private GamepadEx driverGamepad;

// Subsystems
private Drivetrain drivetrain;
private Intake intake;
private Lift lift;
private ArmServos armServos;
private Carousel carousel;
private CapServos capServos;
private SensorColor sensorColor;

@Override
public void robotInit() {
    drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
    drivetrain.init();
    intake = new Intake(intakeMotor, intakeServo, telemetry, hardwareMap);
    lift = new Lift(liftMotor, telemetry, hardwareMap);
    armServos = new ArmServos(armServo, dropServo, telemetry, hardwareMap);
    carousel = new Carousel(hardwareMap, telemetry);
    capServos = new CapServos(clawServo, capArmServo, telemetry, hardwareMap);
    //liftMotor = new MotorEx(hardwareMap, "lift");
    sensorColor = new SensorColor(hardwareMap, telemetry, "colorSensor");
}
@Override
    /*public void matchStart() {
        schedule(
                new SequentialCommandGroup(
                        new CBlueCarouselCommandR(drivetrain, intake, lift, armServos, carousel)));
    }};*/

public void matchStart() {
    schedule(
            new SequentialCommandGroup
                    (
                   new CBlueWarehouseCommandR(drivetrain, intake, lift, armServos, sensorColor, capServos)
                    ));
            }};