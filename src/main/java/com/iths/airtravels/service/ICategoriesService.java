package com.iths.airtravels.service;

import com.iths.airtravels.entity.Categories;

import java.util.List;

public interface ICategoriesService {

    List<Categories> getAllCategories();
    Categories getCategories(Long id);
    Categories saveCategories(Categories categories);
    Categories addCategory(Categories categories);
}
