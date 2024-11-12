package org.firstinspires.ftc.teamcode.FTCCenterStage.AutoCode.Trajectory.Base;

import org.firstinspires.ftc.teamcode.FTCCenterStage.Globals;

public class SleepAction implements MovementPerformer
{
    long start;
    int ms;

    @Override
    public void run()
    {
        start = System.currentTimeMillis();

        while ((System.currentTimeMillis()-start) < ms && Globals.opMode.opModeIsActive())
        {
            Globals.updateTracking();
        }
    }

    public SleepAction(int ms)
    {
        this.ms = ms;
    }
}
