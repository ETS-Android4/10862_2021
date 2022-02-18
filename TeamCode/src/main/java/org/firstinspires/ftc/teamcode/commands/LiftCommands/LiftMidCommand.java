package org.firstinspires.ftc.teamcode.commands.LiftCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class LiftMidCommand extends SequentialCommandGroup {
    private Lift lift;

    public LiftMidCommand(Lift lift, ArmServos armServos){
        addCommands(
                new InstantCommand(lift::liftMid, lift),
                new InstantCommand(armServos::armDrop ,armServos)
                );
        
    }   
}
