package org.firstinspires.ftc.teamcode.commands.DropFreightCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class SharedDropFreightCommand extends SequentialCommandGroup {
    private ArmServos armServos;

    public SharedDropFreightCommand(ArmServos armServos, Drivetrain drivetrain){
        addRequirements(armServos, drivetrain);
        addCommands(
                new InstantCommand(armServos::armShared),
                new InstantCommand(armServos::boxPush),
                new DriveForwardCommand(drivetrain, 4)
        );
    }

}
