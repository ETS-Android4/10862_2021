package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.CarouselCommand.RightCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class YRedCarouselCommand extends SequentialCommandGroup {
    public YRedCarouselCommand(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor sensorColor, CapServos capServos) {

        addCommands(
                new DriveForwardCommand(drivetrain,10),
                new TurnToCommand(drivetrain, 90, true),
                new DriveForwardCommand(drivetrain, 20),
                new TurnToCommand(drivetrain, 130),

                new DriveForwardCommand(drivetrain, 3),
                new KindaSlowDriveForwardCommand(drivetrain, 4),
                new RightCarouselCommand(carousel, drivetrain),

                new DriveForwardCommand(drivetrain, -4),
                new TurnToCommand(drivetrain, 360, true),
                new DriveForwardCommand(drivetrain, 33)
                );
    }
}