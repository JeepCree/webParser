package ua.com.mobifix.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.Shop;

import java.util.List;
import java.util.Optional;

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
    public Categories findCategoryById(Long categoryId) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(categoryId.intValue());
        return optionalCategory.orElse(null); // возвращает объект Categories или null, если он отсутствует
    }
    public Categories updateCategory (Long id, Categories category){
        Categories existingCategory = categoriesRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("Категория с id " + id + " не найден"));
        existingCategory.setActive(category.getActive());
        existingCategory.setName(category.getName());
        existingCategory.setParentId(category.getParentId());
        existingCategory.setRootCategory(category.isRootCategory());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setMetaTitle(category.getMetaTitle());
        existingCategory.setMetaDescription(category.getMetaDescription());
        existingCategory.setMetaKeywords(category.getMetaKeywords());
        existingCategory.setHumanReadableUrl(category.getHumanReadableUrl());
        existingCategory.setUrlImage(category.getUrlImage());
        existingCategory.setShopName(category.getShopName());
        return categoriesRepository.save(existingCategory);
    }
}
