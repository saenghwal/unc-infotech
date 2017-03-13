package io.smartbudget.ejb.dao.impl;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;

import io.smartbudget.ejb.dao.CategoryDAO;
import io.smartbudget.exception.NotFoundException;
import io.smartbudget.persistence.entity.Category;
import io.smartbudget.persistence.entity.User;
import io.smartbudget.persistence.mappers.CategoriesMapper;

@Stateful
@Local
public class CategoryDAOImpl extends GenericDAOImpl<Category, Long> implements CategoryDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDAOImpl.class);

    public CategoryDAOImpl(SqlSessionFactory sessionFactory) {
        super(sessionFactory.openSession().getMapper(CategoriesMapper.class));
    }

    public CategoryDAOImpl() {
    }


    public void addCategory(User user, Category category) {
        LOGGER.debug("Add new category {}", category);
        category.setUser(user);
        mapper.save(category);
    }

    @Override
    public Category findById(Long categoryId) {
        Category category = ((CategoriesMapper) mapper).findById(categoryId);
        if(category == null) {
            throw new NotFoundException();
        }

        return category;
    }

    @Override
    public Category find(User user, Long categoryId) {
        return ((CategoriesMapper) mapper).find(user, categoryId);
    }

    @Override
    public List<Category> findCategories(User user) {
        return ((CategoriesMapper) mapper).findCategories(user);
    }

    @Override
    public List<String> findSuggestions(User user, String q) {
        q = q == null? "": q.toLowerCase();
        return ((CategoriesMapper) mapper).findSuggestions(user, "%" + q + "%");
    }
}
