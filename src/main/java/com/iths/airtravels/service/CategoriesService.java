package com.iths.airtravels.service;

import com.iths.airtravels.entity.Categories;
import com.iths.airtravels.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements ICategoriesService{

    private CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories getCategories(Long id) {
        return categoriesRepository.getOne(id);
    }

    @Override
    public Categories saveCategories(Categories categories) {
        return categoriesRepository.save(categories);
    }

    @Override
    public Categories addCategory(Categories categories) {
        return categoriesRepository.save(categories);
    }
}
