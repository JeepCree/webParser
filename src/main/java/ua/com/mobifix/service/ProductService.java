package ua.com.mobifix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.ProductRepository;
import ua.com.mobifix.entity.ShopRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public void getProductByCategory(Iterable ids){
//        productRepository.get
    }


}
