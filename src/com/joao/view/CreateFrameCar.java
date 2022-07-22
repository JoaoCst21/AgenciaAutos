package com.joao.view;

import com.joao.controller.CarController;
import com.joao.model.Car;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

public class CreateFrameCar extends CreateFrame<Car, CarController>{
    public CreateFrameCar() {
        super(CarController.getCarController(), new String[] {"año", "capacidad", "modelo", "marca", "puertas"});
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
