package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class AutoIntakeCommand extends SequentialCommandGroup {

    public AutoIntakeCommand(Lift lift, Intake intake, ArmServos armServos, Drivetrain drivetrain, SensorColor sensorColor) {

        addCommands(
                new WaitUntilCommand(sensorColor::freightInBox).withTimeout(500),
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand(intake::outtake),
                                new InstantCommand(intake::servoUp),
                                new InstantCommand(armServos::armUp),
                                new DriveForwardCommand(drivetrain, -2)
                        ),
                        new SequentialCommandGroup(
                                new DriveForwardCommand(drivetrain,2),
                                new InstantCommand(intake::outtake),
                                new InstantCommand(intake::servoUp),
                                new InstantCommand(armServos::armUp),
                                new DriveForwardCommand(drivetrain, -4)
                        ),
                        sensorColor::freightInBox
                )

        );
    }
}