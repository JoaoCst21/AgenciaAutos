package com.joao.controller;

import com.joao.model.Motorcycle;

public class MotorcycleController extends ConveyanceController<Motorcycle>{
    @Override
    public void validate(Motorcycle moto) throws Exception {
        validateYear(moto.getYear());
        validateTransportCapacity(moto.getTransportCapacity());
        validateBrand(moto.getBrand());
        validateModel(moto.getModel());
    }

    @Override
    public void validateYear(int year) throws Exception {
        if (year < 1930 || year > 2024) throw new Exception("a Motorcycle cant have been released before 1930 or after 2024");
    }

    @Override
    public void validateTransportCapacity(int transportCapacity) throws Exception {
        if (transportCapacity < 1 || transportCapacity > 5) throw new Exception("a Motorcycle transport capacity cant be less tan 1 or more than 5 people");
    }

    @Override
    public void validateModel(String model) throws Exception {
        if (model.length() < 2) throw new Exception("a Motorcycle model needs to be at least 3 letters");
    }

    @Override
    public void validateBrand(String brand) throws Exception {
        if (brand.length() < 2) throw new Exception("a Motorcycle brand needs to be at least 3 letters");
    }
}
