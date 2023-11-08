package ua.com.mobifix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.Shop;

import java.util.List;

@Service
public class CategoryService {
    private CategoriesRepository categoriesRepository;
    @Autowired
    public CategoryService(CategoriesRepository categoriesRepository){
        this.categoriesRepository = categoriesRepository;
    }
    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }

}
