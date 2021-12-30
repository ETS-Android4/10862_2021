package org.firstinspires.ftc.teamcode.autons.lm1.red;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SlowestDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class RedSafeCommand extends SequentialCommandGroup {
    public RedSafeCommand(Drivetrain drivetrain, Intake intake, Telemetry telemetry) {
        //declare variables here

        addCommands(
                new WaitCommand(5000),
                new DriveForwardCommand(drivetrain, 43),
                new TurnToCommand(drivetrain, 152),
                new KindaSlowDriveForwardCommand(drivetrain, 38),
                new SlowDriveForwardCommand(drivetrain, 10),
                //new InstantCommand(intake::halfIntakeBlue, intake),
                new SlowestDriveForwardCommand(drivetrain, 4),
                new WaitCommand(1200),
                new SlowestDriveForwardCommand(drivetrain, 4),
                new WaitCommand(1200),
                new SlowestDriveForwardCommand(drivetrain, 4),
                new WaitCommand(1200),
                new SlowestDriveForwardCommand(drivetrain, 4),
                new WaitCommand(800),
                new InstantCommand(intake::stop, intake),
                new DriveForwardCommand(drivetrain, -30),
                new TurnToCommand(drivetrain, 270),
                new TurnCommand(drivetrain, -5),
                new DriveForwardCommand(drivetrain, 130)
        );
    }
}