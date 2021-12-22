package org.firstinspires.ftc.teamcode.autons.lmchamp.red.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DropCommand;
import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.RightCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class CRedCarouselCommandL extends SequentialCommandGroup {
    public CRedCarouselCommandL(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel) {
        //declare variables here


        addCommands(
                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, -60, true),
                new ArmLowCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -6.5),
                new DropCommand(armServos),
                new WaitCommand(3000),
                new DriveForwardCommand(drivetrain, 3),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 360),
                new ResetCommand(armServos, lift),
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