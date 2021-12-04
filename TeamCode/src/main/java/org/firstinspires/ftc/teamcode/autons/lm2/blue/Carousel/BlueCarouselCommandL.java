package org.firstinspires.ftc.teamcode.autons.lm2.blue.Carousel;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.arm.ArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.DropCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class BlueCarouselCommandL extends SequentialCommandGroup {
    public BlueCarouselCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos) {
        //declare variables here


        addCommands(
                //distance is in inches
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 45, true),
                new ArmLowCommand(lift),
                new DriveForwardCommand(drivetrain, -24),
                new DropCommand(armServos),
                new DriveForwardCommand(drivetrain, 24),
                new TurnToCommand(drivetrain, 0),
                new DriveForwardCommand(drivetrain, 24.5),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, 24),
                //carousel
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, 24)
        );
    }
}