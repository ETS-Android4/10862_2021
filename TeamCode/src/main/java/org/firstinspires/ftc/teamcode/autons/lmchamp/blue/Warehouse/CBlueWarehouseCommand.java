package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

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
                new SplineCommand(drivetrain, new Vector2d(-5,38), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(19,   -17.5), Math.toRadians(0), true),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-5, 40), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(19,   -17.5), Math.toRadians(0), true),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::autoIntake),
                new SplineCommand(drivetrain, new Vector2d(-5,44), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift, armServos),
                new SplineCommand(drivetrain, new Vector2d(18,   -11.8), Math.toRadians(0), true),
                new TurnCommand(drivetrain, -5),
                new AutoDropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-10,44), Math.toRadians(90))
        );
    }
}