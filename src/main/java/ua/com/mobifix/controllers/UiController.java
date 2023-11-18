package ua.com.mobifix.controllers;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.entity.ShopRepository;
import ua.com.mobifix.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Controller
@RequestMapping(path="/")
public class UiController {
    private CategoryService categoryService;
    private ShopRepository shopRepository;
    private CategoriesRepository categoriesRepository;
    @Autowired
    public UiController(CategoryService categoryService,
                 ShopRepository shopRepository,
                 CategoriesRepository categoriesRepository){
        this.categoryService = categoryService;
        this.shopRepository = shopRepository;
        this.categoriesRepository = categoriesRepository;
    }
    @GetMapping("/")
    public String getIndexPage(Model model){
        String homePage = "Home Page";
        model.addAttribute("pageInfo", homePage);
        return "index";
    }
    @GetMapping("/add-shop")
    public String getAddShopPage(Model model){
        String homePage = "Add Shop";
        model.addAttribute("pageInfo", homePage);
        model.addAttribute("catalog", shopRepository.findAll());
        return "add-shop";
    }
    @GetMapping("/shop-settings")
    public String getShopSettingsPage(Model model){
        String homePage = "Shop Settings";
        model.addAttribute("pageInfo", homePage);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        try {
            String categoriesJson = objectMapper.writeValueAsString(categoriesRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "shop-settings";
    }
    @GetMapping("/catalog")
    public String getCatalogPage(Model model){
        String homePage = "Catalog";
        model.addAttribute("pageInfo", homePage);
        return "catalog";
    }
    @GetMapping("/catalog-settings")
    public String getCatalogSettingsPage(Model model){
        String homePage = "Catalog Settings";
        model.addAttribute("pageInfo", homePage);
        if(shopRepository.findAll().isEmpty()){
            model.addAttribute("subject", "Add Shop");
            model.addAttribute("showElement", false);
            return "catalog-settings";
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String categoriesJson = objectMapper.writeValueAsString(categoriesRepository.findAll());
                model.addAttribute("jsonString", categoriesJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("showElement", true);
            model.addAttribute("catalog", categoryService.getAllCategories());
            model.addAttribute("shops", shopRepository.findAll());
        }
        return "catalog-settings";
    }
}