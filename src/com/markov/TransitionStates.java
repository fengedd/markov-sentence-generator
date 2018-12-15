package com.markov;

import java.util.*;

class TransitionStates {
    private final NavigableMap<Double, String> map = new TreeMap<Double, String>();
    private final Random random;
    private double total = 0;


    public TransitionStates() {
        this.random = new Random();
    }

    public void addFutureState(double weight, String result) {
        if (weight <= 0) return;
        total += weight;
        map.put(total, result);
    }

    public String nextState() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }


    @Override
    public String toString() {
        return map.entrySet().toString();
    }
}
