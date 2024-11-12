package org.firstinspires.ftc.teamcode.FTCCenterStage.StateM;

import org.firstinspires.ftc.teamcode.FTCCenterStage.MecDrive;

public class StateMBase extends StateMachine<StateMBase.State> implements StateMMovmentPerformer {

    public double angle;


    public enum State {
        START,
        TURRET,
        DOWN,
        CLAW,
        IDLE,


    }


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
    public StateMBase() {
        state = State.START;

    }
    @Override
    public ReturnState runIteration() {
        switch (state) {

            case START: {

                if (getElapsedStateTime() > 1000) {
                    switchState(State.TURRET);
                }
                break;
            }
            case TURRET: {

                if(getElapsedStateTime() > 200) {
                    switchState(State.DOWN);
                }
                break;
            }
            case DOWN: {

                if(getElapsedStateTime() > 200) {

                    switchState(State.IDLE);
                }
                break;
            }

            case IDLE: {
                if(getElapsedStateTime() > 100) {
                    MecDrive.RESETME = true;
                    return ReturnState.PROCEED;
                }
                break;
            }

        }
        return ReturnState.KEEP_RUNNING_ME;
    }
}
