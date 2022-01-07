package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

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
import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
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

@Disabled
@Autonomous(name = "CRed Carousel", group = "RED")
public class CRedCarouselAuton extends MatchOpMode {
    public static double startPoseX = 0;
    public static double startPoseY = 0;
    public static double startPoseHeading = 0;

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
                            new CRedCarouselCommandL(drivetrain, intake, lift, armServos, carousel, sensorColor, capServos))
                    );
                    put(TeamMarkerPipeline.Position.MIDDLE, new SequentialCommandGroup(
                            new CRedCarouselCommandC(drivetrain, intake, lift, armServos, carousel, sensorColor, capServos))
                    );
                    put(TeamMarkerPipeline.Position.RIGHT, new SequentialCommandGroup(
                            new CRedCarouselCommandR(drivetrain, intake, lift, armServos, carousel, sensorColor, capServos))
                    );
                }}, vision::getCurrentPosition)
        );

    }
}