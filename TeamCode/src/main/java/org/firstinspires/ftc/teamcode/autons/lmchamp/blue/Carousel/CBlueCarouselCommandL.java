package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.LeftCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.AutoLiftResetCommand;
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

                new DriveForwardCommand(drivetrain, 10),
                new CapArmMidCommand(capServos, drivetrain),

                new TurnToCommand(drivetrain, 235),
                new DriveForwardCommand(drivetrain, 35),
                new KindaSlowDriveForwardCommand(drivetrain, 5),
                new LeftCarouselCommand(carousel),

                new DriveForwardCommand(drivetrain, -7),
                new TurnToCommand(drivetrain, 210),

                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new DriveForwardCommand(drivetrain,6),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new TurnToCommand(drivetrain, 210),
                new DriveForwardCommand(drivetrain,-31),
                new InstantCommand(intake::stop),

                new InstantCommand(intake::servoUp),
                new LiftHighCommand(lift, armServos),

                new WaitCommand(500),
                new DriveForwardCommand(drivetrain,-2),
                new DropFreightCommand(armServos, drivetrain),
                new AutoLiftResetCommand(armServos, lift),

                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, -32)
        );
    }
}