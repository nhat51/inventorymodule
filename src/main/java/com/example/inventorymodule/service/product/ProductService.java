package com.example.inventorymodule.service.product;

import com.example.inventorymodule.entity.Product;
import com.example.inventorymodule.response.ResponseApi;
import com.example.inventorymodule.specification.ObjectFilter;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAll(ObjectFilter filter);
    ResponseApi findByCategory(String name);
    ResponseApi findByName(String name);
    ResponseApi getById(int id);
    ResponseApi save(Product product);
    ResponseApi delete(int id);
    ResponseApi update(int id, Product product);
}
