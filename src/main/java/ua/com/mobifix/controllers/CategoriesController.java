package ua.com.mobifix.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.mobifix.entity.*;
import ua.com.mobifix.service.*;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
                                  String shopName){
        if(shopRepository.findAll().isEmpty()){
            model.addAttribute("subject", "Add Shop");
            model.addAttribute("showElement", false);
            return "catalog-settings";
        } else {
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
            category.setShopName(shopName);
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
                                  String shopName){
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
            category.setShopName(shopName);
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
}
