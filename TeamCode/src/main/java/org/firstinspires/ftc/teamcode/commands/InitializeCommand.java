package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.CapArmCommands.CapArmRestCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

public class InitializeCommand extends SequentialCommandGroup {
    public InitializeCommand(ArmServos armServos, Lift lift, Intake intake, CapServos capServos){
        addCommands(
                new LiftResetCommand(armServos, lift),
                new InstantCommand(intake::servoMid, intake),
                new CapArmRestCommand(capServos)
        );
    }
}