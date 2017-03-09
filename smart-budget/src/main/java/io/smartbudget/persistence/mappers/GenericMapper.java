package io.smartbudget.persistence.mappers;

import java.util.List;

public interface GenericMapper<T, ID> {
    void insert(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    T findById(ID id);
}
