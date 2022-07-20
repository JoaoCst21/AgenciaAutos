package com.joao.model;

public interface CRUD<Class> {
    public void create(Class object);
    public Class read(int id);
    public void update(Class object);
    public void delete(Class object);
}
