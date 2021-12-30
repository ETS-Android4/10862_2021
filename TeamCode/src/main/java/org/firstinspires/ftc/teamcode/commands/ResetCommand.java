package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

import java.time.Instant;

public class ResetCommand extends SequentialCommandGroup {
    private ArmServos armServos;
    private Lift lift;

    public ResetCommand(ArmServos armServos, Lift lift){
        addCommands(
                new InstantCommand(lift::liftResting, lift),
                new WaitCommand(100),
                new InstantCommand(armServos::armHome, armServos)
        );
    }

}
