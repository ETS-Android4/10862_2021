package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.commands.InitializeCommand;
import org.firstinspires.ftc.teamcode.driveTrain.MatchOpMode;
import org.firstinspires.ftc.teamcode.driveTrain.SampleTankDrive;
import org.firstinspires.ftc.teamcode.pipelines.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
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

/*// Motors
private MotorEx leftFront, leftRear, rightRear, rightFront;
private MotorEx intakeMotor;
private ServoEx intakeServo;
private MotorEx liftMotor;
private CRServo carouselServo;
private ColorSensor colorSensor;*/

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
private CapServos capServos;

    @Override
    public void robotInit() {
        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();
        vision = new Vision(hardwareMap, "Webcam 1", telemetry);
        sensorColor = new SensorColor(hardwareMap, telemetry,"colorSensor");
        drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));

        new InitializeCommand(armServos, lift, intake, capServos);
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
                        new CRedWarehouseCommandL(drivetrain, intake, lift, armServos, sensorColor, capServos))
                );
                put(TeamMarkerPipeline.Position.MIDDLE, new SequentialCommandGroup(
                        new CRedWarehouseCommandC(drivetrain, intake, lift, armServos, sensorColor, capServos))
                );
                put(TeamMarkerPipeline.Position.RIGHT, new SequentialCommandGroup(
                        new CRedWarehouseCommandR(drivetrain, intake, lift, armServos, sensorColor, capServos))
                );
            }}, vision::getCurrentPosition)
    );
    }
}