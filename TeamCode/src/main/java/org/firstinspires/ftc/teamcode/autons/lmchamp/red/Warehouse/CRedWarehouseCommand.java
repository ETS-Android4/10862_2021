package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.IntakeCommands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.AutoDropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CRedWarehouseCommand extends SequentialCommandGroup {
    public CRedWarehouseCommand(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
        addCommands(
                new InstantCommand(intake::autoIntake),
                new SplineCommand(drivetrain, new Vector2d(-8,-32), Math.toRadians(270)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(17.8,16), Math.toRadians(353), true),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::autoIntake),
                new SplineCommand(drivetrain, new Vector2d(-8,-37), Math.toRadians(270)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(18.2,14.5), Math.toRadians(350), true),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::autoIntake),
                new SplineCommand(drivetrain, new Vector2d(-8,-43), Math.toRadians(270)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(16.85,11), Math.toRadians(355), true),
                new TurnCommand(drivetrain, -5),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-8,-43), Math.toRadians(270))
        );
    }
}