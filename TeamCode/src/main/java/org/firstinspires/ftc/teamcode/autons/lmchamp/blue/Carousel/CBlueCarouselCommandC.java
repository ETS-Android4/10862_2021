package org.firstinspires.ftc.teamcode.autons.lmchamp.blue.Carousel;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftMidCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.KindaSlowDriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

public class CBlueCarouselCommandC extends SequentialCommandGroup {
    public CBlueCarouselCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos, Carousel carousel, SensorColor colorSensor) {
        //declare variables here


        addCommands(
                //distance is in inches
                //Setup
                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 68, true),
                new LiftMidCommand(lift),
                new WaitCommand(1000),

                new KindaSlowDriveForwardCommand(drivetrain, -6.5),
                new DropFreightCommand(armServos),
                new WaitCommand(3000),
                new DriveForwardCommand(drivetrain, 4),
                new InstantCommand(armServos::armUp,armServos),

                new TurnToCommand(drivetrain, 90),
                new LiftResetCommand(armServos, lift),

                new DriveForwardCommand(drivetrain, 23.5)
        );
    }
}