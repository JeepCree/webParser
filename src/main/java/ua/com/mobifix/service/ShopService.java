package ua.com.mobifix.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.Shop;
import ua.com.mobifix.entity.ShopRepository;

@Service
public class ShopService {
    private ShopRepository shopRepository;
    @Autowired
    public ShopService (ShopRepository shopRepository){
        this.shopRepository = shopRepository;
    }
    public Shop updateShop(Long id, Shop shop){
        Shop existingShop = shopRepository.findById(id.intValue())
                .orElseThrow(() -> new EntityNotFoundException("Магазин с id " + id + " не найден"));
        existingShop.setNameShop(shop.getNameShop());
        existingShop.setLinkShop(shop.getLinkShop());
        return shopRepository.save(existingShop);
    }
}
