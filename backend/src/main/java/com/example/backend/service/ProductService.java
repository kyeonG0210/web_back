package com.example.backend.service;

import com.example.backend.entity.Product;
import com.example.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product add(Product product) {
        return repository.save(product);
    }

    public Product getByTitle(String title) {
        return repository.findByTitle(title).orElse(null);
    }

    public Product update(Long id, Product updated) {
        return repository.findById(id).map(p -> {
            p.setTitle(updated.getTitle());
            p.setAuthor(updated.getAuthor());
            p.setPublisher(updated.getPublisher());
            p.setUserId(updated.getUserId());
            return repository.save(p);
        }).orElse(null);
    }

    public boolean deleteByTitle(String title) {
        Optional<Product> product = repository.findByTitle(title);
        if (product.isPresent()) {
            repository.delete(product.get());
            return true;
        }
        return false;
    }
}
