package com.dj.strategypattern;

public class PracticeTeach implements TeachStrategy{
    @Override
    public void teach() {
        System.out.println("practice teach");
    }
}
