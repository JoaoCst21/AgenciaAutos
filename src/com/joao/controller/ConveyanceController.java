package com.joao.controller;

import com.joao.model.CRUD;
import com.joao.model.Conveyance;

import java.util.ArrayList;

/**
 * Esta Clase será usada para almacenar Objetos tipo {@link T} e interactuar
 * con los Frames en {@link com.joao.view}. también implementa {@link com.joao.model.CRUD}
 * @param <T> Clase que hereda de  {@link com.joao.model.Conveyance}.
 * Será usada para crear objetos en el Arraylist {{@link #conveyances}}.<br/>
 * y esta Clase será la elegida para implementar {@link com.joao.model.CRUD} y todos sus metodos
 * */
public abstract class ConveyanceController<T extends Conveyance> implements CRUD<T> {
    private int id;
    /**
     * ArrayList que actuara como base de Datos para los Objetos tipo {@link T}
     * */
    ArrayList<T> conveyances = new ArrayList<>();

    protected ArrayList<T> getConveyances() {
        return conveyances;
    }

    // Abstract validation Methods
    public abstract void validateYear(int year) throws Exception;
    public abstract void validateTransportCapacity(int transportCapacity) throws Exception;
    public abstract void validateModel(String model) throws Exception;
    public abstract void validateBrand(String brand) throws Exception;
    protected void verifyArrayLength() throws Exception{
        if (conveyances.size() == 0) throw new Exception("The database is Empty");
    }


    // Interface Methods
    @Override
    public void create(T object) {
        object.setId(id);
        conveyances.add(object);
        id++;
    }

    @Override
    public T read(int id) {
        for (T conveyance : conveyances)
            if (conveyance.getId() == id) return conveyance;

        // Display Error Message
        return null;
    }

    @Override
    public void update(T conveyance) {
        for (T transport : conveyances)
            if (conveyance.getId() == transport.getId()) {
                conveyances.remove(transport);
                conveyances.add(conveyance);
                return;
            }
    }

    @Override
    public void delete(T conveyance) {
        conveyances.remove(conveyance);
    }

}
