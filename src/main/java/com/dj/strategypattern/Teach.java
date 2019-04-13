package com.dj.strategypattern;

public class Teach {
    private TeachStrategy teachStrategy;

    public Teach(TeachStrategy teachStrategy) {
        this.teachStrategy = teachStrategy;
    }
    public void teach(){
        teachStrategy.teach();
    }
    public static void main(String[] args) {
        new Teach(new PptTeachStrategy()).teach();
        new Teach(TeachStrategyFactory.getStrategy("PRACTICE")).teach();
    }
}
