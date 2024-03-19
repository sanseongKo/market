package com.market.marketJpa.service;

import com.market.marketJpa.repository.ProductRepository;
import com.market.marketJpa.vo.Product;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product write(Product product) {
        // ID 생성 로직 등은 JPA에서 자동 처리됩니다.
        return productRepository.save(product);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    // findByIdAndJoin 대응 메서드
    @Transactional(readOnly = true)
    public Optional<Product> findByIdAndJoin(int id) {
        return productRepository.findProductDetailsById(id);
    }
}