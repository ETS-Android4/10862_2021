package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.autons.lm2.blue.Warehouse.BlueWarehouseCommandC;
import org.firstinspires.ftc.teamcode.autons.lm2.blue.Warehouse.BlueWarehouseCommandL;
import org.firstinspires.ftc.teamcode.autons.lm2.blue.Warehouse.BlueWarehouseCommandR;
import org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse.CBlueWarehouseCommandC;
import org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse.CBlueWarehouseCommandL;
import org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse.CBlueWarehouseCommandR;
import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.pipelines.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

import java.util.HashMap;
import java.util.logging.Level;

@Autonomous(name = "CRed Warehouse", group = "BLUE")
public class CRedWarehouseAuton extends MatchOpMode {
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
private ColorSensor colorSensor;

// Gamepad
private GamepadEx driverGamepad;

// Subsystems
private Drivetrain drivetrain;
private Intake intake;
private Lift lift;
private Vision vision;
private ArmServos armServos;
private Carousel carousel;
private SensorColor sensorColor;

@Override
public void robotInit() {
    // Subsystems
    drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
    drivetrain.init();
    intakeMotor = new MotorEx(hardwareMap, "intake");
    liftMotor = new MotorEx(hardwareMap, "lift", Motor.GoBILDA.RPM_117);

    //drivetrain.setPoseEstimate(Trajectories.BlueLeftTape.startPose);
    vision = new Vision(hardwareMap, "Webcam 1", telemetry);
    armServo = new SimpleServo(hardwareMap,"arm", 0, 360);
    dropServo = new SimpleServo(hardwareMap, "drop",0,360);
    sensorColor = new SensorColor(hardwareMap, telemetry,"colorSensor");

    drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));
    intake = new Intake(intakeMotor, intakeServo, telemetry);
    lift = new Lift(liftMotor, telemetry);
    armServos = new ArmServos(armServo, dropServo, telemetry);
    //TODO:Y isn't this working?
    sensorColor = new com.arcrobotics.ftclib.hardware.SensorColor(hardwareMap, telemetry,"colorSensor");
    sensorColor = new com.arcrobotics.ftclib.hardware.SensorColor(colorSensor, telemetry);
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
                        new CBlueWarehouseCommandL(drivetrain, intake, lift, armServos, colorSensor))
                );
                put(TeamMarkerPipeline.Position.MIDDLE, new SequentialCommandGroup(
                        new CBlueWarehouseCommandC(drivetrain, intake, lift, armServos, colorSensor))
                );
                put(TeamMarkerPipeline.Position.RIGHT, new SequentialCommandGroup(
                        new CBlueWarehouseCommandR(drivetrain, intake, lift, armServos, colorSensor))
                );
            }}, vision::getCurrentPosition)
    );
    }
}