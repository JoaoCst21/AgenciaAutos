package com.joao.view;

import com.joao.controller.BusController;
import com.joao.model.Bus;

public class UpdateFrameBus extends UpdateFrame<Bus, BusController>{
    public UpdateFrameBus() {
        super(BusController.getBusController(), new String[] {"año", "capacidad", "modelo", "marca", "puertas"});
    }

    @Override
    protected void setTextfieldValue(Bus bus) {
        textfields.get("año").setText(String.valueOf(bus.getYear()));
        textfields.get("capacidad").setText(String.valueOf(bus.getTransportCapacity()));
        textfields.get("modelo").setText(String.valueOf(bus.getModel()));
        textfields.get("marca").setText(String.valueOf(bus.getBrand()));
        textfields.get("puertas").setText(String.valueOf(bus.getDoors()));
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
