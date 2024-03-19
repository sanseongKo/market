package com.market.marketJpa.controller;

import com.market.marketJpa.service.ProductService;
import com.market.marketJpa.vo.Product;
import com.market.marketJpa.vo.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@Log4j2
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/write")
    public ResponseResult<Product> write(@RequestBody Product product) {
        Product result = productService.write(product);
        return ResponseResult.success("0000", result);
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return ResponseResult.success("0000", null);
    }

    @GetMapping("/{id}")
    public Optional<Product> findByIdAndJoin(@PathVariable int id) {
        return productService.findByIdAndJoin(id);
    }
}
