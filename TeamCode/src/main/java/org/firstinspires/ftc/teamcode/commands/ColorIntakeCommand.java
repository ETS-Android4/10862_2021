package org.firstinspires.ftc.teamcode.commands;

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

        addCommands(
                new ConditionalCommand(
                        new SequentialCommandGroup(
                                new InstantCommand(armServos::boxClose),
                                new WaitCommand(200),
                                new InstantCommand(armServos::armDrop),
                                new InstantCommand(intake::stop)),

                        new InstantCommand(),
                        () -> (colorSensor.freightInBox()) && (ArmServos.boxCanMove)
                )
        );
    }
}