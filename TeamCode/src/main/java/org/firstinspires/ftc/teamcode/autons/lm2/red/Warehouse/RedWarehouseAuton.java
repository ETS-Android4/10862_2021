package org.firstinspires.ftc.teamcode.autons.lm2.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SelectCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.pipelines.TeamMarkerPipeline;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Vision;

import java.util.HashMap;

@Disabled
@Autonomous(name = "Red Warehouse", group = "RED")
public class RedWarehouseAuton extends MatchOpMode {
    public static double startPoseX = 0;
    public static double startPoseY = 0;
    public static double startPoseHeading = 0;

    // Motors
    private MotorEx leftFront, leftRear, rightRear, rightFront;
    private MotorEx intakeMotor;
    private MotorEx liftMotor;
    private ServoEx dropServo, armServo;

    // Gamepad
    private GamepadEx driverGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private Intake intake;
    private Lift lift;
    private Vision vision;
    private ArmServos armServos;

    @Override
    public void robotInit() {
        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();
        // Intake hardware Initializations
        intakeMotor = new MotorEx(hardwareMap, "intakeMotor");
        liftMotor = new MotorEx(hardwareMap, "liftMotor", Motor.GoBILDA.RPM_435);
        //TODO: Do I need to change the RPM?

        //drivetrain.setPoseEstimate(Trajectories.BlueLeftTape.startPose);
        drivetrain.setPoseEstimate(new Pose2d(startPoseX, startPoseY, Math.toRadians(startPoseHeading)));
        intake = new Intake(intakeMotor, telemetry);
        lift = new Lift(liftMotor, telemetry);
        armServos = new ArmServos(armServo, dropServo, telemetry);
    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void matchStart() {
        schedule(
                new SelectCommand(new HashMap<Object, Command>() {{
                    put(TeamMarkerPipeline.Position.LEFT, new SequentialCommandGroup(
                            new RedWarehouseCommandL(drivetrain, intake, lift, armServos)
                    ));
                    put(TeamMarkerPipeline.Position.MIDDLE, new SequentialCommandGroup(
                            new RedWarehouseCommandC(drivetrain, intake, lift, armServos)
                    ));
                    put(TeamMarkerPipeline.Position.RIGHT, new SequentialCommandGroup(
                            new RedWarehouseCommandR(drivetrain, intake, lift, armServos)
                    ));
                }}, vision::getCurrentPosition)
        );

    }
}