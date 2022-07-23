package com.joao.view;

import com.joao.controller.CarController;
import com.joao.model.Car;

public class UpdateFrameCar extends UpdateFrame<Car, CarController>{

    public UpdateFrameCar() {
        super(CarController.getCarController(), new String[] {"año", "capacidad", "modelo", "marca", "puertas"});
    }

    @Override
    protected void setTextfieldValue(Car car) {
        textfields.get("año").setText(String.valueOf(car.getYear()));
        textfields.get("capacidad").setText(String.valueOf(car.getTransportCapacity()));
        textfields.get("modelo").setText(String.valueOf(car.getModel()));
        textfields.get("marca").setText(String.valueOf(car.getBrand()));
        textfields.get("puertas").setText(String.valueOf(car.getDoors()));
    }

    @Override
    protected Car getObject() {
        int year = Integer.parseInt(textfields.get("año").getText());
        int capacity = Integer.parseInt(textfields.get("capacidad").getText());
        String brand = textfields.get("marca").getText();
        String model = textfields.get("modelo").getText();
        int doors = Integer.parseInt(textfields.get("puertas").getText());
        return new Car(0, year, capacity, brand, model, doors);
    }
}
