package com.joao.model;

public class Motorcycle extends Conveyance{
    public Motorcycle(int id, int year, int transportCapacity, String model, String brand) {
        super("Moto", id, year, transportCapacity, model, brand);
    }
}
