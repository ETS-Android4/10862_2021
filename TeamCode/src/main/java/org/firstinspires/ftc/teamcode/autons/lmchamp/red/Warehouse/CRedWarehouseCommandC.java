package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Warehouse;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

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

public class CRedWarehouseCommandC extends SequentialCommandGroup {
    public CRedWarehouseCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
//kind of good

        addCommands(
                new InstantCommand(capServos::autoMid),
                new SplineCommand(drivetrain, new Vector2d(20,20), Math.toRadians(0)),
                new CapArmMidCommand(capServos, drivetrain),

                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),

                new SplineCommand(drivetrain, new Vector2d(0,-35), Math.toRadians(180)),
                new TurnToCommand(drivetrain, 90),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
                new SplineCommand(drivetrain, new Vector2d(20,20), Math.toRadians(180),true),

                new LiftHighCommand(lift),
                new DriveForwardCommand(drivetrain, -30),
                new DropFreightCommand(armServos),

                new DriveForwardCommand(drivetrain, 30),
                new LiftResetCommand(armServos, lift),
                new TurnToCommand(drivetrain,-93, true),

                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                //outOfWarehouse
                new TurnToCommand(drivetrain,-87,true),
                new DriveForwardCommand(drivetrain, -30),
                new TurnToCommand(drivetrain, -150, true),

                new LiftHighCommand(lift),
                new DriveForwardCommand(drivetrain, -30),

                new DropFreightCommand(armServos),
                new WaitCommand(250),

                new DriveForwardCommand(drivetrain, 30),
                new TurnCommand(drivetrain,90),
                new LiftResetCommand(armServos, lift)
        );
    }
}