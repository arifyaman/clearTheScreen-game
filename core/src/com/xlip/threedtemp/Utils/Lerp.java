package com.xlip.threedtemp.Utils;

/**
 * Created by Arif on 20.07.2017.
 */

public class Lerp {
    private float orjStart;
    private float start;
    private float end;
    private float interpolation;
    private  boolean finished;
    private Lerp combined;
    private float finisherFraction;




    public Lerp(float start, float end, float interpolation) {
        this.start = start;
        this.orjStart = start;
        this.end = end;
        this.interpolation = interpolation;
        finished = false;
        this.finisherFraction = 100;
    }


    public float getValue(float delta) {

        if(isFinished())
            onFinished();

        if(!this.finished) {

            if (Math.abs(end - start) < Math.abs(orjStart-end)/finisherFraction || end == start) {
                start = end;
                finished = true;

            }

            start += (end - start) * interpolation * delta;

            return start;
        }else {
            if(combined != null) {
                return combined.getValue(delta);
            }

            return end;
        }
    }

    public Lerp combineWith(float end) {
        combineWith(end, interpolation);
        return this;
    }

    public Lerp combineWith(float end, float interpolation){
        if(combined == null) {
            combined = new Lerp(this.end,end,interpolation);
        }else {
            combined.combineWith(end,interpolation);
        }

        return this;
    }

    public void addToEnd(float difference) {
        this.end += difference;
        finished = false;
    }

    public Lerp go(float end) {
        this.end = end;
        finished = false;
        return this;
    }

    public boolean isFinished() {
        if(combined != null){
            return combined.isFinished();
        }

        return this.finished;
    }

    public void reset(){
        this.start = orjStart;
        this.finished = false;
    }

    public float getEnd() {
        return end;
    }

    public void onFinished(){

    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public float getFinisherFraction() {
        return finisherFraction;
    }

    public void setFinisherFraction(float finisherFraction) {
        this.finisherFraction = finisherFraction;
    }

    public float getInterpolation() {
        return interpolation;
    }

    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }
}
