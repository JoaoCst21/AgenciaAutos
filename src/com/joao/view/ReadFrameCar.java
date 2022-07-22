/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.joao.view;

import com.joao.controller.CarController;
import com.joao.model.Car;
import java.util.ArrayList;

/**
 *
 * @author informatica
 */
public class ReadFrameCar extends ReadFrame{
    CarController carController = CarController.getCarController();
    
    public ReadFrameCar() {
        super(new String[] {"ID", "Año", "Capacidad de Transporte", "Modelo", "Marca", "Puertas"}, CarController.getFields());
    }
}
