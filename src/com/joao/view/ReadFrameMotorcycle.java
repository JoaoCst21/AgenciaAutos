package com.joao.view;

import com.joao.controller.MotorcycleController;

public class ReadFrameMotorcycle extends ReadFrame{
    MotorcycleController motorcycleController = MotorcycleController.getMotorcycleController();

    public ReadFrameMotorcycle() {
        super(new String[] {"ID", "Año", "Capacidad de Transporte", "Modelo", "Marca"}, MotorcycleController.getFields());
    }
}
