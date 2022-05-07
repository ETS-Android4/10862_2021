package org.firstinspires.ftc.teamcode.TeleOps;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.TeleOpDropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.IntakeCommands.ColorIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SharedSlowDriveCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommandT;
import org.firstinspires.ftc.teamcode.commands.HalfDropCommandT;
import org.firstinspires.ftc.teamcode.driveTrain.MatchOpMode;
import org.firstinspires.ftc.teamcode.driveTrain.SampleTankDrive;
import org.firstinspires.ftc.teamcode.subsystems.ArmServos;
import org.firstinspires.ftc.teamcode.subsystems.CapServos;
import org.firstinspires.ftc.teamcode.subsystems.Carousel;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.SensorColor;

@Config
@TeleOp(name = "Red TeleOp")
public class RedTeleOp extends MatchOpMode {
    // Gamepads
    private GamepadEx driverGamepad, operatorGamepad;

    // Motors and Servos
    private ColorSensor colorSensor;
    private MotorEx leftFront,  leftRear, rightRear,  rightFront;
    private MotorEx liftMotor, liftMotor2;
    private MotorEx intakeMotor;

    private ServoEx intakeServo;
    private CRServo carouselServo;
    private ServoEx armServo, dropServo;

    private ServoEx capArmServo, realCapArmServo, clawServo;

    // Subsystems
    private SensorColor sensorColor;
    private Drivetrain drivetrain;
    private Lift lift;
    private Intake intake;
    private Carousel carousel;
    private ArmServos armServos;
    private CapServos capServos;

    // Buttons
    private Button intakeTrigger, outtakeTrigger;
    private Button slowModeBumper;
    public Button liftManualUpButton, liftManualDownButton;
    public Button liftLowButton, liftMidButton, liftHighButton;
    public Button carouselRightTrigger, carouselLeftTrigger;
    public Button resetEveryThingButton;
    public Button dropFreightButton, upBoxButton;
    public Button intakeClawUpButton;
    public Button intakeClawDownButton;
    public Button outRealCapHomeTrigger;
    public Button inRealCapHomeTrigger;
    public Button realClawHomeButton, realClawMidButton, realClawLowButton;
    public Button slideResetButton;


    @Override
    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);    //Used for button config
        operatorGamepad = new GamepadEx(gamepad2);

        //Subsystems
        sensorColor = new SensorColor(hardwareMap, telemetry, "colorSensor");
        drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
        lift = new Lift(liftMotor, liftMotor2, telemetry, hardwareMap);
        intake = new Intake(intakeMotor, intakeServo, telemetry, hardwareMap);
        carousel = new Carousel(hardwareMap, telemetry);
        armServos = new ArmServos(armServo, dropServo, telemetry, hardwareMap);
        capServos = new CapServos(clawServo, capArmServo, realCapArmServo, telemetry, hardwareMap);

        intake.setDefaultCommand(new ColorIntakeCommand(intake, sensorColor, armServos));   //Color Sensor default command
        drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));   //Drivetrain default command
        drivetrain.init();
    }

    @Override
    public void configureButtons() {
        slowModeBumper = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER))    //Slowmode
                .whileHeld(new SharedSlowDriveCommand(drivetrain, driverGamepad));



        outtakeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)   //Outtake
                .whileHeld(intake::outtake)
                .whenReleased(intake::stop));
        intakeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)   //Intake
                .whileHeld(intake::intake)
                .whenReleased(intake::stop));



        liftManualUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT)     //Manual Lift Up
                .whenPressed(lift::liftManual)
                .whenReleased(lift::stopLift));
        liftManualDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT)    //Manual Lift Down
                .whenPressed(lift::lowerLiftManual)
                .whenReleased(lift::stopLift));

        slideResetButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.BACK))    //Reset encoders (hopefully)
                .whenPressed(lift::reset);

        resetEveryThingButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN))  //Resets Lift/Servos
                .whenPressed(new LiftResetCommandT(armServos, lift))
                .whenPressed(new InstantCommand(lift::liftResting, lift))
                .whenPressed(new InstantCommand(capServos::clawOpen));

        liftLowButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.X)   //Lift Low
                .whenPressed(lift::liftLow));
        liftMidButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.B)   //Lift Mid
                .whenPressed(lift::liftMid));
        liftHighButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y)  //Lift Shared High
                .whenPressed(lift::liftSharedHigh));

        dropFreightButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER))    //Outtake Freight
                .whenPressed(new TeleOpDropFreightCommand(armServos,drivetrain));
        dropFreightButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER))    //Outtake Freight Driver
                .whenPressed(new TeleOpDropFreightCommand(armServos,drivetrain));
        upBoxButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A))    //Move Lift Box Up
                .whenPressed(new HalfDropCommandT(armServos));


        carouselLeftTrigger = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)   //Carousel Left
                .whileHeld(carousel::carouselLeft)
                .whenReleased(carousel::stop));
        carouselRightTrigger = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)   //Carousel Right
                .whileHeld(carousel::carouselRight)
                .whenReleased(carousel::stop));



        intakeClawUpButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_UP)          //Up Intake Claw
                .whileHeld(armServos::boxUp));
        intakeClawDownButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)      //Down Intake Claw
                .whileHeld(armServos::boxDown));



        outRealCapHomeTrigger = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_RIGHT)    //Out Cap Servo
                .whenPressed(capServos::addToCap));
        inRealCapHomeTrigger = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_LEFT)      //In Cap Servo
                .whenPressed(capServos::subtractToCap));

        realClawHomeButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.Y)    //Home
                .whenPressed(capServos::realCapHome));
        realClawMidButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.X)     //Mid
                .whenPressed(capServos::realCapMid));
        realClawMidButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.B)     //Mid
                .whenPressed(capServos::realCapMid));
        realClawLowButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.A)     //Low
                .whenPressed(capServos::realCapLow));
    }

    @Override
    public void matchLoop() { }
    @Override
    public void disabledPeriodic() { }
    @Override
    public void matchStart() { }
    @Override
    public void robotPeriodic() {
        sensorColor.periodic();
    }
}
