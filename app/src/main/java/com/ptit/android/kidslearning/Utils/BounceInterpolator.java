package com.ptit.android.kidslearning.Utils;

import android.view.animation.Interpolator;

public class BounceInterpolator implements Interpolator {
    private double amplitude, frequency;

    public BounceInterpolator(double amplitude, double frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    @Override
    public float getInterpolation(float input) {
        return (float) (-1 * Math.pow(Math.E, -input / amplitude) * Math.cos(frequency * input) + 1);
    }
}