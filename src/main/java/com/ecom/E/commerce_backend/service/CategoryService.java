package com.ecom.E.commerce_backend.service;


import com.ecom.E.commerce_backend.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CategoryService {


    public ResponseEntity<Category> addCategory(Category category);

    public ResponseEntity<List<Category>> getAllCategory();

    public ResponseEntity<Category> getCategoryById(Long id) throws Exception;

    public void deleteCategory(Long id);

    public ResponseEntity<Category> updateCategory(Category category,Long id) throws Exception;
}
