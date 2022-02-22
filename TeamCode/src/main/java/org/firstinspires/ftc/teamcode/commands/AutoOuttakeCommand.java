package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class AutoOuttakeCommand extends SequentialCommandGroup {

    public AutoOuttakeCommand(Lift lift, Intake intake, ArmServos armServos, Drivetrain drivetrain) {
        addRequirements(lift, intake, armServos, drivetrain);
        addCommands(
                new LiftHighCommand(lift, armServos),
                new DropFreightCommand(armServos, drivetrain),
                new DriveForwardCommand(drivetrain, -4),
                new WaitCommand(500),
                new AutoLiftResetCommand(armServos, lift)
        );
    }
}