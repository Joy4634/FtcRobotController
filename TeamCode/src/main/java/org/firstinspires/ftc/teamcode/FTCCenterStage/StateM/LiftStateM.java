package org.firstinspires.ftc.teamcode.FTCCenterStage.StateM;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;
import org.firstinspires.ftc.teamcode.FTCCenterStage.MecDrive;

public class LiftStateM extends StateMachine<LiftStateM.State> implements StateMMovmentPerformer {

    public double angle;


    public enum State {
        START,
        PIVOTWITHTHATDOOR,
        RAISETHATLIFT,
        POSISTIONS,
        IDLE,


    }
  // PIVOT WITH THAT DOOR = pivet up and close the door
  // RAISE THAT LIFT = raise the lift
  // POSISTIONS = set it to placing position



    public State getStAte()
    {
        return state;
    }

    @Override
    public boolean run() {
        return runIteration() == ReturnState.PROCEED;
    }

    @Override
    public void reset() {
        state = State.START;

    }

    @Override
    public String getName() {
        return "AutoTransfer";
    }
    public LiftStateM() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 500) {
                    switchState(State.PIVOTWITHTHATDOOR);
                }
                break;
            }
            case PIVOTWITHTHATDOOR: {
                Globals.SLift.setPosition(.15);
                Globals.Pivot.setPosition(.8);
                Globals.Door.setPosition(1);

                if(getElapsedStateTime() >500) {
                    switchState(State.RAISETHATLIFT);
                }
                break;
            }
            case RAISETHATLIFT: {
                Globals.Lift.setTargetPosition(-1400);
                Globals.Lift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                Globals.Lift.setPower(1);

                if(getElapsedStateTime() > 200) {

                    switchState(State.POSISTIONS);
                }
                break;
            }
            case POSISTIONS: {
                Globals.SLift.setPosition(.3);
                Globals.Pivot.setPosition(1);

                if(getElapsedStateTime() > 200) {
                    MecDrive.RESETME = true;
                    switchState(State.IDLE);
                }
                break;
            }

            case IDLE: {
                if(getElapsedStateTime() > 100) {

                    return ReturnState.PROCEED;
                }
                break;
            }

        }
        return ReturnState.KEEP_RUNNING_ME;
    }
}
