package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Util;
import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.ColorIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
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

import java.util.logging.Level;

public class CRedWarehouseCommandC extends SequentialCommandGroup {
    public CRedWarehouseCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
        addCommands(
                new InstantCommand(capServos::autoMid),
                new SplineCommand(drivetrain, new Vector2d(21,27), Math.toRadians(0)),
                new CapArmMidCommand(capServos, drivetrain),

                new SplineCommand(drivetrain, new Vector2d(-6.5,-37), Math.toRadians(272)),
                new InstantCommand(armServos::boxOpen),
                new InstantCommand(intake::intake),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift),
                new WaitCommand(100),
                new SplineCommand(drivetrain, new Vector2d(16.5,19.3), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new LiftResetCommand(armServos, lift),
                new InstantCommand(armServos::boxOpen),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-6.5,-39), Math.toRadians(272)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift),
                new WaitCommand(100),
                new SplineCommand(drivetrain, new Vector2d(16.2,19.3), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new LiftResetCommand(armServos, lift),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-6.5,-41), Math.toRadians(272)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift),
                new WaitCommand(100),
                new SplineCommand(drivetrain, new Vector2d(16,18.7), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new LiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-6.5,-43), Math.toRadians(270))
        );
    }
}