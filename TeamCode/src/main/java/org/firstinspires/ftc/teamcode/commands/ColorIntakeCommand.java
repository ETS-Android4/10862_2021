package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.ConditionalCommand;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.ScheduleCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

import java.time.Instant;

public class ColorIntakeCommand extends SequentialCommandGroup {

    public ColorIntakeCommand(Lift lift, Intake intake, SensorColor colorSensor) {

            addCommands(
                    new ConditionalCommand(
                            new SequentialCommandGroup(
                                    new InstantCommand(intake::servoMid, intake),
                                    new InstantCommand(intake::outtake, intake),
                                    new WaitCommand(1000),
                                    new InstantCommand(lift::liftLow, lift),
                                    new InstantCommand(intake::stop, intake)),
                            new InstantCommand(),
                            () -> (colorSensor.red() > 200) && (colorSensor.green() > 200)
                    )
            );
    }


}