package org.firstinspires.ftc.teamcode.autons.lm2.red.Warehouse;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedWarehouseCommandR extends SequentialCommandGroup {
    public RedWarehouseCommandR(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos) {
        //declare variables here


        addCommands(
                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 60, true),
                //original 60
                new LiftHighCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -5),
                new DropFreightCommand(armServos, drivetrain),
                new WaitCommand(1000),
                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, 2),

                new TurnToCommand(drivetrain, 0),
                new AutoLiftResetCommand(armServos, lift),
                new DriveForwardCommand(drivetrain, 23),
                new TurnCommand(drivetrain,85),
                new DriveForwardCommand(drivetrain, 45)
        );
    }
}