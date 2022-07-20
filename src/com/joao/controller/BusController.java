package com.joao.controller;

import com.joao.model.Bus;

public class BusController extends ConveyanceController<Bus> {
    @Override
    public void validate(Bus bus) throws Exception {
        validateYear(bus.getYear());
        validateTransportCapacity(bus.getTransportCapacity());
        validateBrand(bus.getBrand());
        validateModel(bus.getModel());
        validateDoors(bus.getDoors());
    }

    private void validateDoors(int doors) throws Exception {
        if (doors < 2 || doors > 10) throw new Exception("a Bus cant have more than 10 doors or less than 2");
    }

    @Override
    public void validateYear(int year) throws Exception {
        if (year < 1930 || year > 2024) throw new Exception("a Bus cant be released before 1930 or before 2024");
    }

    @Override
    public void validateTransportCapacity(int transportCapacity) throws Exception {
        if (transportCapacity < 5 || transportCapacity > 50) throw new Exception("a Bus transport capacity cant be less than 5 person or more than 50");
    }

    @Override
    public void validateModel(String model) throws Exception {
        if (model.length() < 2) throw new Exception("The Bus model must be at lest 3 letters long");
    }

    @Override
    public void validateBrand(String brand) throws Exception {
        if (brand.length() < 2) throw new Exception("The Bus brand must be at lest 3 letters long");
    }
}
