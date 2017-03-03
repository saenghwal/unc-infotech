package io.smartbudget.ejb.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import io.budgetapp.application.NotFoundException;
import io.dropwizard.hibernate.AbstractDAO;

/**
 *
 */
public class DefaultDAO<T> extends AbstractDAO<T> {


    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public DefaultDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }



    protected final <T> T singleResult(Criteria criteria) {
        T t = (T)uniqueResult(criteria);
        if(t == null) {
            throw new NotFoundException();
        }

        return t;
    }

}
