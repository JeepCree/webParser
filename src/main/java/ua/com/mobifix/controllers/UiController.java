package ua.com.mobifix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class UiController {
    @GetMapping("/")
    private String getIndexPage(Model model){
        String homePage = "Home Page";
        model.addAttribute("pageInfo", homePage);
        return "index";
    }
    @GetMapping("/add-shop")
    private String getAddShopPage(Model model){
        String homePage = "Add Shop";
        model.addAttribute("pageInfo", homePage);
        return "add-shop";
    }
    @GetMapping("/shop-settings")
    private String getShopSettingsPage(Model model){
        String homePage = "Shop Settings";
        model.addAttribute("pageInfo", homePage);
        return "shop-settings";
    }
    @GetMapping("/catalog")
    private String getCatalogPage(Model model){
        String homePage = "Catalog";
        model.addAttribute("pageInfo", homePage);
        return "catalog";
    }
    @GetMapping("/catalog-settings")
    private String getCatalogSettingsPage(Model model){
        String homePage = "Catalog Settings";
        model.addAttribute("pageInfo", homePage);
        return "catalog-settings";
    }
}