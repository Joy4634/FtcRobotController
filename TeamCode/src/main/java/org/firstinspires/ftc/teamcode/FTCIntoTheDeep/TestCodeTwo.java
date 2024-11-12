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
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;
import org.firstinspires.ftc.teamcode.FTCCenterStage.MecanumDrive;
import org.firstinspires.ftc.teamcode.FTCCenterStage.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.drivebase.CenterStageDriveBase;

import static org.firstinspires.ftc.teamcode.FTCCenterStage.Globals.Lift;


@TeleOp
public class TestCodeTwo extends LinearOpMode {

//    // Drive Motors
//    private DcMotorEx FL;
//    private DcMotorEx FR;
//    private DcMotorEx RL;
//    private DcMotorEx RR;

    // Other Motors
    private DcMotorEx Uppy;


//    // Servos
//    private float Rservopos;
//    private float Lservopos;
    private Servo Claw;
    private Servo Wrist;
    private Servo UppyMini;
    private Servo SmushMush;

    // Local Variables
    private double leftStickX;
    private double leftStickY;
    private double rightStickX;
    private float  speedFactor;

    private boolean ClawPressed = true;
    private double WristPosition = 0;
//    CBTL why variable L49
//    CBTL = not go here L49
//    double UppyEc;
    public static double UppyCounts;
    // Magic state machine thing
    public static boolean RESETME;

    // Sysinternals, dont touch
    CenterStageDriveBase centerStageDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;



    // State Machine




    public void runOpMode() {

        // Drive Motors
//        FL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FL");
//        FR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "FR");
//        RL = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RL");
//        RR = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "RR");
//
//        // Other Motors
        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");


       // Uppy.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        // Servos
//
//        LCLaw = (Servo) hardwareMap.get(Servo.class, "LCLaw");
       Claw = (Servo) hardwareMap.get(Servo.class, "Claw");
       Wrist = (Servo) hardwareMap.get(Servo.class, "Wrist");
       UppyMini = (Servo) hardwareMap.get(Servo.class, "UppyMini");
       SmushMush = (Servo) hardwareMap.get(Servo.class, "SmushMush");

        // Sysinit, don't touch
//        uncomment maybe later
//        centerStageDriveBase = new CenterStageDriveBase();
//        centerStageDriveBase.init(hardwareMap);
//        centerStageDriveBase.enablePID();
//        Globals.robot=centerStageDriveBase;
//        Globals.driveBase=centerStageDriveBase;
//        Globals.trackingWheelIntegrator = trackingWheelIntegrator;
//        Globals.opMode = this;
//        Globals.robot.enableBrake(true);
//        trackingWheelIntegrator = new TrackingWheelIntegrator();

        // Initial Servo positions
//        Rservopos = 1;
//        Lservopos = -1;

//        // Run to position
        Uppy.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        Uppy.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        Uppy.setTargetPosition(1);
        Uppy.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);



//        UppyEc = Lift.getCurrentPosition();
//        telemetry.addData("UppyEc", UppyEc);
//        telemetry.update();

//        int UppyEncoders;
//        int Uppycounts;
//
//        int UppyEC = getMotorCurrentPosition(3);
//
//        UppyEC = Uppy.getCurrentPosition();

//        Uppy.setDirection(DcMotorEx.Direction.FORWARD);
//        int UppyCurrent = UppyEC;
        // Telemetry initialisation
//        telemetry.addData("LiftEC", UppyEC);

        telemetry.setMsTransmissionInterval(20);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()){
            runGamepad();
            UppyCounts = Uppy.getCurrentPosition();
            telemetry.addData("Uppy counts:", UppyCounts);
            telemetry.update();
        }
    }

    //private int getMotorCurrentPosition(int i) {
    //    return i;
    //}


    void runGamepad() {

        // Default speed, change with care
        speedFactor = (float) .5;

        // Map stick values to local variables
        leftStickX = gamepad1.left_stick_x;
        leftStickY = gamepad1.left_stick_y;


        if(gamepad1.left_bumper) {
            if(ClawPressed == false) {
                if(Claw.getPosition() == 0) {
                    Claw.setPosition(.25);
                    ClawPressed = true;
                } else if(ClawPressed == false){
                    Claw.setPosition(0);
                    ClawPressed = true;
                }
            }
        }
        else{
        ClawPressed = false;
        }

        if (gamepad1.x){
            Uppy.setPower(1);
        }
         else {
            Uppy.setPower(0);
        }
         if (gamepad1.dpad_up){
             SmushMush.setPosition(.25);
         }
        if (gamepad1.dpad_down){
            SmushMush.setPosition(0);
        }

/*
         SmushMush open is .25
         SmushMush closed is 0
        Claw open = .25
        Claw closed = 0
        Uppy mini strait = .5
        Uppy mini fold = 1
        Uppy mini down = .1
        Wrist 0.9
        Wrist Forward = .2
        Wrist L45 = 0
        Wrist R45 = .4
        Wrist 90 = .55
*/

        // Telemetry
//        telemetry.addData("lift counts:", Lift.getCurrentPosition());
//        telemetry.addData("ServoRpos", Rservopos);
//        telemetry.addData("ServoLpos", Lservopos);



        // Drive
//        uncomment maybe later
//        MecanumDrive.cartesian(Globals.robot, -leftStickY * speedFactor, leftStickX * speedFactor, rightStickX * speedFactor);
    }
}

// VALUE INDEX
