package org.firstinspires.ftc.teamcode.autons.lm2.red.Carousel;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedCarouselCommandC extends SequentialCommandGroup {
    public RedCarouselCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos) {
        //declare variables here


        addCommands(
                //distance is in inches
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 315, true),
                //arm - Mid
                new DriveForwardCommand(drivetrain, -24),
                //servo deposit
                new DriveForwardCommand(drivetrain, 24),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, 24.5),
                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, 24),
                //carousel
                new DriveForwardCommand(drivetrain, -2),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, 24)
                //distance is in inches
                //The weird makes the robot go opposite direction
        );
    }
}