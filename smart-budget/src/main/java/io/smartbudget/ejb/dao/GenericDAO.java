package io.smartbudget.ejb.dao;


import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    void save(T entity);

    void merge(T entity);

    void delete(T entity);

    List<T> findAll(Class clazz);

    T findById(ID id);
}
