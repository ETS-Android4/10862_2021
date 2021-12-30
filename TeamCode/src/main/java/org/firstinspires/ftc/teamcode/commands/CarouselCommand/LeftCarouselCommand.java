package org.firstinspires.ftc.teamcode.commands.CarouselCommand;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Carousel;


public class LeftCarouselCommand extends SequentialCommandGroup{
    private Carousel carousel;

    public LeftCarouselCommand(Carousel carousel){
        addCommands(
                new InstantCommand(carousel::carouselLeft, carousel),
                new WaitCommand(4000),
                new InstantCommand(carousel::stop, carousel)
        );
    }
}