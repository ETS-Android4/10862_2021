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

import org.firstinspires.ftc.teamcode.commands.IntakeCommands.ColorIntakeCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.DefaultDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DriveCommands.SharedSlowDriveCommand;
import org.firstinspires.ftc.teamcode.commands.DropFreightCommands.SharedDropFreightCommand;
import org.firstinspires.ftc.teamcode.commands.LiftCommands.LiftResetCommandT;
import org.firstinspires.ftc.teamcode.commands.ManualBoxCommand;
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
@TeleOp(name = "Shared TeleOp")
public class SharedTeleOp extends MatchOpMode {
    // Gamepads
    private GamepadEx driverGamepad, operatorGamepad;

    // Motors and Servos
        private MotorEx leftFront,  leftRear, rightRear,  rightFront;
        private MotorEx liftMotor, liftMotor2;
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
        public Button intakeClawUpButton;
        public Button intakeClawDownButton;
        public Button resetClawServoBumper;

    @Override
    public void robotInit() {
            driverGamepad = new GamepadEx(gamepad1);
            operatorGamepad = new GamepadEx(gamepad2);

            drivetrain = new Drivetrain(new SampleTankDrive(hardwareMap), telemetry);
            drivetrain.init();
            intake = new Intake(intakeMotor, intakeServo, telemetry, hardwareMap);
            lift = new Lift(liftMotor, liftMotor2, telemetry, hardwareMap);
            armServos = new ArmServos(armServo, dropServo, telemetry, hardwareMap);
            carousel = new Carousel(hardwareMap, telemetry);
            capServos = new CapServos(clawServo, capArmServo, telemetry, hardwareMap);

            //gamepad1.setJoystickDeadzone(0.0f);
            drivetrain.setDefaultCommand(new DefaultDriveCommand(drivetrain, driverGamepad));

            sensorColor = new SensorColor(hardwareMap, telemetry, "colorSensor");
            intake.setDefaultCommand(new ColorIntakeCommand(intake, sensorColor, armServos));
    }

    @Override
    public void configureButtons() {

        //slowmode for the drivetrain
            slowModeBumper = (new GamepadButton(driverGamepad, GamepadKeys.Button.RIGHT_BUMPER))
                    .whileHeld(new SharedSlowDriveCommand(drivetrain, driverGamepad));

        //intake
            outtakeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)
                    .whileHeld(intake::outtake).whenReleased(intake::stop));
            intakeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                    .whileHeld(intake::intake).whenReleased(intake::stop));


        //Intake Trigger makes bow go down
            /*intakeTrigger = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                    .whileHeld(armServos::armHome).whenReleased(intake::stop));*/

        /*//Intake positions
            intakeUpButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_UP))
                . whenPressed(intake::servoUp);
            intakeMiddleButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_RIGHT))
                . whenPressed(intake::servoMid);
            intakeDownButton = (new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER))
                . whenPressed(intake::servoDown);*/

        //lift commands
            liftManualUpButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_RIGHT)
                    .whenPressed(lift::liftManual).whenReleased(lift::stopLift));
            liftManualDownButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_LEFT)
                    .whenPressed(lift::lowerLiftManual).whenReleased(lift::stopLift));

        //tf is this shit HELLO
        //reset everything
            resetEveryThingButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN))
                    .whenPressed(new LiftResetCommandT(armServos, lift));
            resetEveryThingButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN))
                    .whenPressed(new InstantCommand(lift::liftResting, lift));
            resetEveryThingButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.DPAD_DOWN))
                    .whenPressed(new InstantCommand(capServos::clawOpen));

        //Lift positions
            liftLowButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.X)
                    .whenPressed(lift::liftLow));
            liftMidButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.B)
                    .whenPressed(lift::liftMid));
            liftHighButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.Y)
                    .whenPressed(lift::liftSharedHigh));

        //carousel
            carouselLeftTrigger = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER)
                    .whileHeld(carousel::carouselLeft).whenReleased(carousel::stop));
            carouselRightTrigger = (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)
                    .whileHeld(carousel::carouselRight).whenReleased(carousel::stop));

        //Outtaking the freight motion
            dropFreightButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER))
                    .whenPressed(new SharedDropFreightCommand(armServos,drivetrain));
            /*resetClawServoBumper = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER))
                    .whenPressed(armServos::boxOpen);*/


        //Box servos stuff
            upBoxButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.A))
                  .whenPressed(new ManualBoxCommand(armServos, drivetrain));

        /*resetBoxButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.RIGHT_BUMPER))
                .whenPressed(lift::resetEncoder);*/

        //TODO:Fix the slide reset button
            //slideResetButton = (new GamepadButton(operatorGamepad, GamepadKeys.Button.BACK)).whenPressed(() -> reset(liftMotor));

            intakeClawUpButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_UP)
                    .whenPressed(armServos::boxUp));
            intakeClawDownButton = (new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)
                    .whenPressed(armServos::boxDown));
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
