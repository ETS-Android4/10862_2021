package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmHighCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SplineCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftMidCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftLowCommand;
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

public class CBlueWarehouseCommandL extends SequentialCommandGroup {
    public CBlueWarehouseCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
        //Low
        addCommands(
                new InstantCommand(capServos::autoLow),
                new SplineCommand(drivetrain, new Vector2d(23,   -20.5), Math.toRadians(0)),
                new CapArmMidCommand(capServos, drivetrain),



                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-5,38), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(19,   -17.5), Math.toRadians(0), true),
                new DropFreightCommand(armServos),

                new LiftResetCommand(armServos, lift),
                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-5, 40), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(19,   -17.5), Math.toRadians(0), true),
                new DropFreightCommand(armServos),

                new LiftResetCommand(armServos, lift),
                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new SplineCommand(drivetrain, new Vector2d(-5,44), Math.toRadians(90)),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new LiftHighCommand(lift),
                new SplineCommand(drivetrain, new Vector2d(18,   -11.8), Math.toRadians(0), true),
                new DropFreightCommand(armServos),

                new LiftResetCommand(armServos, lift),
                new SplineCommand(drivetrain, new Vector2d(-10,44), Math.toRadians(90))
        );
    }
}