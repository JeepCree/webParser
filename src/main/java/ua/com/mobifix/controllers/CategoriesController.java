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
import ua.com.mobifix.parser.ProductParser;
import ua.com.mobifix.service.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/")
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
                                ProductRepository productRepository) {
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
                                  Long shopId) {
        if (shopRepository.findAll().isEmpty()) {
            model.addAttribute("subject", "Add Shop");
            model.addAttribute("showElement", false);
            return "catalog-settings";
        } else {
            if (parentCategory.equals(null)) {
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
                                Long shopId) {
        if (parentCategory.equals(null)) {
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
    private String deleteCategory(Long id) {
        if (categoriesRepository.existsById(id.intValue())) {
            categoriesRepository.deleteById(id.intValue());
        } else {
            return "redirect:/catalog-settings";
        }
        return "redirect:/catalog-settings";
    }

    @PostMapping("/save-categories-to-json")
    public String saveCartegoriesToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String basePath = Paths.get("").toAbsolutePath().toString(); // корень проекта
        String filePath = basePath + "/data/categories_export_" + Time.getTime() + ".json";
        try (FileWriter writer = new FileWriter(filePath)) {
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

    @GetMapping("/set-shop-settings")
    @ResponseBody
    private void setShopSettings(Long shopId) {
        Shop shop = new Shop();
        shop.setIdShop(shopId);
        shop.setNameShop("vseplus.com");
        shop.setLinkShop("https://vseplus.com/");
        shop.setUrlCategoryPrefix("https://vseplus.com");
        shop.setUrlCategoryPrefixLevel2("https://vseplus.com");
        shop.setUrlCategoryPrefixLevel3("noEl");
        shop.setUrlCategoryPrefixLevel4("noEl");
        shop.setUrlCategoryPrefixLevel5("noEl");
        shop.setSelectCategoryTag("div.menu-box__menu-item.menu-box__menu-item_catalog > div > div > ul > li > a");
        shop.setSelectCategoryTagLevel2("section.sidebar__item.sidebar__item_root-preview > ul > li > div > ul > li > a");
        shop.setSelectCategoryTagLevel3("noEl");
        shop.setSelectCategoryTagLevel4("noEl");
        shop.setSelectCategoryTagLevel5("noEl");
        shop.setSelectCategoryNameTag("a");
        shop.setSelectCategoryNameTagLevel2("a");
        shop.setSelectCategoryNameTagLevel3("noEl");
        shop.setSelectCategoryNameTagLevel4("noEl");
        shop.setSelectCategoryNameTagLevel5("noEl");
        shop.setSelectCategoryAttrHref("href");
        shop.setSelectCategoryAttrHrefLevel2("href");
        shop.setSelectCategoryAttrHrefLevel3("noEl");
        shop.setSelectCategoryAttrHrefLevel4("noEl");
        shop.setSelectCategoryAttrHrefLevel5("noEl");
        shop.setCookies("SEARCH_SAMESITE=CgQIjZoB");
        shop.setReplaceCategoryName("");
        shop.setReplaceCategoryNameLevel2("");
        shop.setReplaceCategoryNameLevel3("");
        shop.setReplaceCategoryNameLevel4("");
        shop.setReplaceCategoryNameLevel5("");
        shop.setReplaceCategoryUrl("");
        shop.setReplaceCategoryUrlLevel2("");
        shop.setReplaceCategoryUrlLevel3("");
        shop.setReplaceCategoryUrlLevel4("");
        shop.setReplaceCategoryUrlLevel5("");
        shop.setNoScanCategoryList("");
        shopRepository.save(shop);
    }

    @GetMapping("/scan-shop-catalog")
    @ResponseBody
    private void addNewShopCategory(Model model, Long shopId) {
        CategoryParser categoryParser = new CategoryParser();
        Shop shop = shopRepository.getByIdShop(shopId);

        Map<String, String> cookies = new HashMap<>();
        String[] cookiePairs = shop.getCookies().split("; ");
        for (String cookiePair : cookiePairs) {
            String[] parts = cookiePair.split("=");
            if (parts.length == 2) {
                cookies.put(parts[0], parts[1]);
            }
        }

//
//        shop.setNameShop(shop.getNameShop());
//        shop.setLinkShop(shop.getLinkShop());
//        shop.setUrlCategoryPrefix(shop.getUrlCategoryPrefix());
//        shop.setUrlCategoryPrefixLevel2(shop.getUrlCategoryPrefixLevel2());
//        shop.setUrlCategoryPrefixLevel3(shop.getUrlCategoryPrefixLevel3());
//        shop.setUrlCategoryPrefixLevel4(shop.getUrlCategoryPrefixLevel4());
//        shop.setUrlCategoryPrefixLevel5(shop.getUrlCategoryPrefixLevel5());
//        shop.setSelectCategoryTag(shop.getSelectCategoryTag());
//        shop.setSelectCategoryTagLevel2(shop.getSelectCategoryTagLevel2());
//        shop.setSelectCategoryTagLevel3(shop.getSelectCategoryTagLevel3());
//        shop.setSelectCategoryTagLevel4(shop.getSelectCategoryTagLevel4());
//        shop.setSelectCategoryTagLevel5(shop.getSelectCategoryTagLevel5());
//        shop.setSelectCategoryNameTag(shop.getSelectCategoryNameTag());
//        shop.setSelectCategoryNameTagLevel2(shop.getSelectCategoryNameTagLevel2());
//        shop.setSelectCategoryNameTagLevel3(shop.getSelectCategoryNameTagLevel3());
//        shop.setSelectCategoryNameTagLevel4(shop.getSelectCategoryNameTagLevel4());
//        shop.setSelectCategoryNameTagLevel5(shop.getSelectCategoryNameTagLevel5());
//        shop.setSelectCategoryAttrHref(shop.getSelectCategoryAttrHref());
//        shop.setSelectCategoryAttrHrefLevel2(shop.getSelectCategoryAttrHrefLevel2());
//        shop.setSelectCategoryAttrHrefLevel3(shop.getSelectCategoryAttrHrefLevel3());
//        shop.setSelectCategoryAttrHrefLevel4(shop.getSelectCategoryAttrHrefLevel4());
//        shop.setSelectCategoryAttrHrefLevel5(shop.getSelectCategoryAttrHrefLevel5());
//        if (shop.getReplaceCategoryName() == null){
//            shop.setReplaceCategoryName("");
//        }
//        shop.setReplaceCategoryName(shop.getReplaceCategoryName());
//        if (shop.getReplaceCategoryNameLevel2() == null){
//            shop.setReplaceCategoryNameLevel2("");
//        }
//        shop.setReplaceCategoryNameLevel2(shop.getReplaceCategoryNameLevel2());
//        if (shop.getReplaceCategoryNameLevel3() == null){
//            shop.setReplaceCategoryNameLevel3("");
//        }
//        shop.setReplaceCategoryNameLevel3(shop.getReplaceCategoryNameLevel3());
//        if (shop.getReplaceCategoryNameLevel4() == null){
//            shop.setReplaceCategoryNameLevel4("");
//        }
//        shop.setReplaceCategoryNameLevel4(shop.getReplaceCategoryNameLevel4());
//        if (shop.getReplaceCategoryNameLevel5() == null){
//            shop.setReplaceCategoryNameLevel5("");
//        }
//        shop.setReplaceCategoryNameLevel5(shop.getReplaceCategoryNameLevel5());
//
//        shop.setReplaceCategoryUrl(shop.getReplaceCategoryUrl());
//        if (shop.getReplaceCategoryUrlLevel2() == null){
//            shop.setReplaceCategoryUrlLevel2("");
//        }
//        shop.setReplaceCategoryUrlLevel2(shop.getReplaceCategoryUrlLevel2());
//        if (shop.getReplaceCategoryUrlLevel3() == null){
//            shop.setReplaceCategoryUrlLevel3("");
//        }
//        shop.setReplaceCategoryUrlLevel3(shop.getReplaceCategoryUrlLevel3());
//        if (shop.getReplaceCategoryUrlLevel4() == null){
//            shop.setReplaceCategoryUrlLevel4("");
//        }
//        shop.setReplaceCategoryUrlLevel4(shop.getReplaceCategoryUrlLevel4());
//        if (shop.getReplaceCategoryUrlLevel5() == null){
//            shop.setReplaceCategoryUrlLevel5("");
//        }
//        shop.setReplaceCategoryUrlLevel5(shop.getReplaceCategoryUrlLevel5());
//
//        shop.setCookies(shop.getCookies());
//
//        shop.setNoScanCategoryList(shop.getNoScanCategoryList());

        for (Categories el : categoryParser.getCatalog(shop, categoriesRepository.findFirstByOrderByIdDesc().getId())) {
            categoriesRepository.updateOrSaveByUrl(el);
        }
        System.out.println("End of scan!");
    }

    @GetMapping("/run")
    @ResponseBody
    public void run() {
        List<Long> list = new ArrayList<>();
        for (Categories categories : categoriesRepository.findAll()) {
            if (categoriesRepository.findByParentId(categories.getId()).isEmpty()) {
                list.add(categories.getId());
            }
        }
        for (Long id : list) {
            productService.saveScanProducts(id);
        }
        System.out.println("Shop is update!");
    }

    @GetMapping("/run-for-shop")
    @ResponseBody
    public void runForShop(Long shopId) {
        List<Long> list = new ArrayList<>();
        for (Categories categories : categoriesRepository.findAllByShopId(shopId)) {
            if (categoriesRepository.findByParentId(categories.getId()).isEmpty()) {
                list.add(categories.getId());
            }
        }
        for (Long id : list) {
//            new Thread(() -> productService.saveScanProducts(id);
            productService.saveScanProducts(id);
        }
        System.out.println("Shop is update!");
    }



}
