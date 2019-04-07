package com.dj.singletonpattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EnumSingletonTest {
    public static void main(String[] args) {
        try {
            EnumSingleton singleton = EnumSingleton.getSingleton();
            FileOutputStream fileOutputStream = new FileOutputStream("EnumObj.obj");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOutputStream);
            objOut.writeObject(singleton);
            objOut.flush();
            objOut.close();

            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("EnumObj.obj"));
            EnumSingleton singleton1 = (EnumSingleton) objectIn.readObject();
            System.out.println(singleton1==singleton);
            System.out.println(singleton.toString()  +"   "+ singleton1);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
