package org.firstinspires.ftc.teamcode.autons.lm2.blue.Warehouse;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DropCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmHighCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmLowCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class BlueWarehouseCommandR extends SequentialCommandGroup {
    public BlueWarehouseCommandR(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos) {
        //declare variables here


        addCommands(
                new InstantCommand(armServos::armUp,armServos),

                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 298),
                new ArmHighCommand(lift),
                new WaitCommand(1000),

                new DriveForwardCommand(drivetrain, -5),
                new DropCommand(armServos),
                new WaitCommand(3000),
                new InstantCommand(lift::liftResting, lift),

                new TurnToCommand(drivetrain, 360),
                new DriveForwardCommand(drivetrain, 28.1),
                new TurnToCommand(drivetrain,90, true),
                new DriveForwardCommand(drivetrain, -20)
        );
    }
}