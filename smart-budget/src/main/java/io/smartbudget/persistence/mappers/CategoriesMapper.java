package io.smartbudget.persistence.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import io.smartbudget.persistence.entity.Category;
import io.smartbudget.persistence.entity.User;

public interface CategoriesMapper extends GenericMapper<Category, Long> {

    List<Category> findCategories(User user);

    Category find(@Param("user") User user, @Param("categoryId") Long categoryId);

    List<String> findSuggestions(@Param("user") User user, @Param("query") String q);
}
