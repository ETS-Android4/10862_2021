package org.firstinspires.ftc.teamcode.autons.lm2.blue.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.DropCommand;
import org.firstinspires.ftc.teamcode.commands.LeftCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.ResetCommand;
import org.firstinspires.ftc.teamcode.commands.RightCarouselCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmHighCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class BlueCarouselCommandR extends SequentialCommandGroup {
    public BlueCarouselCommandR(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel) {
        //declare variables here


        addCommands(
                //distance is in inches
                //Setup
                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 68, true),
                new ArmHighCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -6.5),
                new DropCommand(armServos),
                new WaitCommand(3000),
                new DriveForwardCommand(drivetrain, 4),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 90),
                new ResetCommand(armServos, lift),

                new DriveForwardCommand(drivetrain, 23.5)
        );
    }
}