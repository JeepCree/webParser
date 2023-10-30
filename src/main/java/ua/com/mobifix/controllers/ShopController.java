package ua.com.mobifix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.mobifix.entity.ShopRepository;

@Controller
@RequestMapping(path="/")
public class ShopController {
    private final ShopRepository shopRepository;
    @Autowired
    public ShopController(ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }
    
}

