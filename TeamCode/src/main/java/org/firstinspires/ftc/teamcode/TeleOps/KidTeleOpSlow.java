package org.firstinspires.ftc.teamcode.TeleOps;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.DriveCommands.SharedSlowDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SlowDriveCommandforKids;
import org.firstinspires.ftc.teamcode.driveTrain.MatchOpMode;
import org.firstinspires.ftc.teamcode.driveTrain.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@Config
@TeleOp(name = "Kid TeleOp")
public class KidTeleOpSlow extends MatchOpMode {
    // Gamepads
    private GamepadEx driverGamepad, operatorGamepad;

    // Motors and Servos
    private MotorEx leftFront,  leftRear, rightRear,  rightFront;

    // Subsystems
    private Drivetrain drivetrain;

    //Buttons
    private Button slowModeBumper;

    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);

        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();
    }

    @Override
    public void configureButtons() {
        slowModeBumper = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER))    //Slowmode
                .whileHeld(new SharedSlowDriveCommand(drivetrain, driverGamepad));
    }

    @Override
    public void matchLoop() { }
    @Override
    public void disabledPeriodic() { }
    @Override
    public void matchStart() { }
}
