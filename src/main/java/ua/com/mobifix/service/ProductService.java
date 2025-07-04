package ua.com.mobifix.service;

import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.mobifix.entity.*;
import ua.com.mobifix.parser.AllProductParser;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
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
    Optional<Product> existingProductOptional = productRepository.findByLinkSha3(SHA3.generateSHA3Hash(product.getLink()));

        // Если товар найден, выполнить обновление
        if (existingProductOptional.isPresent()) {

                Product existingProduct = existingProductOptional.get();
                // Обновить поля товара
                existingProduct.setCategories(product.getCategories());
                if (product.getArticle() != null && !product.getArticle().isEmpty()) {
                    existingProduct.setArticle(product.getArticle());
                }
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setStock(product.getStock());
                existingProduct.setLink(product.getLink());
                existingProduct.setLinkSha3(product.getLinkSha3());
                existingProduct.setImageLink(product.getImageLink());
//                existingProduct.setBreadcrumbs(product.getBreadcrumbs());
//                existingProduct.setDescription(product.getDescription());
                existingProduct.setChpu(product.getChpu());
                existingProduct.setTimestampField(new Timestamp(System.currentTimeMillis()));

                productRepository.save(existingProduct);
//            System.out.println("ТОВАР ОБНОВЛЕН!");
        } else {
            try {
                if (!product.getName().equals("")) {
                    productRepository.save(product);
                    System.out.println("\u001B[32mДобавлен новый товар: " +
                            "\n" + product.getArticle() +
                            "\n" + product.getName() +
                            "\n" + product.getPrice() +
                            "\n" + product.getStock() +
                            "\n" + product.getLink() +
                            "\u001B[0m"
                    );
                } else {
                System.out.println("\u001B[33mИмя товара пустое — товар не сохранен.\u001B[0m");
            }
            } catch (DataIntegrityViolationException ex){
                ex.printStackTrace();
                System.out.println("\u001B[31mОШИБКА!!! ТОВАР НЕ ДОБАВЛЕН!\u001B[0m");
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
//            product.setBreadcrumbs(obj.getBreadcrumbs());
//            product.setDescription(obj.getDescription());
            product.setChpu(Slugify.builder().locale(new Locale("ru")).build().slugify(obj.getName()));
            product.setShopId(categoriesRepository.findById(idCat.intValue()).get().getShopId());
            product.setTimestampField(new Timestamp(System.currentTimeMillis()));
//            new Thread(() -> updateByLink(product)).start();
            new Thread(() -> updateByLinkSha3(product)).start();



        }
        System.out.println("DB Update!");
    }

}

