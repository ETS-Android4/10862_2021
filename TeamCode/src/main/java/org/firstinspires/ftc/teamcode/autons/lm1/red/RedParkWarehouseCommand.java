package org.firstinspires.ftc.teamcode.autons.lm1.red;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

public class RedParkWarehouseCommand extends SequentialCommandGroup {
    public RedParkWarehouseCommand(Drivetrain drivetrain, Intake intake, Telemetry telemetry) {
        //declare variables here

        addCommands(
                new DriveForwardCommand(drivetrain, 20),
                new TurnToCommand(drivetrain, -93, true),
                new DriveForwardCommand(drivetrain, 90)
        );
    }
}