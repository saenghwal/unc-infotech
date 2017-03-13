package io.smartbudget.ejb.dao;

import java.util.List;

import io.smartbudget.persistence.entity.Category;
import io.smartbudget.persistence.entity.User;

public interface CategoryDAO extends GenericDAO<Category, Long> {

    List<Category> findCategories(User user);

    Category find(User user, Long categoryId);

    List<String> findSuggestions(User user, String q);
}
