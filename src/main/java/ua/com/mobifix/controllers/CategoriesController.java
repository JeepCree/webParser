package ua.com.mobifix.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            try {
                String categoriesJson = objectMapper.writeValueAsString(categoriesRepository.findAll());
                model.addAttribute("jsonString", categoriesJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("catalog", categoriesRepository.findAll());
            model.addAttribute("shops", shopRepository.findAll());
            model.addAttribute("showElement", true);
            return "catalog-settings";
        }
    }
    @PostMapping("/getCatalog")
    public String getCatalog(@RequestBody String requestBody, Model model) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            Long categoryId = jsonNode.get("categoryId").asLong();
            model.addAttribute("pageInfo", "Edit Category");
            String categoriesJson = objectMapper.writeValueAsString(categoriesRepository.findAll());
            model.addAttribute("jsonString", categoriesJson);
            model.addAttribute("catalog", categoriesRepository.findAll());
            model.addAttribute("shops", shopRepository.findAll());
            model.addAttribute("category", categoryService.findCategoryById(categoryId));
            return "edit-catalog";
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace();
            return "error";
        }
    }
    @PostMapping("/save-category")
    private String saveCategory(Model model,
                                  Long id,
                                  String name,
                                  Long parentCategory,
                                  Boolean active,
                                  String description,
                                  String metaTitle,
                                  String metaDescription,
                                  String metaKeywords,
                                  String humanReadableUrl,
                                  String urlImage,
                                  String shopName){
            Categories category = new Categories();
            category.setName(name);
            category.setParentId(parentCategory);
            category.setActive(active);
            category.setDescription(description);
            category.setMetaTitle(metaTitle);
            category.setMetaDescription(metaDescription);
            category.setMetaKeywords(metaKeywords);
            category.setHumanReadableUrl(humanReadableUrl);
            category.setUrlImage(urlImage);
            category.setShopName(shopName);
            categoryService.updateCategory(id, category);
            return "redirect:/catalog-settings";
    }
    @PostMapping("/delete-category")
    private String deleteCategory(Long id){
        if (categoriesRepository.existsById(id.intValue())) {
            categoriesRepository.deleteById(id.intValue());
        } else {
            return "redirect:/catalog-settings";
        }
        return "redirect:/catalog-settings";
    }
}
