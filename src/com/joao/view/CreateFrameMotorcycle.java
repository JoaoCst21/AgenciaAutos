package com.joao.view;

import com.joao.controller.MotorcycleController;
import com.joao.model.Motorcycle;

public class CreateFrameMotorcycle extends CreateFrame<Motorcycle, MotorcycleController> {
    public CreateFrameMotorcycle() {
        super(MotorcycleController.getMotorcycleController(), new String[] {"año", "capacidad", "modelo", "marca"});
    }

    @Override
    protected Motorcycle getObject() {
        int year = Integer.parseInt(textfields.get("año").getText());
        int capacity = Integer.parseInt(textfields.get("capacidad").getText());
        String brand = textfields.get("marca").getText();
        String model = textfields.get("modelo").getText();
        return new Motorcycle(0, year, capacity, brand, model);
    }
}
