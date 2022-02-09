package org.firstinspires.ftc.teamcode.commands.LiftCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class LiftResetCommand extends SequentialCommandGroup {
        public LiftResetCommand(ArmServos armServos, Lift lift){
        addCommands(
                new InstantCommand(armServos::armHome, armServos),
                new InstantCommand(armServos::boxOpen, armServos),
                new InstantCommand(lift::liftResting, lift)
        );
    }

}
