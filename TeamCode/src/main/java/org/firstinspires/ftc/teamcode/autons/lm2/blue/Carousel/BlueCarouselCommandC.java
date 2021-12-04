package org.firstinspires.ftc.teamcode.autons.lm2.blue.Carousel;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.LeftCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class BlueCarouselCommandC extends SequentialCommandGroup {
    public BlueCarouselCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel) {
        //declare variables here


        addCommands(
                //distance is in inches
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 45, true),
                new ArmMidCommand(lift),
                new DriveForwardCommand(drivetrain, -24),
                //servo deposit
                new DriveForwardCommand(drivetrain, 24),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, 24.5),
                new TurnToCommand(drivetrain, 170),
                new DriveForwardCommand(drivetrain, 24),
                //carousel
                new LeftCarouselCommand(carousel),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, 24)

                //distance is in inches
                //The weird makes the robot go opposite direction

                //one is freight pickup
                //zero is freight drop
        );
    }
}