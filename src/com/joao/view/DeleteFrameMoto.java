package com.joao.view;

import com.joao.controller.MotorcycleController;
import com.joao.model.Motorcycle;

public class DeleteFrameMoto extends DeleteFrame<Motorcycle, MotorcycleController>{

    public DeleteFrameMoto() {
        super(MotorcycleController.getMotorcycleController(), new String[] {"id", "año", "capacidad", "modelo", "marca", "puertas"});
    }

    @Override
    protected void setDataTableValue(Motorcycle moto) {
        String[] array = data[0];
        array[0] = String.valueOf(moto.getId());
        array[1] = String.valueOf(moto.getYear());
        array[2] = String.valueOf(moto.getTransportCapacity());
        array[3] = String.valueOf(moto.getModel());
        array[4] = String.valueOf(moto.getBrand());
    }
}
