package org.firstinspires.ftc.teamcode.FTCIntoTheDeep.StateM;

import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMMovmentPerformer;
import org.firstinspires.ftc.teamcode.FTCCenterStage.StateM.StateMachine;
import org.firstinspires.ftc.teamcode.FTCIntoTheDeep.MeckyDrives;

public class GoInnie extends StateMachine<GoInnie.State> implements StateMMovmentPerformer {

    public double angle;


    public enum State {
        START,
        RETRACTTHEWATERFALL,
        SLURPIEUPPY,
        WATERFALLSTHEOTHERWAY,
        IDLE,


    }
// RETRACTTHEWATERFALL = UppyMini Rises
// SLURPIEUPPY TheToungue Retracts
// WATERFALLSTHEOTHERWAY UppyMini falls backwards



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
    public GoInnie() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 500) {
                    switchState(State.RETRACTTHEWATERFALL);
                }
                break;
            }
            case RETRACTTHEWATERFALL: {
                MeckyDrives.UppyMini.setPosition(.1);
                MeckyDrives.Claw.setPosition(0);

                if(getElapsedStateTime() >500) {
                    switchState(State.SLURPIEUPPY);
                }
                break;
            }
            case SLURPIEUPPY: {
                MeckyDrives.TheToungue.setPosition(.75);

                if(getElapsedStateTime() > 200) {

                    switchState(State.WATERFALLSTHEOTHERWAY);
                }
                break;
            }
            case WATERFALLSTHEOTHERWAY: {
                MeckyDrives.UppyMini.setPosition(1);

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
