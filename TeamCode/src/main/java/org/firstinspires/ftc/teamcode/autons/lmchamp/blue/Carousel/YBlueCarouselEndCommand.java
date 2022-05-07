package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Carousel;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class YBlueCarouselEndCommand extends SequentialCommandGroup {
    public YBlueCarouselEndCommand(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor sensorColor, CapServos capServos) {

        addCommands(

                new TurnToCommand(drivetrain, 90, true),
                new WaitCommand(150),
                new DriveForwardCommand(drivetrain, 24.2),
                new TurnToCommand(drivetrain, 113),
                new CapArmCarouselCommand(capServos, drivetrain),

                new TurnToCommand(drivetrain, 270),
                new DriveForwardCommand(drivetrain, 16.5),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, 9),

                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, -7)
        );
    }
}