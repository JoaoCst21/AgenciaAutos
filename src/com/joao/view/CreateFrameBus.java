package com.joao.view;

import com.joao.controller.BusController;
import com.joao.model.Bus;

public class CreateFrameBus extends CreateFrame<Bus, BusController>{
    public CreateFrameBus() {
        super(BusController.getBusController(), new String[] {"año", "capacidad", "modelo", "marca", "puertas"});
    }

    @Override
    protected Bus getObject() {
        int year = Integer.parseInt(textfields.get("año").getText());
        int capacity = Integer.parseInt(textfields.get("capacidad").getText());
        String brand = textfields.get("marca").getText();
        String model = textfields.get("modelo").getText();
        int doors = Integer.parseInt(textfields.get("puertas").getText());
        return new Bus(0, year, capacity, brand, model, doors);
    }
}
