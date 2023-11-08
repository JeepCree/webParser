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
import ua.com.mobifix.entity.ShopRepository;
import ua.com.mobifix.service.CategoryService;
import ua.com.mobifix.service.ShopService;

@Controller
@RequestMapping(path="/")
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;
    private final ShopRepository shopRepository;
    private final CategoryService categoryService;
    private final ShopService shopService;
    @Autowired
    public CategoriesController(CategoriesRepository categoriesRepository,
                                CategoryService categoryService,
                                ShopService shopService,
                                ShopRepository shopRepository){
        this.categoriesRepository = categoriesRepository;
        this.categoryService = categoryService;
        this.shopService = shopService;
        this.shopRepository = shopRepository;
    }
    @PostMapping("/catalog-settings")
    private String addNewCategory(Model model,
                                  String newCategory,
                                  Long parentCategory,
                                  Boolean isActive,
                                  String description,
                                  String metaTitle,
                                  String metaDescription,
                                  String metaKeywords,
                                  String humanReadableUrl,
                                  String urlImage,
                                  String shopName){
        if(shopRepository.findAll().isEmpty()){
            model.addAttribute("subject", "Add Shop");
            model.addAttribute("showElement", false);
            return "catalog-settings";
        } else {
            model.addAttribute("showElement", false);
            Categories category = new Categories();
            category.setName(newCategory);
            category.setParentId(parentCategory);
            category.setActive(isActive);
            category.setDescription(description);
            category.setMetaTitle(metaTitle);
            category.setMetaDescription(metaDescription);
            category.setMetaKeywords(metaKeywords);
            category.setHumanReadableUrl(humanReadableUrl);
            category.setUrlImage(urlImage);
            category.setShopName(shopName);
            categoriesRepository.save(category);
            model.addAttribute("catalog", categoriesRepository.findAll());
            model.addAttribute("shops", shopRepository.findAll());
            model.addAttribute("showElement", true);
            return "catalog-settings";
        }
    }
}
