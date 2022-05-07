package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.driveTrain.MatchOpMode;
import org.firstinspires.ftc.teamcode.driveTrain.SampleTankDrive;
import org.firstinspires.ftc.teamcode.pipelines.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

import java.util.HashMap;
import java.util.logging.Level;
@Autonomous(name = "CBlue Warehouse", group = "BLUE", preselectTeleOp = "Blue TeleOp")
public class CBlueWarehouseAuton extends MatchOpMode {

    public static double startPoseX = 0;
    public static double startPoseY = 0;
    public static double startPoseHeading = 0;

    //Motors and Servos
    private MotorEx leftFront, leftRear, rightRear, rightFront;
    private MotorEx intakeMotor;
    private ServoEx intakeServo;
    private MotorEx liftMotor;
    private CRServo carouselServo;
    private ColorSensor colorSensor;
    private ServoEx capArmServo, realCapArmServo, clawServo;
    private ServoEx armServo, dropServo;

    // Gamepad
    private GamepadEx driverGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private Intake intake;
    private Lift lift;
    private Vision vision;
    private ArmServos armServos;
    //private Carousel carousel;
    private SensorColor sensorColor;
    private CapServos capServos;


    @Override
    public void robotInit() {
        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();
        intake = new Intake(intakeMotor, intakeServo, telemetry, hardwareMap);
        lift = new Lift(liftMotor, liftMotor, telemetry, hardwareMap);
        armServos = new ArmServos(armServo, dropServo, telemetry, hardwareMap);
        //carousel = new Carousel(hardwareMap, telemetry);
        capServos = new CapServos(clawServo, capArmServo, realCapArmServo, telemetry, hardwareMap);

        sensorColor = new SensorColor(hardwareMap, telemetry, "colorSensor");
        vision = new Vision(hardwareMap, "Webcam 1", telemetry);
        drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));
    }

    @Override
    public void disabledPeriodic() {
        Util.logger(this, telemetry, Level.INFO, "Current Position", vision.getCurrentPosition());
        vision.periodic();
    }

    @Override
    public void matchStart() {
        schedule(
                new SelectCommand(new HashMap<Object, Command>() {{
                    put(TeamMarkerPipeline.Position.LEFT, new SequentialCommandGroup(
                            //Low
                            new InstantCommand(capServos::autoLow),
                            new SplineCommand(drivetrain, new Vector2d(22,   -18.5), Math.toRadians(360)),
                            new CBlueWarehouseCommand(drivetrain, intake, lift, armServos, sensorColor, capServos))
                    );
                    put(TeamMarkerPipeline.Position.MIDDLE, new SequentialCommandGroup(
                            //Mid
                            new InstantCommand(capServos::autoMid),
                            new SplineCommand(drivetrain, new Vector2d(22.5, -19), Math.toRadians(0)),
                            new CBlueWarehouseCommand(drivetrain, intake, lift, armServos, sensorColor, capServos))
                    );
                    put(TeamMarkerPipeline.Position.RIGHT, new SequentialCommandGroup(
                            //High
                            new InstantCommand(capServos::autoHigh),
                            new SplineCommand(drivetrain, new Vector2d(22.5,   -18.5), Math.toRadians(0)),
                            new CBlueWarehouseCommand(drivetrain, intake, lift, armServos, sensorColor, capServos))
                    );
                }}, vision::getCurrentPosition)
        );
    }
}