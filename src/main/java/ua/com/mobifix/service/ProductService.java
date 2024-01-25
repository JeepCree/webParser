package ua.com.mobifix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.*;
import ua.com.mobifix.parser.AllProductParser;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoriesRepository categoriesRepository;
    private ShopRepository shopRepository;
    @Autowired
    public ProductService (ProductRepository productRepository,
                           CategoriesRepository categoriesRepository,
                           ShopRepository shopRepository){
        this.productRepository = productRepository;
        this.categoriesRepository = categoriesRepository;
        this.shopRepository = shopRepository;
    }

    public Long getShopByCategory (Long idCat){
        return categoriesRepository.findById(idCat.intValue()).get().getShopId();
    }

    public Optional<Product> getProductByCategory (Long id){
        return productRepository.findById(id.intValue());
    }
    public void saveScanProducts (Long idCat){
        Long idShop = getShopByCategory(idCat);
        Shop settings = shopRepository.getByIdShop(idShop);
        settings.setScanProductsUrl(categoriesRepository.findById(idCat.intValue()).get().getUrl());
        AllProductParser allProductParser = new AllProductParser();

        List<Product> productList = allProductParser.getProducts(settings, idCat);
        for(Product product : productList){
                product.getArticle();
            }
        }

    }

