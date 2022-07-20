package com.joao.model;

public class Car extends Conveyance {
    private int doors;

    public Car(String conveyanceName, int id, int year, int transportCapacity, String model, String brand, int doors) {
        super("Carro", id, year, transportCapacity, model, brand);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}