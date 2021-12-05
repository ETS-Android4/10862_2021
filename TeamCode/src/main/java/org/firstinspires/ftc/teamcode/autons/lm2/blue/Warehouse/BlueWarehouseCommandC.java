package org.firstinspires.ftc.teamcode.autons.lm2.blue.Warehouse;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.commands.DropCommand;
import org.firstinspires.ftc.teamcode.commands.arm.ArmMidCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.commands.drive.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class BlueWarehouseCommandC extends SequentialCommandGroup {
    public BlueWarehouseCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos) {
        //declare variables here


        addCommands(
                //Setup
                new InstantCommand(armServos::armUp,armServos),

                new DriveForwardCommand(drivetrain, -24),
                new TurnToCommand(drivetrain, 60, true),
                new ArmMidCommand(lift),
                new WaitCommand(1000),

                new DriveForwardCommand(drivetrain, -5),
                new DropCommand(armServos),
                new WaitCommand(3000),
                new InstantCommand(lift::liftResting, lift),

                new TurnToCommand(drivetrain, 90),
                new DriveForwardCommand(drivetrain, 33),
                new TurnToCommand(drivetrain,0),
                new DriveForwardCommand(drivetrain, 18)

                //distance is in inches
                //The weird makes the robot go opposite direction

                //one is freight pickup
                //zero is freight drop
        );
    }
}