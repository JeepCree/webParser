package ua.com.mobifix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.CategoriesRepository;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.entity.ShopRepository;

import java.util.List;

@Service
public class ShopService {
    private ShopRepository shopRepository;
    @Autowired
    public ShopService (ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }
    public List<Shop> getAllShops(){
        return shopRepository.findAll();
    }
    public Shop save(Shop shop){
        return shopRepository.save(shop);
    }
}
