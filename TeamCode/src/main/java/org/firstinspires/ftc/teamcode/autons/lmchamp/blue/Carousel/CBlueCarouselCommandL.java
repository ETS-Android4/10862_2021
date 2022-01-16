package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmHighCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.LeftCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.RightCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftLowCommand;
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

public class CBlueCarouselCommandL extends SequentialCommandGroup {
    public CBlueCarouselCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
        //Low
        addCommands(
                new InstantCommand(capServos::autoLow),
                new DriveForwardCommand(drivetrain, 20),
                new TurnToCommand(drivetrain, 60, true),

                new DriveForwardCommand(drivetrain, 8),
                new CapArmLowCommand(capServos, drivetrain),

                new TurnToCommand(drivetrain, 235),
                new DriveForwardCommand(drivetrain, 27),
                new KindaSlowDriveForwardCommand(drivetrain, 4),
                new LeftCarouselCommand(carousel),

                new DriveForwardCommand(drivetrain, -6),
                new TurnToCommand(drivetrain, 210),

                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new DriveForwardCommand(drivetrain,6),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new TurnToCommand(drivetrain, 245),
                new DriveForwardCommand(drivetrain,-33),
                new InstantCommand(intake::servoUp),
                new LiftHighCommand(lift),

                new WaitCommand(500),
                new DriveForwardCommand(drivetrain,-3),
                new DropFreightCommand(armServos),
                new LiftResetCommand(armServos, lift),

                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, -28),

                new InstantCommand(intake::stop)
        );
    }
}