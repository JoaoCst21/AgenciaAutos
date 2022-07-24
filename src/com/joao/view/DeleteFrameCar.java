package com.joao.view;

import com.joao.controller.CarController;
import com.joao.model.Car;

public class DeleteFrameCar extends DeleteFrame<Car, CarController>{

    public DeleteFrameCar() {
        super(CarController.getCarController(), new String[] {"id", "año", "capacidad", "modelo", "marca", "puertas"});
    }

    @Override
    protected void setDataTableValue(Car car) {
        String[] array = data[0];
        array[0] = String.valueOf(car.getId());
        array[1] = String.valueOf(car.getYear());
        array[2] = String.valueOf(car.getTransportCapacity());
        array[3] = String.valueOf(car.getModel());
        array[4] = String.valueOf(car.getBrand());
        array[5] = String.valueOf(car.getDoors());
    }
}
