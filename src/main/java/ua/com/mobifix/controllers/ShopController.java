package ua.com.mobifix.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.entity.ShopRepository;
import ua.com.mobifix.service.ShopService;
import ua.com.mobifix.service.Time;

import java.io.FileWriter;
import java.io.IOException;

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
        shop.setNameShop(newShop);
        shop.setLinkShop(link);
        shopRepository.save(shop);
        model.addAttribute("catalog", shopRepository.findAllByOrderByNameShopAsc());
        return "add-shop";
        }


    @PostMapping("/edit-shop")
    public String editShop(Model model, Long editShop ){
        String homePage = "Edit Shop";
        model.addAttribute("pageInfo", homePage);
        model.addAttribute("catalog", shopRepository.findAllByOrderByNameShopAsc());
        Shop shop = shopRepository.getByIdShop(editShop);
        model.addAttribute("shop", shop);
        return "edit-shop";
    }

    @PostMapping("/save-shop")
    public String updateShop(Model model, Long id, String name, String link){
        Shop shop = new Shop();
        shop.setNameShop(name);
        shop.setLinkShop(link);
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
    @PostMapping("/save-to-json")
    public String saveToJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("C:\\Users\\dima2\\IdeaProjects\\webParser\\src\\main\\resources\\data\\shop_export_" + Time.getTime() + ".json")) {
            gson.toJson(shopRepository.findAllByOrderByNameShopAsc(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/add-shop";
    }

}

