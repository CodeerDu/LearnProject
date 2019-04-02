package com.dj.abastractFactory;

public class OAFactory implements  ISoftwareFactory{
    public IGuide getGuide(){
        return new IGuide() {
            public void guide() {
                System.out.println("Guide");
            }
        };
    }
    public ISoft getSoft(){
        return new ISoft() {
            public void exec() {
                System.out.println("SOft");
            }
        } ;
    }
}
