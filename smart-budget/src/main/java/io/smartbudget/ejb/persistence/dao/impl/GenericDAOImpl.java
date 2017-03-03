package io.smartbudget.ejb.persistence.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.PersistenceContext;

import io.smartbudget.ejb.persistence.dao.GenericDAO;
import io.smartbudget.ejb.persistence.mappers.GenericMapper;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    //@PersistenceContext(unitName="DEVMODE")
    protected GenericMapper mapper;

    public GenericDAOImpl(GenericMapper mapper) {
        this.mapper = mapper;
    }

    public GenericDAOImpl() {
    }

    @Override
    public List<T> findAll() {
        return mapper.findAll();
    }

    @Override
    public T findById(ID id) {
        return (T)mapper.findById(id);
    }

    @Override
    public void insert(T entity) {
        mapper.insert(entity);
    }

    @Override
    public void update(T entity) {
        mapper.update(entity);
    }

    @Override
    public void delete(T entity) {
        mapper.delete(entity);
    }
}