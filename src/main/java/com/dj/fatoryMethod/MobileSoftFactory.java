package com.dj.fatoryMethod;

public class MobileSoftFactory implements  ISoftFactory{
    public ISoft getInstance(){
        return new MobileSoft();
    }
}
