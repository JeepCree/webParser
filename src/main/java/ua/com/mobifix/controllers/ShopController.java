package ua.com.mobifix.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.entity.ShopRepository;
import ua.com.mobifix.service.ShopService;

@Controller
@RequestMapping(path="/")
public class ShopController {
    private final ShopRepository shopRepository;
    private final ShopService shopService;
    @Autowired
    public ShopController(ShopRepository shopRepository, ShopService shopService){
        this.shopRepository = shopRepository;
        this.shopService = shopService;
    }
    @PostMapping("/add-shop")
    public String setNewShop(Model model, String newShop, String link){
        Shop shop = new Shop();
        shop.setName(newShop);
        shop.setLink(link);
        shopRepository.save(shop);
        model.addAttribute("catalog", shopRepository.findAll());
        return "add-shop";
        }

    @PostMapping("/edit-shop")
    public String editShop(Model model, Long editShop ){
        String homePage = "Edit Shop";
        model.addAttribute("pageInfo", homePage);
        model.addAttribute("catalog", shopRepository.findAll());
        Shop shop = shopRepository.getById(editShop);
        model.addAttribute("shop", shop);
        return "edit-shop";
    }
    @PostMapping("/save-shop")
    public String saveShop(Model model, Long id, String name, String link){
        Shop shop = new Shop();
        shop.setName(name);
        shop.setLink(link);
        shopService.updateShop(id, shop);
        return "redirect:/add-shop";
    }
    @PostMapping("/delete-shop")
    public String deleteShop(Model model, Integer deleteShop){
        if (shopRepository.existsById(deleteShop)) {
            shopRepository.deleteById(deleteShop);
        } else {
            return "redirect:/add-shop";
        }
        return "redirect:/add-shop";
    }
}

