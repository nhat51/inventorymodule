package com.example.inventorymodule.repositoty;

import com.example.inventorymodule.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findCategoryByName(String name);
}
