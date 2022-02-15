package org.firstinspires.ftc.teamcode.commands.LiftCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class AutoLiftResetCommand extends SequentialCommandGroup {
        public AutoLiftResetCommand(ArmServos armServos, Lift lift){
        addCommands(
                new InstantCommand(lift::liftResting, lift),
                new InstantCommand(armServos::armHome, armServos),
                new InstantCommand(armServos::boxOpen, armServos)

        );
    }

}
