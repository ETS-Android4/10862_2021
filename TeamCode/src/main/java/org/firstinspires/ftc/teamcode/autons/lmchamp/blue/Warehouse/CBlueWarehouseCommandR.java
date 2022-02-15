package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CBlueWarehouseCommandR extends SequentialCommandGroup {
    public CBlueWarehouseCommandR(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor sensorColor, CapServos capServos) {
        //High
        addCommands(
                new InstantCommand(capServos::autoHigh),
                new SplineCommand(drivetrain, new Vector2d(22.5,   -20.5), Math.toRadians(0)),
                new CapArmMidCommand(capServos, drivetrain),

                new InstantCommand(armServos::boxOpen),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-5,38), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(19.5,   -17.5), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-5, 40), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(19.5,   -17.5), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-5,44), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(19.2,   -12.8), Math.toRadians(0), true),
                new DropFreightCommand(armServos, drivetrain),

                new AutoLiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-10,42), Math.toRadians(90))
        );
    }
}