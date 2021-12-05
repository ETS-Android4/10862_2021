package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Carousel;


public class LeftCarouselCommand extends CommandBase {
    private Carousel carousel;
    ElapsedTime timer;

    public LeftCarouselCommand(Carousel carousel) {
        timer = new ElapsedTime();
        this.carousel = carousel;
        this.addRequirements(carousel);
    }

    @Override
    public void initialize(){
        timer.reset();
    }

    @Override
    public void execute(){
        carousel.set(Math.abs(Math.sin(timer.seconds()*2)));
    }

    @Override
    public void end(boolean interrupted){
        carousel.stop();
    }
}
