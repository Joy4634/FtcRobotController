//  _____                _           _       _  _    __  _____ _  _
// |  ___| __ ___   __ _| |__   ___ | |_ ___| || |  / /_|___ /| || |
// | |_ | '__/ _ \ / _` | '_ \ / _ \| __/ __| || |_| '_ \ |_ \| || |_
// |  _|| | | (_) | (_| | |_) | (_) | |_\__ \__   _| (_) |__) |__   _|
// |_|  |_|  \___/ \__, |_.__/ \___/ \__|___/  |_|  \___/____/   |_|
//                 |___/
package org.firstinspires.ftc.teamcode.FTCCenterStage;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drivebase.CenterStageDriveBase;


@TeleOp
public class CenterStageOutreach extends LinearOpMode {

    // Drive Motors
    private DcMotorEx FL;
    private DcMotorEx FR;
    private DcMotorEx RL;
    private DcMotorEx RR;

    // Other Motors
    private DcMotorEx Lift;


    // Servos
    private float Rservopos;
    private float Lservopos;
    private Servo RCLaw;
    private Servo LCLaw;

    // Local Variables
    private double leftStickX;
    private double leftStickY;
    private double rightStickX;
    private float  speedFactor;

    // Magic state machine thing
    public static boolean RESETME;

    // Sysinternals, dont touch
    CenterStageDriveBase centerStageDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;

    // State Machine




    public void runOpMode() {

        // Drive Motors
        FL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FL");
        FR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FR");
        RL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RL");
        RR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RR");

        // Other Motors
        Lift = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Lift");

        // Servos
//
        LCLaw = (Servo) hardwareMap.get(Servo.class, "LCLaw");
        RCLaw = (Servo) hardwareMap.get(Servo.class, "RCLaw");


        // Sysinit, don't touch
        centerStageDriveBase = new CenterStageDriveBase();
        centerStageDriveBase.init(hardwareMap);
        centerStageDriveBase.enablePID();
        Globals.robot=centerStageDriveBase;
        Globals.driveBase=centerStageDriveBase;
        Globals.trackingWheelIntegrator = trackingWheelIntegrator;
        Globals.opMode = this;
        Globals.robot.enableBrake(true);
        trackingWheelIntegrator = new TrackingWheelIntegrator();

        // Initial Servo positions
        Rservopos = 1;
        Lservopos = -1;

        // Run to position
        Lift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        Lift.setTargetPosition(1);
        Lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        // Telemetry initialisation
        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            runGamepad();
        }
    }

    void runGamepad() {

        // Default speed, change with care
        speedFactor = (float) .5;

        // Map stick values to local variables
        leftStickX = gamepad1.left_stick_x;
        leftStickY = gamepad1.left_stick_y;
        rightStickX = gamepad1.right_stick_x;


        if (gamepad1.dpad_up) {
            Lift.setTargetPosition(0);
            Lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }
        if (gamepad1.dpad_right) {
            Lift.setTargetPosition(-174);
            Lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }
        if (gamepad1.dpad_left) {
            Lift.setTargetPosition(-257);
            Lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }
        if (gamepad1.dpad_down) {
            Lift.setTargetPosition(-326);
            Lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            Lift.setPower(1);
        }

        if (gamepad1.right_bumper) {
            LCLaw.setPosition(.4);
            RCLaw.setPosition(1);
        }

        if (gamepad1.left_bumper) {
            LCLaw.setPosition(.9);
            RCLaw.setPosition(.7);

        }
        if (gamepad1.left_trigger > .5) {
            LCLaw.setPosition(.4);
        }
        if (gamepad1.right_trigger > .5) {
            RCLaw.setPosition(.65);
        }


        // RCLaw close = 1
        // LCLaw close = .8
        // RCLaw open = .65
        // LCLaw open = .4


        // Telemetry
        telemetry.addData("lift counts:", Lift.getCurrentPosition());
        telemetry.addData("ServoRpos", Rservopos);
        telemetry.addData("ServoLpos", Lservopos);
        telemetry.update();

        // Drive
        MecanumDrive.cartesian(Globals.robot, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);
    }
}

// VALUE INDEX
