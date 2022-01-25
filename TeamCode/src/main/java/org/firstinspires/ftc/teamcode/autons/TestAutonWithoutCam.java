package org.firstinspires.ftc.teamcode.autons;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse.CBlueWarehouseCommandR;
import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.LeftCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.RightCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SlowSplineCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
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
/*public void matchStart() {
    schedule(
            new SequentialCommandGroup
                    (
                   new CBlueWarehouseCommandR(drivetrain, intake, lift, armServos, sensorColor, capServos)
                    ));
            }};*/

public void matchStart() {
        schedule(new SequentialCommandGroup(
                new InstantCommand(capServos::autoMid),
                new SplineCommand(drivetrain, new Vector2d(20,25), Math.toRadians(0)),
                new CapArmMidCommand(capServos, drivetrain),
                new SplineCommand(drivetrain, new Vector2d(-8,-35), Math.toRadians(267)),

                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                //new DriveForwardCommand(drivetrain,5),
                //Maybe remove
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(20,25.5), Math.toRadians(0), true),
                new DropFreightCommand(armServos),

                new LiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-8,-35), Math.toRadians(270)),

                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new DriveForwardCommand(drivetrain, 7),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(20,25.5), Math.toRadians(0), true),
                new DropFreightCommand(armServos),

                new LiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-8,-35), Math.toRadians(270)),

                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new DriveForwardCommand(drivetrain, 9),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(20,25.5), Math.toRadians(0), true),
                new DropFreightCommand(armServos),

                new LiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-9,-35), Math.toRadians(270))
        ));
        }};