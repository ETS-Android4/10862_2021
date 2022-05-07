package org.firstinspires.ftc.teamcode.commands.DropFreightCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class AutoDropFreightCommand extends SequentialCommandGroup {
    private ArmServos armServos;
    public AutoDropFreightCommand(ArmServos armServos, Drivetrain drivetrain){
        addRequirements(armServos, drivetrain);
        addCommands(
                new InstantCommand(armServos::boxAutoPush),
                new WaitCommand(60)
        );
    }

}
