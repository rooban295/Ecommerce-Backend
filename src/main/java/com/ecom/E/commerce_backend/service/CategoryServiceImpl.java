package com.ecom.E.commerce_backend.service;

import com.ecom.E.commerce_backend.model.Category;
import com.ecom.E.commerce_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public ResponseEntity<Category> addCategory(Category category) {
        return new ResponseEntity<>(categoryRepo.save(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(categoryRepo.findAll());
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long id) throws Exception {
        Optional<Category> category=categoryRepo.findById(id);
        if(category.isEmpty()){
            throw new Exception("Category not found");
        }
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public ResponseEntity<Category> updateCategory(Category category,Long id) throws Exception {
        Optional<Category> updatedCategory=categoryRepo.findById(id);
        if(updatedCategory.isEmpty()){
            throw new Exception("Category not found");
        }
        updatedCategory.get().setCategoryName(category.getCategoryName());
        return new ResponseEntity<>(categoryRepo.save(updatedCategory.get()),HttpStatus.ACCEPTED);
    }
}
