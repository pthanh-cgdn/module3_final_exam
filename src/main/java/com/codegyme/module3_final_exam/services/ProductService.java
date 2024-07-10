package com.codegyme.module3_final_exam.services;

import com.codegyme.module3_final_exam.models.Product;
import com.codegyme.module3_final_exam.repositories.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository = new ProductRepository();

    public boolean addProduct(Product product) {
        return productRepository.add(product);
    }


    public List<Product> getAll() {
        return productRepository.getAll();
    }


    public List<Product> viewTop(int top) {
        return productRepository.viewTop(top);
    }

    public List<Product> viewListSaleTime(String startDate, String startEnd) {
        return productRepository.viewListSaleTime(startDate,startEnd);
    }
}
