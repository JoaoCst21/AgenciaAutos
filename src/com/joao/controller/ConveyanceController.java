package com.joao.controller;

import com.joao.model.CRUD;
import com.joao.model.Conveyance;

import java.util.ArrayList;

public abstract class ConveyanceController<T extends Conveyance> implements CRUD<T> {
    private int id;
    ArrayList<T> conveyances = new ArrayList<>();

    // Abstract validation Methods
    public abstract void validate(T conveyance); // Maybe boolean
    public abstract void validateYear();
    public abstract void validateTransportCapacity();
    public abstract void validateModel();
    public abstract void validateBrand();


    // Interface Methods
    @Override
    public void create(T conveyance) {

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
        // Validate
        validate(conveyance);

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
