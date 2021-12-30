package org.firstinspires.ftc.teamcode.commands.LiftCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class LiftLowCommand extends SequentialCommandGroup {
    private Lift lift;
    
    public LiftLowCommand(Lift lift){
        addCommands(
                new InstantCommand(lift::liftLow, lift)
                );
        
    }   
}
