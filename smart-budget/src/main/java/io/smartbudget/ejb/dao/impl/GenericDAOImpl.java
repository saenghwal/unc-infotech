package io.smartbudget.ejb.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.Serializable;
import java.util.List;

import io.smartbudget.ejb.dao.GenericDAO;
import io.smartbudget.ejb.dao.MyBatisConnectionFactory;
import io.smartbudget.persistence.mappers.GenericMapper;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    protected GenericMapper mapper;
    protected SqlSessionFactory sqlSessionFactory;

    public GenericDAOImpl(GenericMapper mapper) {
        this.mapper = mapper;
    }

    public GenericDAOImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
    }

    public GenericDAOImpl() {
    }

    @Override
    public List<T> findAll(Class entity) {
        return mapper.findAll();
    }

    @Override
    public T findById(ID id) {
        return (T)mapper.findById(id);
    }

    @Override
    public void save(T entity) {
        mapper.save(entity);
    }

    @Override
    public void merge(T entity) {
        mapper.merge(entity);
    }

    @Override
    public void delete(T entity) {
        mapper.delete(entity);
    }
}
