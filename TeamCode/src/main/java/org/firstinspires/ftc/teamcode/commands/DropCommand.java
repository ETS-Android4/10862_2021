package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

import java.time.Instant;

public class DropCommand extends SequentialCommandGroup {
    private ArmServos armServos;

    public DropCommand(ArmServos armServos){
        addCommands(
                new InstantCommand(armServos::armDrop, armServos),
                new WaitCommand(300),
                new InstantCommand(armServos::boxOpen, armServos)
                );
    }

}
