package org.firstinspires.ftc.teamcode.autons.lm2.red.Warehouse;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.drive.DriveForwardCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class RedWarehouseCommandC extends SequentialCommandGroup {
    public RedWarehouseCommandC(Drivetrain drivetrain, Intake intake, Lift lift, ArmServos armServos) {
        //declare variables here


        addCommands(

        );
    }
}