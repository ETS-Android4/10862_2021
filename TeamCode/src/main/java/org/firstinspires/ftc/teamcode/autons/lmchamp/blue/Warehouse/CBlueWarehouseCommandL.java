package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DropCommand;
import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class CBlueWarehouseCommandL extends SequentialCommandGroup {
    public CBlueWarehouseCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos) {
        //declare variables here


        addCommands(
                //Setup
                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 298),
                new ArmLowCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -4.5),
                new DropCommand(armServos),
                new KindaSlowDriveForwardCommand(drivetrain, -0.5),
                new WaitCommand(1000),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 0, true),
                new ResetCommand(armServos, lift),
                new DriveForwardCommand(drivetrain, 22),
                new TurnCommand(drivetrain,90),
                new DriveForwardCommand(drivetrain, -40)

                //intake
                new IntakeCommand(lift, intake),
                new ColorIntakeCommand(lift, intake, colorSensor),

                new DriveForwardCommand(drivetrain, 40),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 298),
                new ArmLowCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -4.5),
                new DropCommand(armServos),
                new KindaSlowDriveForwardCommand(drivetrain, -0.5),
                new WaitCommand(1000),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 0, true),
                new ResetCommand(armServos, lift),
                new DriveForwardCommand(drivetrain, 22),
                new TurnCommand(drivetrain,90),
                new DriveForwardCommand(drivetrain, -40)
        );
    }
}