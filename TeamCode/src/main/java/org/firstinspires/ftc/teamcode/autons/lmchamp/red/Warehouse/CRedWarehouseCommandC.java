package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftMidCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CRedWarehouseCommandC extends SequentialCommandGroup {
    public CRedWarehouseCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor colorSensor, CapServos capServos) {
        //declare variables here


        addCommands(
                new DriveForwardCommand(drivetrain, 24),
                new TurnToCommand(drivetrain, 62,true),
                new CapArmMidCommand(capServos, drivetrain),

                //new SplineCommand(drivetrain, new Vector2d(10, 13), 0),

                new TurnToCommand(drivetrain, 0, true),
                new InstantCommand(capServos::capReset, capServos),
                new DriveForwardCommand(drivetrain, -22),
                new TurnToCommand(drivetrain,-93,true),
                new DriveForwardCommand(drivetrain, 32),

                //intake
                //new IntakeCommand(lift, intake, colorSensor, armServos),

                new TurnToCommand(drivetrain,-88,true),
                new DriveForwardCommand(drivetrain, -45),
                new SplineCommand(drivetrain, new Vector2d(-15, -13), 0, true),

                /*new TurnToCommand(drivetrain,-90,true),
                new DriveForwardCommand(drivetrain, -45),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, -20),*/
                new LiftMidCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, 4.5),
                new DropFreightCommand(armServos),
                new KindaSlowDriveForwardCommand(drivetrain, 0.5),
                new WaitCommand(1000),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 0, true),
                new LiftResetCommand(armServos, lift),
                new DriveForwardCommand(drivetrain, -22),
                new TurnCommand(drivetrain,90),
                new DriveForwardCommand(drivetrain, 45)
        );
    }
}