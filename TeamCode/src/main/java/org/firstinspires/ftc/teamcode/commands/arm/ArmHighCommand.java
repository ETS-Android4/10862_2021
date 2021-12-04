package org.firstinspires.ftc.teamcode.commands.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class ArmHighCommand extends SequentialCommandGroup {
    private Lift lift;

    public ArmHighCommand(Lift lift){
        addCommands(
                new InstantCommand(lift::liftHigh, lift)
                );
        
    }   
}
