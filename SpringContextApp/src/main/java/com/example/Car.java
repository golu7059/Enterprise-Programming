package com.example;

public class Car {
    IEngine engine;

    public void setEngine(IEngine engine){
        this.engine = engine;
    }
    void drive(){
        int start = engine.start();

        if(start == 1){
            System.out.println("Car is driving");
        } else {
            System.out.println("Car is not started");
        }
    }

}
