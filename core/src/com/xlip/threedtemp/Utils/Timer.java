package com.xlip.threedtemp.Utils;

/**
 * Created by arif on 02.02.2018.
 */

public abstract class Timer {

    private float time;
    private float set;
    private boolean enabled;
    private boolean finished;


    public Timer(float seconds) {
        this.set = seconds;
        this.time = 0;
        this.finished = false;
    }

    public void tick(float delta) {
        if(enabled && !finished){
            this.time += delta;
        }

        if(time >= set && !finished){
            run();
            finished = true;
        }
    }
    public void reset(){
        this.time = 0;
        finished = false;
        enabled = false;
    }

    public abstract void run();



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
