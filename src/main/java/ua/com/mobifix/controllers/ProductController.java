package ua.com.mobifix.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.mobifix.entity.Product;
import ua.com.mobifix.entity.ProductRepository;
import ua.com.mobifix.entity.ShopRepository;
import ua.com.mobifix.parser.ProductParser;
import ua.com.mobifix.parser.breadcrumbs.BreadcrumbsToCatalogParser;
import ua.com.mobifix.parser.entity.ScanProductSettings;
import ua.com.mobifix.service.CsvParser;
import ua.com.mobifix.service.ProductService;
import ua.com.mobifix.service.SHA3;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(path="/")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ShopRepository shopRepository;
    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService, ShopRepository shopRepository, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration){
        this.productRepository = productRepository;
        this.productService = productService;
        this.shopRepository = shopRepository;
        this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
    }

    @GetMapping("/import-product")
    public String importCsv() {
        try {
            List<String[]> csvData = CsvParser.parseCsv("C:\\Share\\import.csv");

            for (String[] row : csvData) {
                Product product = new Product();
                product.setArticle(row[0]);
                product.setName(row[1]);
                product.setPrice(Double.parseDouble(row[2]));
                product.setStock(row[3]);
                product.setLink(row[4]);
                product.setCategories(Long.parseLong(row[5]));
                // Установите другие поля по мере необходимости

                product.setTimestampField(new Timestamp(System.currentTimeMillis()));
                productRepository.save(product);
            }
            return "catalog"; // Можете вернуть имя представления для успешного импорта
        } catch (IOException e) {
            e.printStackTrace();
            return "catalog"; // Можете вернуть имя представления для неудачи импорта
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/save-sheet")
    @ResponseBody
    public boolean getAllCatalog(@RequestBody String requestBody, Model model) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            Long productId = jsonNode.get("rowId").asLong();
            String columnName = jsonNode.get("columnName").asText();
            String newValue = jsonNode.get("newValue").asText();
            if (columnName.equals("name")){
                Product product = productRepository.findById(productId.intValue()).get();
                product.setName(newValue);
                productRepository.save(product);
                System.out.println("Новое значение: " + newValue);
            } else if (columnName.equals("stock")){
                Product product = productRepository.findById(productId.intValue()).get();
                product.setStock(newValue);
                productRepository.save(product);
            } else if (columnName.equals("price")) {
                Product product = productRepository.findById(productId.intValue()).get();
                product.setPrice(Double.parseDouble(newValue));
                productRepository.save(product);
            } else if (columnName.equals("link")) {
                Product product = productRepository.findById(productId.intValue()).get();
                product.setLink(newValue);
                productRepository.save(product);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping({"/add-new-product"})
    @ResponseBody
    public boolean saveNewProduct(String catId, String name, String stock, String price, String link, Model model) {
        Product product = new Product();
        System.out.println(productRepository.findTopByOrderByIdDesc());
        if (productRepository.findTopByOrderByIdDesc() == null){
            product.setArticle("1");
        } else {
            product.setArticle(this.productRepository.findTopByOrderByIdDesc().getArticle() + 1);
        }
        product.setCategories(Long.parseLong(catId));
        product.setName(name);
        product.setStock(stock);
        product.setPrice(Double.parseDouble(price));
        product.setLink(link);
        this.productRepository.save(product);
        return true;
    }

    @GetMapping("/save-scan-products")
    @ResponseBody
    public void saveScanProducts(Long categoryId){
            productService.saveScanProducts(categoryId);
        System.out.println("Category is update!");
    }

    @PostMapping("/save-scan-product")
    @ResponseBody
    public void saveScanProduct(ScanProductSettings settings, Long shopId) {

    }

    @PostMapping("/full-edit-product")
    @ResponseBody
    public JsonNode fullEditProduct(@RequestBody Map<String, String> requestBody) {
        String article = requestBody.get("article");

        // Добавьте необходимую обработку ошибок, например, проверку на null или отсутствие статьи

        Product optionalProduct = productRepository.findByArticle(article);

        // Обработайте случай, если optionalProduct равен null

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonProduct = objectMapper.valueToTree(optionalProduct);

        return jsonProduct;
    }

    @PostMapping("/delete-product")
    @ResponseBody
    public boolean deleteProduct(@RequestParam int catId) {

        try {
            productRepository.deleteById(catId);
            return true;
        } catch (EmptyResultDataAccessException e) {
            // Обработка ошибки, например, запись не найдена
            e.printStackTrace(); // Замените это на ваш обработчик ошибок
            return false;
        } catch (Exception e) {
            // Обработка других ошибок удаления
            e.printStackTrace(); // Замените это на ваш обработчик ошибок
            return false;
        }
    }

    @GetMapping("/link-to-sha3")
    @ResponseBody
    public void linkToSha3() {
        List<Product> productList = productRepository.findAllByLinkSha3IsNull();
        System.out.println("Run");

        if (!productList.isEmpty()) {
            for (Product product : productList) {
                product.setLinkSha3(SHA3.generateSHA3Hash(product.getLink()));

                try {
                    productRepository.save(product);
                } catch (Exception e){
                    e.getMessage();
                }

            }
            } else{
                System.out.println("Нет продуктов с пустым linkSha3");
            }
        System.out.println("End");
    }

    @GetMapping("/scan-product-for-shop")
    @ResponseBody
    public void scanProductForShop(@RequestParam(defaultValue = "<") String operator,
                                   Long shopId,
                                   @RequestParam(required = false) Timestamp time,
                                   Long categoryId
    ) throws InterruptedException {
        List<String> allLinks = new ArrayList<>();
        if (time == null) {
            time = new Timestamp(System.currentTimeMillis());
        }
            switch (operator.toLowerCase()) {
                case "<" -> allLinks = productRepository.findLinksByShopIdAndTimestampFieldBefore(shopId, time);
                case ">" -> allLinks = productRepository.findLinksByShopIdAndTimestampFieldAfter(shopId, time);
                case "=" -> allLinks = productRepository.findLinksByShopIdAndTimestampFieldEqual(shopId, time);
                case "category" -> allLinks = productRepository.findLinksByCategoryId(shopId, categoryId);
                case "bynulldescription" -> allLinks = productRepository.findLinksByShopIdAndDescriptionIsNull(shopId);
                default -> throw new IllegalArgumentException("Неверный оператор: " + operator);
            }
//            allLinks = productRepository.findLinksByShopIdAndTimestampFieldBefore(shopId, time);
//            allLinks = productRepository.findAllLinksByShopId(shopId);

        ProductParser productParser = new ProductParser();
        System.out.println("quantity of links: " + allLinks.size());

        for (String link : allLinks) {
            // Извлекаем Product из Optional
            Product product = productRepository.findByLink(link).orElse(null);

            if (product == null) {
                product = new Product();
            }
            System.out.println("\u001B[36m" + "Start parse product" + "\u001B[0m");
            Product productExec = productParser.getProduct(shopRepository.findByIdShop(shopId), link, shopId);

            if (productExec == null) {
                productRepository.findByLink(link).ifPresent(productRepository::delete);
            } else {
                System.out.println("\u001B[33m" + "execute product from base" + "\u001B[0m");
                product.setArticle(productExec.getArticle());
                product.setBreadcrumbs(productExec.getBreadcrumbs());
                product.setDescription(productExec.getDescription());
                product.setTimestampField(productExec.getTimestampField());
                product.setPrice(productExec.getPrice());
//                if (productExec.getPcs() == ""){
//                    product.setPcs(0);
//                }
                product.setPcs(productExec.getPcs());
                product.setStock(productExec.getStock());
                System.out.println("\u001B[35m" + "upgrade product: " + "\u001B[0m" + product.getArticle());
//                System.out.println("\u001B[35m" + "product pcs " + "\u001B[0m" + product.getPcs());
                System.out.println("\u001B[35m" + "breadcrumbs data: " + "\u001B[0m" + product.getBreadcrumbs());
                System.out.println("\u001B[35m" + "description data: " + "\u001B[0m" + product.getDescription());
                if(product.getTimestampField() != null){
                    productRepository.save(product);
                }  else {
                    System.out.println("Имя товара отсутствует. Товар не сохранен! \n" + product.getLink());
                }

                System.out.println("\u001B[32m" + "save product to DB\n" + "\u001B[0m");


                Thread.sleep(500); // Если пауза необходима
            }
        }
        System.out.println("Descriptions & Breadcrumps is update!");
    }

    @GetMapping("/create-catalog-for-shop")
    @ResponseBody
    public void createCatalogForShop(Long shopId) {

        // Получаем список breadcrumbs для магазина
        List<String> breadcrumbs = productRepository.findAllBreadcrumbsByShopId(shopId);

        // Проверяем, что список не пуст и содержит не только null значения
        if (breadcrumbs == null || breadcrumbs.isEmpty()) {
            System.out.println("No breadcrumbs found for the shop with ID: " + shopId);
            return;
        }

        // Удаляем все null значения из списка
        breadcrumbs.removeIf(Objects::isNull);

        // Преобразуем список в массив строк
        String[] breadcrumbsArray = breadcrumbs.toArray(new String[0]);

        // Создаем объект парсера и строим каталог
        BreadcrumbsToCatalogParser breadcrumbsToCatalogParser = new BreadcrumbsToCatalogParser();
        Map<String, Object> catalog = breadcrumbsToCatalogParser.buildCatalog(breadcrumbsArray);

        // Записываем каталог в файл JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter fileWriter = Objects.requireNonNull(new FileWriter("src/main/resources/data/categories/catalog_" + shopRepository.findByIdShop(shopId)
                .getNameShop() + "_" + Timestamp.valueOf(LocalDateTime.now()).toString().replace(":", "-").replace(" ", "_") + ".json"))) {
            gson.toJson(catalog, fileWriter);
            System.out.println("Catalog has been saved to catalog.json");
        } catch (IOException e) {
            System.err.println("Error writing catalog to file: " + e.getMessage());
        }
    }


}

