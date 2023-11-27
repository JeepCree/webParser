package ua.com.mobifix.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.mobifix.entity.Product;
import ua.com.mobifix.entity.ProductRepository;
import ua.com.mobifix.service.CsvParser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path="/")
public class ProductController {
    private final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
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
                product.setStock(Integer.getInteger(row[3]));
                product.setLink(row[4]);
                product.setCategories(Long.parseLong(row[5]));
                // Установите другие поля по мере необходимости

                product.setTimestampField(new Timestamp(System.currentTimeMillis()));
                productRepository.save(product);
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
            } else if (columnName.equals("stock")){
                Product product = productRepository.findById(productId.intValue()).get();
                product.setStock(Integer.getInteger(newValue));
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
            System.out.println(productId + " / " + columnName + " / " + newValue);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
