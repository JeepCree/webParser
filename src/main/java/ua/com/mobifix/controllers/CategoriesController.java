package ua.com.mobifix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.ProductRepository;
import ua.com.mobifix.service.CategoryService;

@Controller
@RequestMapping(path="/")
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;
    private final CategoryService categoryService;
    @Autowired
    public CategoriesController(CategoriesRepository categoriesRepository,
                                CategoryService categoryService){
        this.categoriesRepository = categoriesRepository;
        this.categoryService = categoryService;
    }
    @PostMapping("/catalog-settings")
    private String getNewCategory(Model model, String newCategory){
        Categories category = new Categories();
        category.setName(newCategory);
        categoriesRepository.save(category);
        model.addAttribute("catalog", categoriesRepository.findAll());
        return "catalog-settings";
    }
}
