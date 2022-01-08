package org.firstinspires.ftc.teamcode.commands.CapArmCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class CapArmRestCommand extends SequentialCommandGroup {
    public CapArmRestCommand(CapServos capServos) {
        addCommands(
                new InstantCommand(capServos::clawClose, capServos),
                new InstantCommand(capServos::capReset, capServos)
        );
    }}