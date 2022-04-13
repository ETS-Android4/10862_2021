package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.LeftCarouselCommand;
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

                new DriveForwardCommand(drivetrain,-2),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain,-15),

                new TurnToCommand(drivetrain, 270),
                new DriveForwardCommand(drivetrain, -4)
//                new DriveForwardCommand(drivetrain, 21),
//                new TurnToCommand(drivetrain, 320),
//
//                new DriveForwardCommand(drivetrain, 9),
//                new CapArmCarouselCommand(capServos, drivetrain),
//
//                new TurnToCommand(drivetrain, 115),   //To carousel
//                new DriveForwardCommand(drivetrain, 26.8),
//                new RightCarouselCommand(carousel, drivetrain),
//
//                new TurnToCommand(drivetrain, 180),
//                new DriveForwardCommand(drivetrain,-14),
//
//                new TurnToCommand(drivetrain, 270),
//                new DriveForwardCommand(drivetrain, -4)
                );
    }
}

/*new DriveForwardCommand(drivetrain, 20),
                new TurnToCommand(drivetrain, 320),

                new DriveForwardCommand(drivetrain, 10),
                new CapArmMidCommand(capServos, drivetrain),

                new TurnToCommand(drivetrain, 123.5),
                new DriveForwardCommand(drivetrain, 35),
                //new SlowestDriveForwardCommand(drivetrain, 4),
                new RightCarouselCommand(carousel, drivetrain),

                new DriveForwardCommand(drivetrain, -8),
                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain,-14),

                new TurnToCommand(drivetrain, 270),
                new DriveForwardCommand(drivetrain,-9)*/

/*new SplineCommand(drivetrain, new Vector2d(45, -13), Math.toRadians(270)),
        new CapArmMidCommand(capServos, drivetrain),

        new TurnToCommand(drivetrain, 132),
        new DriveForwardCommand(drivetrain, 46),
        new RightCarouselCommand(carousel, drivetrain),

        new RedDuckIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),
        new AutoDropFreightCommand(armServos, drivetrain),

        new DriveForwardCommand(drivetrain,-3),
        new TurnToCommand(drivetrain, 270),

        new DriveForwardCommand(drivetrain, -14)*/