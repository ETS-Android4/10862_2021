package org.firstinspires.ftc.teamcode.TeleOps;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.commands.ColorIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.driveTrain.MatchOpMode;
import org.firstinspires.ftc.teamcode.driveTrain.SampleTankDrive;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

@Config
@TeleOp(name = "Blue TeleOp")
public class BlueTeleOp extends MatchOpMode {
    // Gamepads
    private GamepadEx driverGamepad, operatorGamepad;

    // Motors and Servos
    private MotorEx leftFront,  leftRear, rightRear,  rightFront;
    private MotorEx liftMotor;
    private MotorEx intakeMotor;
    private CRServo carouselServo;
    private ServoEx armServo, dropServo;
    private ServoEx intakeServo;
    private ColorSensor colorSensor;
    private ServoEx capArmServo, clawServo;

    // Subsystems
    private Drivetrain drivetrain;
    private Lift lift;
    private Intake intake;
    private ArmServos armServos;
    private Carousel carousel;
    private SensorColor sensorColor;
    private CapServos capServos;

    //Buttons
    private Button intakeTrigger, outtakeTrigger;
    private Button slowModeBumper;
    public Button liftManualUpButton, liftManualDownButton;
    public Button liftLowButton, liftMidButton, liftHighButton;
    public Button carouselRightTrigger, carouselLeftTrigger;
    public Button resetEveryThingButton;
    public Button dropFreightButton, resetBoxButton, upBoxButton;
    public Button intakeUpButton, intakeDownButton, intakeMiddleButton;
    public Button clawOpenButton, clawCloseButton;
    public Button capArmHighButton;
    public Button slideResetButton;
    //TODO:Add clawarm buttons, maybe manual buttons

    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);

        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        drivetrain.init();
        intake = new Intake(intakeMotor, intakeServo, telemetry, hardwareMap);
        lift = new Lift(liftMotor, telemetry, hardwareMap);
        armServos = new ArmServos(armServo, dropServo, telemetry, hardwareMap);
        carousel = new Carousel(hardwareMap, telemetry);
        capServos = new CapServos(clawServo, capArmServo, telemetry, hardwareMap);

        //gamepad1.setJoystickDeadzone(0.0f);

        //drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));

        sensorColor = new SensorColor(hardwareMap, telemetry, "colorSensor");
        intake.setDefaultCommand(new ColorIntakeCommand(lift, intake, sensorColor, armServos));
    }

    @Override
    public void configureButtons() {
        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));

        //slowmode for the drivetrain
        slowModeBumper = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER)).whileHeld(new SlowDriveCommand(drivetrain, driverGamepad));

        //intake
        outtakeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER).whileHeld(intake::outtake).whenReleased(intake::stop));
        intakeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER).whileHeld(intake::intake).whenReleased(intake::stop));

        //Intake positions
        intakeUpButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_UP)). whenPressed(intake::servoUp);
        intakeMiddleButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_RIGHT)). whenPressed(intake::servoMid);
        intakeDownButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)). whenPressed(intake::servoDown);

        //lift commands
        liftManualUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT).whenPressed(lift::liftManual).whenReleased(lift::stopLift));
        liftManualDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT).whenPressed(lift::lowerLiftManual).whenReleased(lift::stopLift));

        //reset everything
        resetEveryThingButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN)).whenPressed(
                new LiftResetCommand(armServos, lift)
        );

        //Lift positions
        liftLowButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.X).whenPressed(lift::liftLow));
        liftMidButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.B).whenPressed(lift::liftMid));
        liftHighButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y).whenPressed(lift::liftHigh));

        //carousel
        carouselLeftTrigger = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_STICK_BUTTON).whileHeld(carousel::carouselLeft).whenReleased(carousel::stop));
        carouselRightTrigger = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER).whileHeld(carousel::carouselRight).whenReleased(carousel::stop));

        //Outaking the freight motion
        dropFreightButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER)).whenPressed(
                new DropFreightCommand(armServos)
        );

        //Box servos stuff
        upBoxButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A)).whenPressed(armServos::armUp);
        resetBoxButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER)).whenPressed(armServos::armHome);

        slideResetButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.BACK)).whenPressed(() -> liftMotor.liftEncoderReset);
/*
        singleFeedButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.Y)).whenPressed(new FeedRingsCommand(feeder, 1));
        // TRIPLE SHOT SPEED *********************
        tripleFeedButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER)).whenPressed(new SmartFeedRingsCommand(feeder, shooterWheels,4,30));
        shootButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER)).toggleWhenPressed(
                new InstantCommand(() -> shooterWheels.setShooterRPM(ShooterWheels.TARGET_SPEED), shooterWheels),
                new InstantCommand(() -> shooterWheels.setShooterRPM(0), shooterWheels));
        (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_STICK_BUTTON)).whileHeld(new VisionCommand(drivetrain, vision, 30));
        powershotButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.B)).toggleWhenPressed(
                new InstantCommand(() -> shooterWheels.setShooterRPM(2900), shooterWheels),
                new InstantCommand(() -> shooterWheels.setShooterRPM(0), shooterWheels));
        intakeButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)).whileHeld(intake::intake).whenReleased(intake::stop);
        outtakeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.X)).whileHeld(intake::outtake).whenReleased(intake::stop);

        toggleClawButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.A)).toggleWhenPressed(
                new InstantCommand(wobbleGoalArm::openClaw, wobbleGoalArm),
                new InstantCommand(wobbleGoalArm::closeClaw, wobbleGoalArm)
        );

        (new GamepadButton(driverGamepad, GamepadKeys.Button.BACK)).whenPressed(() -> shooterWheels.adjustShooterRPM(75));
        (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_LEFT)).whenPressed(() -> shooterWheels.adjustShooterRPM(-75));
        lowMidWobbleButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_RIGHT)).whenPressed(() -> wobbleGoalArm.setWobbleGoal(-65));
        (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_STICK_BUTTON)).toggleWhenPressed(new InstantCommand(intake::dropIntake, intake), new InstantCommand(intake::liftIntake, intake));
        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));
        (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y)).whenPressed(wobbleGoalArm::liftArmManual).whenReleased(wobbleGoalArm::stopArm);
        (new GamepadButton(operatorGamepad, GamepadKeys.Button.X)).whenPressed(wobbleGoalArm::lowerArmManual).whenReleased(wobbleGoalArm::stopArm);
 */

    }

    @Override
    public void matchLoop() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void matchStart() {
    }

    @Override
    public void robotPeriodic() {
        sensorColor.periodic();
    }
}
