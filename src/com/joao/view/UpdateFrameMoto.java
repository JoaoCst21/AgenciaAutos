package com.joao.view;

import com.joao.controller.MotorcycleController;
import com.joao.model.Motorcycle;

public class UpdateFrameMoto extends UpdateFrame<Motorcycle, MotorcycleController>{

    public UpdateFrameMoto() {
        super(MotorcycleController.getMotorcycleController(), new String[] {"año", "capacidad", "modelo", "marca"});
    }

    @Override
    protected void setTextfieldValue(Motorcycle moto) {
        textfields.get("año").setText(String.valueOf(moto.getYear()));
        textfields.get("capacidad").setText(String.valueOf(moto.getTransportCapacity()));
        textfields.get("modelo").setText(String.valueOf(moto.getModel()));
        textfields.get("marca").setText(String.valueOf(moto.getBrand()));
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
