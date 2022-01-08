package org.firstinspires.ftc.teamcode.commands.LiftCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class LiftHighCommand extends SequentialCommandGroup {
    //private Lift lift;
    public LiftHighCommand(Lift lift){
        addCommands(
                new InstantCommand(lift::liftHigh, lift)
                );
        
    }   
}
