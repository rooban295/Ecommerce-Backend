package com.ecom.E.commerce_backend.controller;


import com.ecom.E.commerce_backend.model.Category;
import com.ecom.E.commerce_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @PostMapping("/add")
    public ResponseEntity<Category> addCategory (@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) throws Exception {
       return categoryService.getCategoryById(id);
    }


    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category) throws Exception {
        return categoryService.updateCategory(category,id);
    }
}
