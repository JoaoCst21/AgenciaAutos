package com.joao.model;

/**
 * La interfaz CRUD
 * */
public interface CRUD<Class> {
    /**
     * El fin de este metodo es modificar el objeto recibido y almacenarlo en un Array
     * */
    void create(Class object);

    /**
     * El fin de este metodo es recibir un ID y devolver
     * el Objeto almacenado con ese ID en un Array
     * @return el Objeto encontrado o null
     * */
    Class read(int id);

    /**
     * El fin de este metodo es remplazar un objeto almacenado en un Array con el recibido
     * */
    void update(Class object);

    /**
     * El fin de este metodo es eliminar el objeto recibido de un Array
     * */
    void delete(Class object);

    /**
     * El fin de este metodo es validar que el objeto recibido sea un objeto válido
     * para almacenar en un array
     * */
    void validate(Class object) throws Exception;
}
