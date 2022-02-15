package org.firstinspires.ftc.teamcode.commands.LiftCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class LiftResetCommandT extends SequentialCommandGroup {
        public LiftResetCommandT(ArmServos armServos, Lift lift){
        addCommands(
                new InstantCommand(lift::liftResting, lift),
                new InstantCommand(armServos::armUp, armServos),
                new InstantCommand(armServos::boxOpen, armServos)
        );
    }

}
