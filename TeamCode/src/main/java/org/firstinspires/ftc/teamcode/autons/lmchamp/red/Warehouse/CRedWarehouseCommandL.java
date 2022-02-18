package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CRedWarehouseCommandL extends SequentialCommandGroup {
    public CRedWarehouseCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
        //Low
        addCommands(
                new InstantCommand(capServos::autoLow),
                new SplineCommand(drivetrain, new Vector2d(22,26.2), Math.toRadians(10)),
                new CapArmLowCommand(capServos, drivetrain),

                new InstantCommand(armServos::boxOpen),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-8,-36.5), Math.toRadians(272)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new WaitCommand(100),
                new SplineCommand(drivetrain, new Vector2d(16.5,19.3), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-8,-38.5), Math.toRadians(272)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new WaitCommand(100),
                new SplineCommand(drivetrain, new Vector2d(16.2,19.3), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-8,-40), Math.toRadians(272)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new WaitCommand(100),
                new SplineCommand(drivetrain, new Vector2d(16,18.7), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-9,-43), Math.toRadians(270))
        );
    }
}