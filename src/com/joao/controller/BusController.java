package com.joao.controller;

import com.joao.model.Bus;

import java.util.ArrayList;

public class BusController extends ConveyanceController<Bus> {
    private static BusController busController;

    /**
     * Constructor Singleton.<br/>
     * Esto con el fin de Que siempre se trabaje con la misma base de Datos.
     * Singleton es indispensable para esta implementación, ya que
     * no se puede implementar un ArrayList estático, puesto que el ArrayList es heredado
     * de una Clase Parametrizada/Genérica por lo cual no puede ser declarada como
     * estática.
     * */
    public static BusController getBusController() {
        if (busController == null) busController = new BusController();
        return busController;
    }

    /**
     * crea un Array de 2 dimensiones con todas las propiedades de los objetos
     * almacenados en {@link #conveyances} convertidos a String
     * */
    public static String[][] getFields() {
        ArrayList<Bus> buses = getBusController().getConveyances();
        String[][] array = new String[buses.size()][6];
        int i = 0;
        for (Bus car : buses) {
            array[i][0] = String.valueOf(car.getId());
            array[i][1] = String.valueOf(car.getYear());
            array[i][2] = String.valueOf(car.getTransportCapacity());
            array[i][3] = String.valueOf(car.getModel());
            array[i][4] = String.valueOf(car.getBrand());
            array[i][5] = String.valueOf(car.getDoors());
            i++;
        }

        return array;
    }

    @Override
    public void validate(Bus bus) throws Exception {
        validateYear(bus.getYear());
        validateTransportCapacity(bus.getTransportCapacity());
        validateBrand(bus.getBrand());
        validateModel(bus.getModel());
        validateDoors(bus.getDoors());
    }

    private void validateDoors(int doors) throws Exception {
        if (doors < 2 || doors > 10) throw new Exception("a Bus cant have more than 10 doors or less than 2");
    }

    @Override
    public void validateYear(int year) throws Exception {
        if (year < 1930 || year > 2024) throw new Exception("a Bus cant be released before 1930 or before 2024");
    }

    @Override
    public void validateTransportCapacity(int transportCapacity) throws Exception {
        if (transportCapacity < 5 || transportCapacity > 50) throw new Exception("a Bus transport capacity cant be less than 5 person or more than 50");
    }

    @Override
    public void validateModel(String model) throws Exception {
        if (model.length() < 2) throw new Exception("The Bus model must be at lest 3 letters long");
    }

    @Override
    public void validateBrand(String brand) throws Exception {
        if (brand.length() < 2) throw new Exception("The Bus brand must be at lest 3 letters long");
    }
}
