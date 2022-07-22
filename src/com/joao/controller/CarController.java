package com.joao.controller;

import com.joao.model.Car;

import java.util.ArrayList;


public class CarController extends ConveyanceController<Car> {
    private static CarController carController;

    public static CarController getCarController() {
        if (carController == null) carController = new CarController();
        return carController;
    }
    
    public static String[][] getFields() {
        ArrayList<Car> cars = getCarController().getConveyances();
        String[][] array = new String[cars.size()][6];
        int i = 0;
        for (Car car : cars) {
            array[i][0] = String.valueOf(car.getId());
            array[i][1] = String.valueOf(car.getYear());
            array[i][2] = String.valueOf(car.getTransportCapacity());
            array[i][3] = String.valueOf(car.getModel());
            array[i][4] = String.valueOf(car.getBrand());
            array[i][5] = String.valueOf(car.getDoors());
            i++;
        }

        return array;
    }
    

    // validation Methods
    @Override
    public void validate(Car car) throws Exception {
        validateYear(car.getYear());
        validateTransportCapacity(car.getTransportCapacity());
        validateBrand(car.getBrand());
        validateModel(car.getModel());
        validateDoors(car.getDoors());
    }

    private void validateDoors(int doors) throws Exception {
        if (doors < 2 || doors > 6) throw new Exception("a Car cant have more than 6 doors or less than 2");
    }

    @Override
    public void validateYear(int year) throws Exception {
        if (year < 1910 || year > 2024) throw new Exception("a Car cant be released before 1910 or before 2024");
    }

    @Override
    public void validateTransportCapacity(int transportCapacity) throws Exception {
        if (transportCapacity < 1 || transportCapacity > 10) throw new Exception("a Car transport capacity cant be less than 1 person or more than 10");
    }

    @Override
    public void validateModel(String model) throws Exception {
        if (model.length() < 2) throw new Exception("The Car model must be at lest 3 letters long");
    }

    @Override
    public void validateBrand(String brand) throws Exception {
        if (brand.length() < 2) throw new Exception("The Car brand must be at lest 3 letters long");
    }
}
