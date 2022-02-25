package com.example.inventorymodule.service.category;

import com.example.inventorymodule.entity.Category;
import com.example.inventorymodule.response.ResponseApi;
public interface CategoryService {
    ResponseApi findAll();
    ResponseApi findById(int id);
    ResponseApi save(Category category);
    ResponseApi update(int id, Category category);
    ResponseApi delete(int id);
}
