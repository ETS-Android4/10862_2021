package org.firstinspires.ftc.teamcode.commands.CarouselCommand;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.SlowestDriveForwardCommand;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;


public class RightCarouselCommand extends SequentialCommandGroup{
    private Carousel carousel;
    private Drivetrain drivetrain;

    public RightCarouselCommand(Carousel carousel, Drivetrain drivetrain){
        addCommands(
                new InstantCommand(carousel::carouselRight, carousel),
                new SlowestDriveForwardCommand(drivetrain, 10),
                new WaitCommand(3000),
                new InstantCommand(carousel::stop, carousel)
        );
    }
}