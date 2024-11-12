package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.MeckyDrives;
import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMMovmentPerformer;
import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMachine;

public class GoOutie extends StateMachine<GoOutie.State> implements StateMMovmentPerformer {

    public double angle;


    public enum State {
        START,
        GETOUTOFHERE,
        THETOUNGUESLURPS,
        NOTTHEWATERFALL,
        IDLE,


    }
// GETOUTOFHERE = UppyMini go either strait or partially down, Double check claw open
// THETOUUNGESLURPS TheToungue extends
// NOTTHEWATERFALL UppyMini falls



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
    public GoOutie() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 500) {
                    switchState(State.GETOUTOFHERE);
                }
                break;
            }
            case GETOUTOFHERE: {
                MeckyDrives.UppyMini.setPosition(.5);
                MeckyDrives.Claw.setPosition(.25);

                if(getElapsedStateTime() >500) {
                    switchState(State.THETOUNGUESLURPS);
                }
                break;
            }
            case THETOUNGUESLURPS: {
                MeckyDrives.TheToungue.setPosition(0);
                if(getElapsedStateTime() > 200) {

                    switchState(State.NOTTHEWATERFALL);
                }
                break;
            }
            case NOTTHEWATERFALL: {
                MeckyDrives.UppyMini.setPosition(.1);

                if(getElapsedStateTime() > 200) {
                    MeckyDrives.RESETME = true;
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
