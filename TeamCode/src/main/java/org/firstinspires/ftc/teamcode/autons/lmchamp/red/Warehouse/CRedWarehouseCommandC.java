package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftMidCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CRedWarehouseCommandC extends SequentialCommandGroup {
    public CRedWarehouseCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor colorSensor) {
        //declare variables here


        addCommands(
                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 298),
                new LiftMidCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -4.5),
                new DropFreightCommand(armServos),
                new KindaSlowDriveForwardCommand(drivetrain, -0.5),
                new WaitCommand(1000),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 0, true),
                new LiftResetCommand(armServos, lift),
                new DriveForwardCommand(drivetrain, 22),
                new TurnCommand(drivetrain,90),
                new DriveForwardCommand(drivetrain, -45),

                //intake
                new IntakeCommand(lift, intake, colorSensor, armServos),

                new DriveForwardCommand(drivetrain, 45),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 298),
                new LiftMidCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -4.5),
                new DropFreightCommand(armServos),
                new KindaSlowDriveForwardCommand(drivetrain, -0.5),
                new WaitCommand(1000),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 0, true),
                new LiftResetCommand(armServos, lift),
                new DriveForwardCommand(drivetrain, 22),
                new TurnCommand(drivetrain,90),
                new DriveForwardCommand(drivetrain, -45)
        );
    }
}