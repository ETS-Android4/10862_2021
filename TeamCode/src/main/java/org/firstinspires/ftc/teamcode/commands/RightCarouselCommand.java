package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Carousel;


public class RightCarouselCommand extends SequentialCommandGroup{
    private Carousel carousel;

    public RightCarouselCommand(Carousel carousel){
        addCommands(
                new InstantCommand(carousel::carouselRight, carousel),
                new WaitCommand(4000),
                new InstantCommand(carousel::stop, carousel)
        );
    }
}