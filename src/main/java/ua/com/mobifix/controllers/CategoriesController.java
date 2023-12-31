package ua.com.mobifix.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.mobifix.entity.*;
import ua.com.mobifix.parser.CategoryParser;
import ua.com.mobifix.parser.ScanCategorySettings;
import ua.com.mobifix.service.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/")
public class CategoriesController {
    private final CategoriesRepository categoriesRepository;
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ShopService shopService;
    private final ProductService productService;

    @Autowired
    public CategoriesController(CategoriesRepository categoriesRepository,
                                CategoryService categoryService,
                                ShopService shopService,
                                ShopRepository shopRepository,
                                ProductService productService,
                                ProductRepository productRepository){
        this.categoriesRepository = categoriesRepository;
        this.categoryService = categoryService;
        this.shopService = shopService;
        this.shopRepository = shopRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }
    @PostMapping("/catalog-settings")
    private String addNewCategory(Model model,
                                  String newCategory,
                                  Long parentCategory,
                                  Boolean isActive,
                                  String description,
                                  String metaTitle,
                                  String metaDescription,
                                  String metaKeywords,
                                  String humanReadableUrl,
                                  String urlImage,
                                  Long shopId){
        if(shopRepository.findAll().isEmpty()){
            model.addAttribute("subject", "Add Shop");
            model.addAttribute("showElement", false);
            return "catalog-settings";
        } else {
            if (parentCategory.equals(null)){
                parentCategory = 0L;
            }
            model.addAttribute("showElement", false);
            Categories category = new Categories();
            category.setName(newCategory);
            category.setParentId(parentCategory);
            category.setActive(isActive);
            category.setDescription(description);
            category.setMetaTitle(metaTitle);
            category.setMetaDescription(metaDescription);
            category.setMetaKeywords(metaKeywords);
            category.setHumanReadableUrl(humanReadableUrl);
            category.setUrlImage(urlImage);
            category.setShopId(shopId);
            System.out.println(shopId);
            categoriesRepository.save(category);
            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            try {
                String categoriesJson = objectMapper.writeValueAsString(categoriesRepository.findAll());
                model.addAttribute("jsonString", categoriesJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addAttribute("catalog", categoriesRepository.findAll());
            model.addAttribute("shops", shopRepository.findAll());
            model.addAttribute("showElement", true);
            return "catalog-settings";
        }
    }
    @PostMapping("/getCatalog")
    public String getCatalog(@RequestBody String requestBody, Model model) throws JsonProcessingException {
        model.addAttribute("pageInfo", "Edit Category");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            Long categoryId = jsonNode.get("categoryId").asLong();
            String categoriesJson = objectMapper.writeValueAsString(categoriesRepository.findAllByOrderByNameAsc());
            model.addAttribute("jsonString", categoriesJson);
            List<Long> categoryIds = Arrays.asList(categoryId);
            model.addAttribute("catalog", categoriesRepository.findAllByIdNotInOrderByNameAsc(categoryIds));
            model.addAttribute("shops", shopRepository.findAll());
            model.addAttribute("category", categoryService.findCategoryById(categoryId));
            model.addAttribute("parentCategoryName", categoryService.findCategoryById(categoryService.findCategoryById(categoryId).getParentId()).getName());
            model.addAttribute("parentCategoryId", categoryService.findCategoryById(categoryService.findCategoryById(categoryId).getParentId()).getId());
            return "edit-catalog";
        } catch (Exception e) {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            Long categoryId = jsonNode.get("categoryId").asLong();
            model.addAttribute("parentCategoryName", "Parent");
            return "edit-catalog";
        }
    }
    @PostMapping("/get-all-catalog")
    public String getAllCatalog(@RequestBody String requestBody, Model model) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String categoriesJson = objectMapper.writeValueAsString(categoriesRepository.findAllByOrderByNameAsc());
            model.addAttribute("jsonString", categoriesJson);
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            String categoryIds = jsonNode.get("categoryIds").asText();
            List<Long> categoryIdsList = Arrays.asList(categoryIds.split(","))
                    .stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            model.addAttribute("categoryId", categoryIds);
            model.addAttribute("pageInfo", "Products");
            model.addAttribute("productList", productRepository.findAllByCategoriesIn(categoryIdsList));
            return "catalog";
        } catch (Exception e) {
           e.printStackTrace();
            return "catalog";
        }
    }

    @PostMapping("/save-category")
    private String saveCategory(Model model,
                                  Long id,
                                  String name,
                                  Long parentCategory,
                                  Boolean active,
                                  String description,
                                  String metaTitle,
                                  String metaDescription,
                                  String metaKeywords,
                                  String humanReadableUrl,
                                  String urlImage,
                                  Long shopId){
        if (parentCategory.equals(null)){
            parentCategory = 0L;
        }
            Categories category = new Categories();
            category.setName(name);
            category.setParentId(parentCategory);
            category.setActive(active);
            category.setDescription(description);
            category.setMetaTitle(metaTitle);
            category.setMetaDescription(metaDescription);
            category.setMetaKeywords(metaKeywords);
            category.setHumanReadableUrl(humanReadableUrl);
            category.setUrlImage(urlImage);
            category.setShopId(shopId);
            categoryService.updateCategory(id, category);
            return "redirect:/catalog-settings";
    }
    @PostMapping("/delete-category")
    private String deleteCategory(Long id){
        if (categoriesRepository.existsById(id.intValue())) {
            categoriesRepository.deleteById(id.intValue());
        } else {
            return "redirect:/catalog-settings";
        }
        return "redirect:/catalog-settings";
    }
    @PostMapping("/save-categories-to-json")
    public String saveCartegoriesToJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("C:\\Users\\dima2\\OneDrive\\Рабочий стол\\webParser\\src\\main\\resources\\data\\categories_export_" + Time.getTime() + ".json")) {
            gson.toJson(categoriesRepository.findAllByOrderByNameAsc(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/catalog-settings";
    }

    @PostMapping("/get-store-catalog")
    @ResponseBody
    public List<Categories> getShops(@RequestBody String requestBody, Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(requestBody);
        Long shopId = jsonNode.get("store").asLong();

        return categoriesRepository.findAllByShopIdOrderByNameAsc(shopId);
    }
    @GetMapping("/import-categories")
    public String importCsv() {
        System.out.println("start");
        try {
            List<String[]> csvData = CsvParser.parseCsv("C:\\Share\\import_categories.csv");

            for (String[] row : csvData) {
                Categories categories = new Categories();
                categories.setId(Long.parseLong(row[0]));
                categories.setParentId(Long.parseLong(row[1]));
                categories.setName(row[2]);
//                categories.setUrlImage(row[3]);
//                categories.setMetaKeywords(row[4]);
//                categories.setDescription(row[5]);
//                categories.setMetaTitle(row[6]);
//                categories.setMetaDescription(row[7]);
//                categories.setShopName(row[8]);
//                categories.setActive(Boolean.parseBoolean(row[9]));
                categoriesRepository.save(categories);
            }
            System.out.println("ok");
            return "catalog"; // Можете вернуть имя представления для успешного импорта
        } catch (IOException e) {
            e.printStackTrace();
            return "catalog"; // Можете вернуть имя представления для неудачи импорта
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/scan-catalog")
    @ResponseBody
    private void addNewCategory1(Model model){
        ScanCategorySettings settings = new ScanCategorySettings();
        CategoryParser parser = new CategoryParser();
        settings.setShopName("all-spares.ua");
        settings.setUrlShop("https://all-spares.ua");
        settings.setSelectCategoryTag("body > div > div > header > div.widget_site-menu > div > div.menu-row > div > div > div.layout_header_quick-catalog_menu_nav > a");
        settings.setSelectCategoryNameTag("a");
        settings.setSelectCategoryAttrHref("href");
        settings.setUrlPrefix("https://all-spares.ua");
        settings.setSelectCategoryTagLevel2("body > div > div > div.sop-main-content > div > div > main > div.page_menu_category > div > div > article > div > a");
        settings.setSelectCategoryNameTagLevel2("a");
        settings.setSelectCategoryAttrHrefLevel2("href");
        settings.setSelectCategoryTagLevel3("body > div > div > div.sop-main-content > div > div > div > div.col-12.col-md-8.col-lg-9 > main > div.row.d-flex.product-cards-wrapper > div > div > div > a");
        settings.setSelectCategoryNameTagLevel3("a");
        settings.setSelectCategoryAttrHrefLevel3("href");
        settings.setSelectCategoryTagLevel4("body > div > div > div.sop-main-content > div > div > div > div.col-12.col-md-8.col-lg-9 > main > div.row.d-flex.product-cards-wrapper > div > div > div > a");
        settings.setSelectCategoryNameTagLevel4("a");
        settings.setSelectCategoryAttrHrefLevel4("href");
        settings.setSelectCategoryTagLevel5("body > div > div > div.sop-main-content > div > div > div > div.col-12.col-md-8.col-lg-9 > main > div.row.d-flex.product-cards-wrapper > div > div > div > a");
        settings.setSelectCategoryNameTagLevel5("a");
        settings.setSelectCategoryAttrHrefLevel5("href");
        Map<String, String> cookies = new HashMap<>();
        cookies.put("visitor", "d0de6e4eb1e4479708300ffeee4bbcbb");
        cookies.put("auth", "F98JfjM3DF%2BVMLtW7J6XJfwBpwUz16Eb7hfplc7eUbqIcOcmo4ugkcvwdoWqa39lQjJWtuMFN8k3EtG5fGcYFQ");
        cookies.put("language", "5");
        settings.setCookies(cookies);
        settings.setShopId(5);
        Categories cat = new Categories();
        cat.setId(50001L);
        cat.setDescription("sdfvsd");
        cat.setName("Cat 1");
        cat.setUrl("httP://");
        cat.setParentId(0L);
        cat.setShopId(5L);
        ArrayList<Categories> listCat = new ArrayList<>();
        listCat.add(cat);
//        for (AllScanCategory el : parser.getCatalog(settings, 0L)) {
        for (Categories el : listCat) {
            categoriesRepository.updateOrSaveById(el);
//            System.out.println(el.getCategoryId() + " " + el.getCategoryName() + " " + el.getCategoryUrl() + " " + el.getParentCategoryId());
        }
    }
}
