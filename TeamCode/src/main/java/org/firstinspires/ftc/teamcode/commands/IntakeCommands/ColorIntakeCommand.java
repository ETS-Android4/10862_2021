package org.firstinspires.ftc.teamcode.commands.IntakeCommands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.arcrobotics.ftclib.command.WaitUntilCommand;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class ColorIntakeCommand extends SequentialCommandGroup {

    public ColorIntakeCommand(Intake intake, SensorColor colorSensor, ArmServos armServos) {
        addRequirements(intake, colorSensor, armServos);
        addCommands(
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand(armServos::armUp),
                                new InstantCommand(armServos::boxClose),
                                new InstantCommand(intake::stop),
                                new WaitCommand(500),
                                new InstantCommand(armServos::armHalfDrop)),

                        new InstantCommand(),
                        () -> (colorSensor.freightInBox()) && (ArmServos.boxCanMove)
                )
        );
    }
}