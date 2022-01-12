package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.AutoIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.AutoOuttakeCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftHighCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftMidCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
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

public class CRedCarouselCommandC extends SequentialCommandGroup {
    public CRedCarouselCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor colorSensor, CapServos capServos) {
        //declare variables here


        addCommands(
                new DriveForwardCommand(drivetrain, 24),
                new TurnToCommand(drivetrain, -50, true),
                new CapArmMidCommand(capServos, drivetrain),

                new WaitCommand(1000),
                new TurnToCommand(drivetrain, 125, true),
                new DriveForwardCommand(drivetrain, 27),

                new WaitCommand(300),
                new KindaSlowDriveForwardCommand(drivetrain, 4),
                new RightCarouselCommand(carousel),

                new KindaSlowDriveForwardCommand(drivetrain, -4),
                new TurnToCommand(drivetrain, 140),
                new AutoIntakeCommand(lift, intake, armServos, drivetrain),
                new TurnToCommand(drivetrain,125),
                new DriveForwardCommand(drivetrain, -27),
                new AutoOuttakeCommand(lift, intake, armServos, drivetrain),
                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, 18)

                /*new TurnToCommand(drivetrain, 180, true),
                new DriveForwardCommand(drivetrain, -25),

                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, 5)*/
        );
    }
}