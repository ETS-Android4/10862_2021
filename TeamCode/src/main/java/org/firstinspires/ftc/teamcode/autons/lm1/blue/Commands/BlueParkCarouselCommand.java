package org.firstinspires.ftc.teamcode.autons.lm1.blue.Commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class BlueParkCarouselCommand extends SequentialCommandGroup {
    public BlueParkCarouselCommand(Drivetrain drivetrain, Telemetry telemetry) {
        //declare variables here

        addCommands(
                //distance is in inches
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 45, true),
                //arm
                new DriveForwardCommand(drivetrain, -24),
                //servo deposit
                new DriveForwardCommand(drivetrain, 24),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, 24.5),
                new TurnToCommand(drivetrain, 170),
                new DriveForwardCommand(drivetrain, 24),
                //carousel
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, 24)
        );
    }
}