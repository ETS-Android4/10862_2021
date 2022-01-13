package org.firstinspires.ftc.teamcode.commands;

        import static org.firstinspires.ftc.teamcode.subsystems.ArmServos.freightInBox;

        import com.arcrobotics.ftclib.command.ConditionalCommand;
        import com.arcrobotics.ftclib.command.InstantCommand;
        import com.arcrobotics.ftclib.command.ScheduleCommand;
        import com.arcrobotics.ftclib.command.SequentialCommandGroup;
        import com.arcrobotics.ftclib.command.WaitCommand;
        import com.arcrobotics.ftclib.command.WaitUntilCommand;
        import com.qualcomm.robotcore.hardware.ColorSensor;

        import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
        import org.firstinspires.ftc.teamcode.subsystems.Intake;
        import org.firstinspires.ftc.teamcode.subsystems.Lift;
        import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

        import java.time.Instant;

public class IntakeCommand extends SequentialCommandGroup {

    public IntakeCommand(Lift lift, Intake intake, SensorColor colorSensor, ArmServos armServos) {

        addCommands(
                new InstantCommand(intake::servoDown, intake),
                new InstantCommand(intake::intake, intake),
                //new WaitUntilCommand(armServos::ArmServos.freightInBox),
                new InstantCommand(intake::stop),
                new InstantCommand(intake::outtake, intake),
                new WaitCommand(500),
                new InstantCommand(intake::stop, intake),
                new InstantCommand(intake::servoUp,intake)

        );
    }
}