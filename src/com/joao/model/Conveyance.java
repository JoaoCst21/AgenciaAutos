package com.joao.model;

public abstract class Conveyance {
    private String conveyanceName;
    private int id;
    private int year;
    private int transportCapacity;
    private String model;
    private String brand;

    public Conveyance(String conveyanceName, int id, int year, int transportCapacity, String model, String brand) {
        this.conveyanceName = conveyanceName;
        this.id = id;
        this.year = year;
        this.transportCapacity = transportCapacity;
        this.model = model;
        this.brand = brand;
    }

    // Getters
    public String getConveyanceName() {
        return conveyanceName;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getTransportCapacity() {
        return transportCapacity;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }


    // Setters
    public void setConveyanceName(String conveyanceName) {
        this.conveyanceName = conveyanceName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTransportCapacity(int transportCapacity) {
        this.transportCapacity = transportCapacity;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
