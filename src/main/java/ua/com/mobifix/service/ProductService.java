package ua.com.mobifix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.*;
import ua.com.mobifix.parser.AllProductParser;

import java.sql.Timestamp;
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
    public void updateByLinkSha3(Product product) {
        // Найти товар по ссылке
//        Optional<Product> existingProductOptional = productRepository.findByLink(product.getLink());

        Optional<Product> existingProductOptional = productRepository.findByLinkSha3(SHA3.generateSHA3Hash(product.getLink()));
//        Product existingProduct = productRepository.findAllByShopIdAndCategoriesInAndLink(product.getShopId(), product.getCategories(), product.getLink());

        // Если товар найден, выполнить обновление
        if (existingProductOptional.isPresent()) {

                Product existingProduct = existingProductOptional.get();
                // Обновить поля товара
                existingProduct.setCategories(product.getCategories());
                existingProduct.setArticle(product.getArticle());
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setStock(product.getStock());
                existingProduct.setLink(product.getLink());
                existingProduct.setLinkSha3(product.getLinkSha3());
                existingProduct.setImageLink(product.getImageLink());
                existingProduct.setBreadcrumbs(product.getBreadcrumbs());
                existingProduct.setTimestampField(new Timestamp(System.currentTimeMillis()));
                productRepository.save(existingProduct);

        } else {
            // Если товар не найден, можно выбрасывать исключение или выполнять другие действия
            // Например, можно просто вывести сообщение об ошибке
            try {
                if (!product.getName().equals("")) {
                    productRepository.save(product);
                }
            } catch (DataIntegrityViolationException ex){
                ex.printStackTrace();
            }

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
            product.setLinkSha3(SHA3.generateSHA3Hash(obj.getLink()));
            product.setImageLink(obj.getImageLink());
            product.setBreadcrumbs(obj.getBreadcrumbs());
            product.setShopId(categoriesRepository.findById(idCat.intValue()).get().getShopId());
//            new Thread(() -> updateByLink(product)).start();
            updateByLinkSha3(product);


        }
        System.out.println("DB Update!");
    }
}

