package ua.com.mobifix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.mobifix.entity.ProductRepository;

@Controller
@RequestMapping(path="/")
public class ProductController {
    private final ProductRepository productRepository;
    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

}
