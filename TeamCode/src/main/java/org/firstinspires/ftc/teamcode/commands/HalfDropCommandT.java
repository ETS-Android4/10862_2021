package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;

public class HalfDropCommandT extends SequentialCommandGroup {
    private ArmServos armServos;
    public HalfDropCommandT(ArmServos armServos) {
        addRequirements(armServos);
        addCommands(
                new InstantCommand(armServos::boxClose, armServos),
                new InstantCommand(armServos::armHalfDrop, armServos)
        );
    }
}
