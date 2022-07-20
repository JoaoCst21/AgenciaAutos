package com.joao.view;

import com.joao.controller.CarController;
import com.joao.model.Car;

import java.awt.event.ActionEvent;

public class CreateFrameCar extends CreateFrame {

    public CreateFrameCar() {
        super(new String[] {"año", "capacidad", "modelo", "marca", "puertas"});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // instantiate carController
        CarController carController = new CarController();

        // get Fields
        int year = Integer.parseInt(textfields.get("año").getText());
        int capacity = Integer.parseInt(textfields.get("capacidad").getText());
        String model = textfields.get("modelo").getText();
        String brand = textfields.get("marca").getText();
        int doors = Integer.parseInt(textfields.get("puertas").getText());


        try {
            Car car = new Car(0, year, capacity, model, brand, doors);
            // validate all
            carController.validate(car);
            // add new User to controller
            carController.create(car);
            // disable window
            this.dispose();

        } catch (Exception ex) {
            // do Something
            // display error Message;
            System.out.println(ex.getMessage()); // just for now
        }
    }
}
