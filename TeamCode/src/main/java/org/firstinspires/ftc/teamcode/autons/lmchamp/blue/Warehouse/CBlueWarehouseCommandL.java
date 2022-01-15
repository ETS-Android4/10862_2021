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
        //High
        addCommands(
                new InstantCommand(capServos::autoHigh),
                new SplineCommand(drivetrain, new Vector2d(25,   -20), Math.toRadians(0)),
                new CapArmHighCommand(capServos, drivetrain),
                new SplineCommand(drivetrain, new Vector2d(-5,0), Math.toRadians(90)),
                new TurnToCommand(drivetrain, 93),
//
                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new DriveForwardCommand(drivetrain,32),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new TurnToCommand(drivetrain,87),
                new DriveForwardCommand(drivetrain,-32),
                new LiftHighCommand(lift),

                new TurnToCommand(drivetrain, 155),
                new DriveForwardCommand(drivetrain, -30),

                new DropFreightCommand(armServos),
                new LiftResetCommand(armServos, lift),

                new SplineCommand(drivetrain, new Vector2d(-5,0), Math.toRadians(90)),
                new TurnToCommand(drivetrain, 93),
//
                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new DriveForwardCommand(drivetrain,32),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new TurnToCommand(drivetrain,87),
                new DriveForwardCommand(drivetrain,-32),
                new LiftHighCommand(lift),

                new TurnToCommand(drivetrain, 155),
                new DriveForwardCommand(drivetrain, -30),

                new DropFreightCommand(armServos),
                new LiftResetCommand(armServos, lift),

                new SplineCommand(drivetrain, new Vector2d(-30,13), Math.toRadians(90))
        );
    }
}