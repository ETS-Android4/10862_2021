package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmHighCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.CarouselCommand.RightCarouselCommand;
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

public class CRedCarouselCommandL extends SequentialCommandGroup {
    public CRedCarouselCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor sensorColor, CapServos capServos) {
        //declare variables here
        //Low

        addCommands(
                new InstantCommand(capServos::autoLow),
                new DriveForwardCommand(drivetrain, 20),
                new TurnToCommand(drivetrain, 320),

                new DriveForwardCommand(drivetrain, 10),
                new CapArmMidCommand(capServos, drivetrain),

                new TurnToCommand(drivetrain, 130),
                new DriveForwardCommand(drivetrain, 35),
                new KindaSlowDriveForwardCommand(drivetrain, 5),
                new RightCarouselCommand(carousel),

                new DriveForwardCommand(drivetrain, -7),
                new TurnToCommand(drivetrain, 140),

                new InstantCommand(intake::servoDown),
                new InstantCommand(intake::intake),
                new DriveForwardCommand(drivetrain, 6),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain, sensorColor),

                new TurnToCommand(drivetrain, 125),
                new DriveForwardCommand(drivetrain,-31),
                new InstantCommand(intake::stop),

                new InstantCommand(intake::servoUp),
                new LiftHighCommand(lift),

                new WaitCommand(500),
                new DriveForwardCommand(drivetrain,-2),
                new DropFreightCommand(armServos, drivetrain),
                new LiftResetCommand(armServos, lift),

                new TurnToCommand(drivetrain, 270),
                new DriveForwardCommand(drivetrain, -32)
        );
    }
}