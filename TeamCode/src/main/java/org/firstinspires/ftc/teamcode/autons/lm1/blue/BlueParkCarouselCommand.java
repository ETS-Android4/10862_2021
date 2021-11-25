package org.firstinspires.ftc.teamcode.autons.lm1.blue;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.SlowSplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class BlueParkCarouselCommand extends SequentialCommandGroup {
    public BlueParkCarouselCommand(Drivetrain drivetrain, Telemetry telemetry) {
        //declare variables here

        addCommands(
                new DriveForwardCommand(drivetrain, 24),
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 90,true),
                new WaitCommand(1000),
                new TurnToCommand(drivetrain, 0),
                new WaitCommand(1000),
                new SlowSplineCommand(drivetrain, new Vector2d(20, 20), 0),
                new WaitCommand(1000),
                new SlowSplineCommand(drivetrain, new Vector2d(0, 0), 180, true)
                /*
                    new DriveForwardCommand(drivetrain, 25),
                    new TurnToCommand(drivetrain, -93, true),
                    new DriveForwardCommand(drivetrain, -125)
                 */
        );
    }
}