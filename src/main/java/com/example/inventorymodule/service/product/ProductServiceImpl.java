package com.example.inventorymodule.service.product;

import com.example.inventorymodule.entity.Category;
import com.example.inventorymodule.entity.Product;
import com.example.inventorymodule.repositoty.CategoryRepository;
import com.example.inventorymodule.repositoty.ProductRepository;
import com.example.inventorymodule.response.ResponseApi;
import com.example.inventorymodule.specification.ObjectFilter;
import com.example.inventorymodule.specification.ProductSpecification;
import com.example.inventorymodule.specification.SearchCriteria;
import com.example.inventorymodule.util.SQLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
     private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Page<Product> findAll(ObjectFilter filter) {
        Specification<Product> spec = Specification.where(null);

        PageRequest paging = PageRequest.of(filter.getPage() - 1, filter.getPageSize());

        if (filter.getNameProduct() != null && filter.getNameProduct().length() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.NAME, SQLConstant.LIKE,filter.getNameProduct())));
            System.out.println(filter.getNameProduct());
        }
        if (filter.getCategoryId() > 0){
            System.out.println(filter.getCategoryId());
            spec = spec.and(new ProductSpecification(new SearchCriteria("categoryId",SQLConstant.EQUAL,filter.getCategoryId())));
        }
        if (filter.getMaxPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.PRICE,SQLConstant.GREATER_THAN_OR_EQUAL_TO,filter.getMaxPrice())));
        }
        if (filter.getMinPrice() > 0){
            spec = spec.and(new ProductSpecification(new SearchCriteria(ObjectFilter.PRICE,SQLConstant.LESS_THAN_OR_EQUAL_TO,filter.getMinPrice())));
        }
        return productRepository.findAll(spec,paging);
    }

    @Override
    public ResponseApi findByCategory(String name) {
        Category category = categoryRepository.findCategoryByName(name);
        List<Product> list = productRepository.findProductsByCategoryId(category.getId());
        if (list.size() < 0){
            return new ResponseApi(HttpStatus.NOT_FOUND,"cant find any product","");
        }
        return new ResponseApi(HttpStatus.OK,"Success",list);
    }

    @Override
    public ResponseApi findByName(String name) {
        Product product = productRepository.search(name);
        return new ResponseApi(HttpStatus.OK,"Success",product);
    }

    @Override
    public ResponseApi getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return new ResponseApi(HttpStatus.NOT_FOUND,"Product not found","");
        }
        return new ResponseApi(HttpStatus.OK,"Success",product.get());
    }

    @Override
    public ResponseApi save(Product product) {
        Product exist = productRepository.findProductByName(product.getName());
        if (exist != null){
            return new ResponseApi(HttpStatus.FOUND,"Product already exist","");
        }
        else {
           Product saved = productRepository.save(product);
            return new ResponseApi(HttpStatus.CREATED,"Success",saved);
        }
    }

    @Override
    public ResponseApi delete(int id) {
        Product product = productRepository.getById(id);
        productRepository.delete(product);
        return new ResponseApi(HttpStatus.OK,"Success","");
    }

    @Override
    public ResponseApi update(int id, Product product) {
        Product exist = productRepository.getById(id);
            exist.setName(product.getName());
            exist.setPrice(product.getPrice());
            exist.setThumbnail(product.getThumbnail());
            exist.setInStock(product.getInStock());
            exist.setCategoryId(product.getCategoryId());
        productRepository.save(exist);
            return  new ResponseApi(HttpStatus.OK,"success",product);

    }
}
