package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.AutoDropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CBlueWarehouseCommand extends SequentialCommandGroup {
    public CBlueWarehouseCommand(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
        addCommands(
                new InstantCommand(intake::autoIntake),
                new SplineCommand(drivetrain, new Vector2d(-3.5,36), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new TurnToCommand(drivetrain, 82),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(22.5,   -23), Math.toRadians(20), true),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::autoIntake),
                new SplineCommand(drivetrain, new Vector2d(-3.5, 39), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new TurnToCommand(drivetrain, 82),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(22.5,-14.8), Math.toRadians(22), true),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::autoIntake),
                new SplineCommand(drivetrain, new Vector2d(-3.5, 43), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new TurnToCommand(drivetrain, 86),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(22.8,-12.2), Math.toRadians(23), true),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-4,47), Math.toRadians(90))
        );
    }
}