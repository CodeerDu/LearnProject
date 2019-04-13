package com.dj.strategypattern;

public class PptTeachStrategy implements TeachStrategy{
    @Override
    public void teach() {
        System.out.println("Use PPT");
    }
}
