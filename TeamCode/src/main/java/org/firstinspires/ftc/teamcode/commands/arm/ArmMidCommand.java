package org.firstinspires.ftc.teamcode.commands.arm;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class ArmMidCommand extends SequentialCommandGroup {
    private Lift lift;

    public ArmMidCommand(Lift lift){
        addCommands(
                new InstantCommand(lift::liftMid, lift)
                );
        
    }   
}
