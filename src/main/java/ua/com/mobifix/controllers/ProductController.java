package ua.com.mobifix.controllers;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.mobifix.entity.Product;
import ua.com.mobifix.entity.ProductRepository;
import ua.com.mobifix.service.CsvParser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

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
        System.out.println("start");
        try {
            List<String[]> csvData = CsvParser.parseCsv("C:\\Share\\import.csv");

            for (String[] row : csvData) {
                Product product = new Product();
                product.setArticle(row[0]);
                product.setName(row[1]);
                product.setPrice(row[2]);
                product.setStock(row[3]);
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
}
