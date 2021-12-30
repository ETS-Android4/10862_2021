package org.firstinspires.ftc.teamcode.autons.lm1.blue.Commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class BluePushCommand extends SequentialCommandGroup {
    public BluePushCommand(Drivetrain drivetrain, Intake intake, Telemetry telemetry) {
        //declare variables here

        addCommands(
                //distance is in inches
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, -45),
                //arm
                new DriveForwardCommand(drivetrain, -24),
                //servo deposit
                new DriveForwardCommand(drivetrain, 48),
                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, 48)

        );
    }
}