package com.joao.model;

public class Bus extends Conveyance{
    private int doors;

    public Bus(int id, int year, int transportCapacity, String model, String brand, int doors) {
        super("Camioneta", id, year, transportCapacity, model, brand);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }
}
