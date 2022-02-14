package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

import java.time.Instant;

public class TeleOpDropFreightCommand extends SequentialCommandGroup {
    private ArmServos armServos;

    public TeleOpDropFreightCommand(ArmServos armServos, Drivetrain drivetrain){
        addRequirements(armServos, drivetrain);
        addCommands(
                new InstantCommand(armServos::boxOpen),
                new DriveForwardCommand(drivetrain, 6)
        );
    }

}
