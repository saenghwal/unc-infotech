package io.smartbudget.ejb.persistence.dao;


import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {

    void insert(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    T findById(ID id);
}
