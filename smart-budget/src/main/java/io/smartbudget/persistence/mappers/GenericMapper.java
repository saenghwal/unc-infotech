package io.smartbudget.persistence.mappers;

import java.util.List;

public interface GenericMapper<T, ID> {
    void save(T entity);

    void merge(T entity);

    void delete(T entity);

    List<T> findAll();

    T findById(ID id);
}
