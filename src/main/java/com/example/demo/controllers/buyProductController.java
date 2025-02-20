package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLOutput;
import java.util.Optional;

@Controller
public class buyProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/buyItNow")
    public String buyItNow(@RequestParam("productId") Long productId) {
        Optional<Product> productTemp = productRepository.findById(productId);

        if (productTemp.isPresent()) {
            Product product = productTemp.get();
            if(product.getInv() > 0) {

                product.setInv(product.getInv() - 1);
                productRepository.save(product);
                return "productPurchaseSucess";
            }
            else {
                return "productPurchaseFail";
            }

        }

        return "productPurchaseFail";

    }
}
