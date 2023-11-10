package ua.com.mobifix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.mobifix.entity.Categories;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.entity.ShopRepository;

@Controller
@RequestMapping(path="/")
public class ShopController {
    private final ShopRepository shopRepository;
    @Autowired
    public ShopController(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }
    @PostMapping("/add-shop")
    private String setNewShop(Model model, String newShop, String link){
        Shop shop = new Shop();
        shop.setName(newShop);
        shop.setLink(link);
        shopRepository.save(shop);
        model.addAttribute("catalog", shopRepository.findAll());
        return "add-shop";
        }
    @PostMapping("/edit-shop")
    private String editShop(Model model, String newShop, String link){
        return "redirect:/add-shop";
    }
    @PostMapping("/delete-shop")
    private String deleteShop(Model model, Integer deleteShop){
        if (shopRepository.existsById(deleteShop)) {
            shopRepository.deleteById(deleteShop);
        } else {
            return "redirect:/add-shop";
        }
        return "redirect:/add-shop";
    }
}

