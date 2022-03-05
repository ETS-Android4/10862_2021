package org.firstinspires.ftc.teamcode.commands.CapArmCommands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

public class CapArmWarehouseCommand extends SequentialCommandGroup {
    public CapArmWarehouseCommand(CapServos capServos, Drivetrain drivetrain) {

        addCommands(
                new InstantCommand(capServos::clawOpen, capServos),
                new WaitCommand(200),
                new DriveForwardCommand(drivetrain, -4),
                new TurnToCommand(drivetrain,180),
                new InstantCommand(capServos::capReset, capServos)

        );
    }}

