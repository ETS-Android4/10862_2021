package org.firstinspires.ftc.teamcode.autons;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class TestAutonCommand extends SequentialCommandGroup {
    public TestAutonCommand(Drivetrain drivetrain, Intake intake, Telemetry telemetry) {
        //declare variables here

        addCommands(
            //new TurnCommand(drivetrain, 30),
                //new TurnCommand(drivetrain, 30),
                //new TurnToCommand(drivetrain, 130, true),
                //new DriveForwardCommand(drivetrain, 50),
                //new StrafeSidewaysCommand(drivetrain, 50),
                //new SplineCommand(drivetrain, new Vector2d(20, 5), Math.toRadians(0), false),
                //new SplineCommand(drivetrain, new Vector2d(10, 20), Math.toRadians(180), true)
                //new StrafeToCommand(drivetrain, new Pose2d(30, 20, Math.toRadians(88))),
                //new LineToHeadingCommand(drivetrain, new Pose2d(20, 20, Math.toRadians(45)))
                //new SplineConstantHeadingCommand(drivetrain, new Vector2d(20, 8), Math.toRadians(0))
                new TurnCommand(drivetrain, -360)

        );
    }
}