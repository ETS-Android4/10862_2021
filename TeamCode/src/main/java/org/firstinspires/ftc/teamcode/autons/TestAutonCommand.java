package org.firstinspires.ftc.teamcode.autons;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class TestAutonCommand extends SequentialCommandGroup {
    public TestAutonCommand(Drivetrain drivetrain, Intake intake, Telemetry telemetry) {
        //declare variables here

        addCommands(
                new SplineCommand(drivetrain, new Vector2d(-15, -13), 300, true)

        );
    }
}