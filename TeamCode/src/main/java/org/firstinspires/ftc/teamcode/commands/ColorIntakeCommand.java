package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class ColorIntakeCommand extends SequentialCommandGroup {

    public ColorIntakeCommand(Lift lift, Intake intake, SensorColor colorSensor, ArmServos armServos) {

            addCommands(
                    new ConditionalCommand(
                            new SequentialCommandGroup(
                                    new InstantCommand(intake::servoMid, intake),
                                    new InstantCommand(intake::outtake, intake),
                                    new WaitCommand(500),
                                    new InstantCommand(intake::stop, intake),
                                    new InstantCommand(armServos::armUp, armServos)),
                            new InstantCommand(),
                            () -> (colorSensor.red() > 150) && (colorSensor.green() > 150) && (ArmServos.boxCanMove)
                    )
            );
    }
}