package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.RightCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CRedCarouselCommand extends SequentialCommandGroup {
    public CRedCarouselCommand(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor sensorColor, CapServos capServos) {

        addCommands(
                new DriveForwardCommand(drivetrain, 45),
                new TurnToCommand(drivetrain, 270, true),

                new DriveForwardCommand(drivetrain, 3),
                new CapArmCarouselCommand(capServos, drivetrain),

                new TurnToCommand(drivetrain, 140),   //To carousel
                new DriveForwardCommand(drivetrain, 40),
                new RightCarouselCommand(carousel, drivetrain),

                new DriveForwardCommand(drivetrain,-4),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain,-15),

                new TurnToCommand(drivetrain, 285),
                new DriveForwardCommand(drivetrain, -4)
                );
    }
}