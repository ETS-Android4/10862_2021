package org.firstinspires.ftc.teamcode.autons;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Carousel.CBlueCarouselCommand;
import org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse.CBlueWarehouseCommand;
import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.LeftCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.RightCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.AutoDropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
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
    lift = new Lift(liftMotor, liftMotor, telemetry, hardwareMap);
    armServos = new ArmServos(armServo, dropServo, telemetry, hardwareMap);
    carousel = new Carousel(hardwareMap, telemetry);
    capServos = new CapServos(clawServo, capArmServo, telemetry, hardwareMap);
    liftMotor = new MotorEx(hardwareMap, "lift");
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
                   new CBlueWarehouseCommand(drivetrain, intake, lift, armServos, sensorColor, capServos)
                    ));
            }};*/

public void matchStart()
    {
        schedule(new SequentialCommandGroup(
                //Mid
                new InstantCommand(capServos::autoMid),
                new CBlueCarouselCommand(drivetrain, intake, lift, armServos, carousel, sensorColor, capServos))
        );
    }}