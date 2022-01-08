package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
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
    public CRedCarouselCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor colorSensor, CapServos capServos) {
        //declare variables here


        addCommands(
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, -60, true),
                new CapArmLowCommand(capServos, drivetrain),

                new TurnToCommand(drivetrain, 360),
                new DriveForwardCommand(drivetrain, 25),
                new TurnToCommand(drivetrain, -90,true),
                new DriveForwardCommand(drivetrain,20),

                new WaitCommand(1000),
                new KindaSlowDriveForwardCommand(drivetrain, 3),
                new RightCarouselCommand(carousel),

                new TurnToCommand(drivetrain, 180),
                new DriveForwardCommand(drivetrain, 25),
                new InstantCommand(armServos::armUp,armServos)
        );
    }
}