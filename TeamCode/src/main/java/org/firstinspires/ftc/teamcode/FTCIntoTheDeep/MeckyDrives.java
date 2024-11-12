//  _____                _           _       _  _    __  _____ _  _
// |  ___| __ ___   __ _| |__   ___ | |_ ___| || |  / /_|___ /| || |
// | |_ | '__/ _ \ / _` | '_ \ / _ \| __/ __| || |_| '_ \ |_ \| || |_
// |  _|| | | (_) | (_| | |_) | (_) | |_\__ \__   _| (_) |__) |__   _|
// |_|  |_|  \___/ \__, |_.__/ \___/ \__|___/  |_|  \___/____/   |_|
//                 |___/
package org.firstinspires.ftc.teamcode.FTCIntoTheDeep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;
import org.firstinspires.ftc.teamcode.FTCCenterStage.MecanumDrive;
import org.firstinspires.ftc.teamcode.FTCCenterStage.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoInnie;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM.GoOutie;
import org.firstinspires.ftc.teamcode.drivebase.CenterStageDriveBase;

import static org.firstinspires.ftc.teamcode.FTCCenterStage.Globals.Lift;

@TeleOp
public class MeckyDrives extends LinearOpMode {

    // Drive Motors
    private DcMotorEx FL;
    private DcMotorEx FR;
    private DcMotorEx RL;
    private DcMotorEx RR;
    
    // Other Motors
    public static DcMotorEx Uppy;
    private DcMotorEx UppyHangs;

    // Servos
    public static Servo TheToungue;
    public static Servo UppyMini;
    public static Servo Claw;
    private Servo Wrist;
    private Servo Lifty;
    private Servo Hangy;
    private Servo SmushMush;
    private Servo TrashCan;

    // probably important
    private float Rservopos;
    private float Lservopos;


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
    GoOutie GO = new GoOutie();
    GoInnie GI = new GoInnie();

    public static double UppyCounts;





    public void runOpMode() {

        // Drive Motors
        FL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FL");
        FR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FR");
        RL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RL");
        RR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RR");

        // Other Motors
        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");
        UppyHangs = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "UppyHangs");


        // Servos
        TheToungue = (Servo) hardwareMap.get(Servo.class,  "TheToungue");
        UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini" );
        Claw = (Servo) hardwareMap.get(Servo.class, "Claw" );
        Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist" );
        Lifty = (Servo) hardwareMap.get(Servo.class, "Lifty" );
        Hangy = (Servo) hardwareMap.get(Servo.class, "Hangy" );
        SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush" );
        TrashCan = (Servo) hardwareMap.get(Servo.class, "TrashCan" );


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
        Uppy.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        Uppy.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        Uppy.setTargetPosition(1);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        // Telemetry initialisation
        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();



    // StateM stuff
        while (opModeIsActive()) {

            // Telemetry
            UppyCounts = Uppy.getCurrentPosition();
            telemetry.addData("Uppy counts:", UppyCounts);
            telemetry.addData("ServoRpos", Rservopos);
            telemetry.addData("ServoLpos", Lservopos);
            telemetry.update();

            if (RESETME) {
                GO.reset();
                GI.reset();
//                LDSM.reset();
//                FSLDSM.reset();
                RESETME = false;
            }
//
//            if (gamepad1.left_bumper) {
//                HSM.runIteration();
//                runGamepad();
//            }
//            if (gamepad2.a) {
//                LSM.runIteration();
//                runGamepad();
//            }
//
//            runGamepad();
        }

    }

    void runGamepad() {

        // Default speed, change with care
        speedFactor = (float) .5;

        // Map stick values to local variables
        leftStickX = gamepad1.left_stick_x;
        leftStickY = gamepad1.left_stick_y;
        rightStickX = gamepad1.right_stick_x;

    // If Statements

    // Tim's stuff might use later
//        if (gamepad1.x) {
//            RHook.setPosition(1);
//            LHook.setPosition(0);
//            LHang.setPower(-.3);
//            RHang.setPower(-.3);
//        }
//        else if (!gamepad1.left_bumper && (gamepad1.left_trigger > .5) && !gamepad1.x) {
//            LHang.setPower(0);
//            RHang.setPower(0);
//        }
//
//        if (gamepad1.left_trigger > .5) {
//            RHook.setPosition(1);
//            LHook.setPosition(0);
//            LHang.setPower(-.95);
//            RHang.setPower(-.95);
//        }
//        else if (!gamepad1.left_bumper && (gamepad1.left_trigger > .5) && !gamepad1.x) {
//            LHang.setPower(0);
//            RHang.setPower(0);
//        }
//        if (gamepad1.dpad_right) {
//            RHook.setPosition(1);
//            LHook.setPosition(0);
//        }

//        LiftCounts = Lift.getCurrentPosition();
//
//        telemetry.addData("lift counts:", LiftCounts);




        // Drive
        MecanumDrive.cartesian(Globals.robot, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);
    }
}

/*
    Value Index
 */