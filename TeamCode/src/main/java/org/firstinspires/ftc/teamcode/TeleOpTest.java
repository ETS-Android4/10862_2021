package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.CRServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.commands.drive.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.SlowDriveCommand;
import org.firstinspires.ftc.teamcode.drive.MatchOpMode;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Config
@TeleOp(name = "Blue Teleop")
public class TeleOpTest extends MatchOpMode {
    // Motors
    private MotorEx leftFront,  leftRear, rightRear,  rightFront;
    private MotorEx liftMotor;
    private MotorEx intakeMotor;
    private MotorEx carouselMotor;
    private ServoEx armServo, dropServo;

    // Gamepad
    private GamepadEx driverGamepad, operatorGamepad;

    // Subsystems
    private Drivetrain drivetrain;
    private Lift lift;
    private Intake intake;
    private ArmServos armServos;
    private Carousel carousel;

    //Buttons
    private Button intakeButton, halfSpeedButton, outtakeButton;
    private Button slowModeTrigger;
    public Button liftUpButton, liftDownButton;
    public Button liftRestButton, liftLowButton, liftMidButton, liftHighButton, liftCapButton;
    public Button armServoHomeButton, armServoDropButton;
    public Button dropServoCloseButton, dropServoOpenButton;
    public Button carouselRightButton, carouselLeftButton;

    @Override
    public void robotInit() {

        // Intake hardware Initializations
        intakeMotor = new MotorEx(hardwareMap, "intake");
        // Lift hardware initializations
        liftMotor = new MotorEx(hardwareMap, "lift");
        //carouse hardware initializations
        carouselMotor = new MotorEx(hardwareMap, "carousel");
        // Servos hardware initializations
        armServo = new SimpleServo(hardwareMap,"arm", 0, 180);
        dropServo = new SimpleServo(hardwareMap, "drop",0,180);

        // Subsystems
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap),telemetry);
        drivetrain.init();
        intake = new Intake(intakeMotor, telemetry);
        lift = new Lift(liftMotor, telemetry);
        armServos = new ArmServos(armServo, dropServo, telemetry);
        carousel = new Carousel(carouselMotor, telemetry);

        //gamepad1.setJoystickDeadzone(0.0f);
        driverGamepad = new GamepadEx(gamepad1);
        operatorGamepad = new GamepadEx(gamepad2);
        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));

    }

    @Override
    public void configureButtons() {

        slowModeTrigger = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER)).whileHeld(new SlowDriveCommand(drivetrain, driverGamepad));

        //intake
        intakeButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER).whileHeld(intake::intake).whenReleased(intake::stop));
        outtakeButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER).whileHeld(intake::outtake).whenReleased(intake::stop));

        //lift
        liftUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_UP).whenPressed(lift::liftLow));
        liftDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN).whenPressed(lift::liftResting));
        liftLowButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.X).whenPressed(lift::liftLow));
        liftMidButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.B).whenPressed(lift::liftMid));
        liftHighButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y).whenPressed(lift::liftHigh));
        liftCapButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT).whenPressed(lift::liftCap));

        //drop servos
        dropServoOpenButton= (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER).whenPressed(armServos::boxOpen));
        dropServoCloseButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER).whenPressed(armServos::boxClose));

        //reset
        liftRestButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A).whenPressed(lift::liftResting));
        armServoHomeButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A).whenPressed(armServos::reset));


        //carousel
        carouselLeftButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER).whileHeld(carousel::carouselRight).whenReleased(carousel::stop));
        carouselRightButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER).whileHeld(carousel::carouselLeft).whenReleased(carousel::stop));


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

        liftArmButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_UP)).whenPressed(wobbleGoalArm::liftWobbleGoal);
        lowerArmButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)).whenPressed(wobbleGoalArm::placeWobbleGoal);


        (new GamepadButton(driverGamepad, GamepadKeys.Button.BACK)).whenPressed(() -> shooterWheels.adjustShooterRPM(75));
        (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_LEFT)).whenPressed(() -> shooterWheels.adjustShooterRPM(-75));
        lowMidWobbleButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_RIGHT)).whenPressed(() -> wobbleGoalArm.setWobbleGoal(-65));
        (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_STICK_BUTTON)).toggleWhenPressed(new InstantCommand(intake::dropIntake, intake), new InstantCommand(intake::liftIntake, intake));
        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));
        (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y)).whenPressed(wobbleGoalArm::liftArmManual).whenReleased(wobbleGoalArm::stopArm);
        (new GamepadButton(operatorGamepad, GamepadKeys.Button.X)).whenPressed(wobbleGoalArm::lowerArmManual).whenReleased(wobbleGoalArm::stopArm);
 */
        //drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void matchStart() {
    }

    @Override
    public void robotPeriodic() {
    }
}
