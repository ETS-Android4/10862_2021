package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class AutoOuttakeCommand extends SequentialCommandGroup {

    public AutoOuttakeCommand(Lift lift, Intake intake, ArmServos armServos, Drivetrain drivetrain) {

        addCommands(
                new LiftHighCommand(lift),
                new DropFreightCommand(armServos, drivetrain),
                new DriveForwardCommand(drivetrain, -4),
                new WaitCommand(500),
                new LiftResetCommand(armServos, lift)
        );
    }
}