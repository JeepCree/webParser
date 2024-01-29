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
    public ProductService(ProductRepository productRepository,
                          CategoriesRepository categoriesRepository,
                          ShopRepository shopRepository) {
        this.productRepository = productRepository;
        this.categoriesRepository = categoriesRepository;
        this.shopRepository = shopRepository;
    }
    public void updateByLink(Product product) {
        // Найти товар по ссылке
        Optional<Product> existingProductOptional = productRepository.findByLink(product.getLink());

        // Если товар найден, выполнить обновление
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            // Обновить поля товара
            existingProduct.setArticle(product.getArticle());
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setStock(product.getStock());
            existingProduct.setImageLink(product.getImageLink());
            // Другие поля, которые нужно обновить

            // Сохранить обновленный товар
            productRepository.save(existingProduct);
        } else {
            // Если товар не найден, можно выбрасывать исключение или выполнять другие действия
            // Например, можно просто вывести сообщение об ошибке
            productRepository.save(product);
        }
    }

    public Long getShopByCategory(Long idCat) {
        return categoriesRepository.findById(idCat.intValue()).get().getShopId();
    }

    public Optional<Product> getProductByCategory(Long id) {
        return productRepository.findById(id.intValue());
    }

    public void saveScanProducts(Long idCat) {
        Long idShop = getShopByCategory(idCat);
        Shop settings = shopRepository.getByIdShop(idShop);
        settings.setScanProductsUrl(categoriesRepository.findById(idCat.intValue()).get().getUrl());
        AllProductParser allProductParser = new AllProductParser();

        List<Product> productList = allProductParser.getProducts(settings, idCat);
        for (Product obj : productList) {

            Product product = new Product();
            product.setArticle(obj.getArticle());
            product.setName(obj.getName());
            product.setStock(obj.getStock());
            product.setPrice(obj.getPrice());
            product.setCategories(obj.getCategories());
            product.setLink(obj.getLink());
            product.setImageLink(obj.getImageLink());
            product.setBreadcrumbs(obj.getBreadcrumbs());
            product.setShopId(categoriesRepository.findById(idCat.intValue()).get().getShopId());

            updateByLink(product);

        }

    }
}

