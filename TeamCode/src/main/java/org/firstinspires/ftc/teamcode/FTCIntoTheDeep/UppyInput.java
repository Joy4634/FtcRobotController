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

import org.firstinspires.ftc.teamcode.FTCCenterStage.TrackingWheelIntegrator;
import org.firstinspires.ftc.teamcode.drivebase.CenterStageDriveBase;


@TeleOp
public class UppyInput extends LinearOpMode {


    private DcMotorEx Uppy;



    public static double UppyCounts;
    // Magic state machine thing
    public static boolean RESETME;

    // Sysinternals, dont touch
    CenterStageDriveBase centerStageDriveBase;
    TrackingWheelIntegrator trackingWheelIntegrator;



    // State Machine




    public void runOpMode() {


        Uppy = (DcMotorEx) hardwareMap.get(DcMotorEx.class, "Uppy");


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
