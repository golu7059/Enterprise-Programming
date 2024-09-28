package com.example;

public class App {
    public static void main(String[] args) {

        PetrolEngine petrolEngine = new PetrolEngine();
        // DieselEngine dieselEngine = new DieselEngine();

        Car car = new Car();
        car.setEngine(petrolEngine);
        car.drive();
    }
}
