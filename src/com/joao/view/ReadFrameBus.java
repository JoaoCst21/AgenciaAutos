package com.joao.view;

import com.joao.controller.BusController;

public class ReadFrameBus extends ReadFrame{
    BusController busController = BusController.getBusController();

    public ReadFrameBus() {
        super(new String[] {"ID", "Año", "Capacidad de Transporte", "Modelo", "Marca", "puertas"}, BusController.getFields());
    }
}
