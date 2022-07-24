package com.joao.view;

import com.joao.controller.BusController;
import com.joao.model.Bus;

public class DeleteFrameBus extends DeleteFrame<Bus, BusController>{
    public DeleteFrameBus() {
        super(new String[] {"id", "año", "capacidad", "modelo", "marca", "puertas"});
    }

    @Override
    protected void setTextfieldValue(Bus bus) {
        String[] array = data[0];
        array[0] = String.valueOf(bus.getId());
        array[1] = String.valueOf(bus.getYear());
        array[2] = String.valueOf(bus.getTransportCapacity());
        array[3] = String.valueOf(bus.getModel());
        array[4] = String.valueOf(bus.getBrand());
        array[5] = String.valueOf(bus.getDoors());
    }
}
