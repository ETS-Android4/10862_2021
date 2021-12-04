package org.firstinspires.ftc.teamcode.commands.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class ArmLowCommand extends SequentialCommandGroup {
    private Lift lift;
    
    public ArmLowCommand(Lift lift){
        addCommands(
                new InstantCommand(lift::liftLow, lift)
                );
        
    }   
}
