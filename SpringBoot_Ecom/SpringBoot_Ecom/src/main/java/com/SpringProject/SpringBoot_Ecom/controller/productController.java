package com.SpringProject.SpringBoot_Ecom.controller;


import com.SpringProject.SpringBoot_Ecom.model.products;
import com.SpringProject.SpringBoot_Ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class productController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<products>> getProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<products> getProductById(@PathVariable int id){
        products product=productService.getAllProductById(id);
        if(product!=null){
           return new ResponseEntity<>(product,HttpStatus.OK) ;
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageById(@PathVariable int productId){
        products product=productService.getAllProductById(productId);
        if(product!=null){
            return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart products product, @RequestPart MultipartFile imageFile){
        products savedProduct= null;
        try {
            savedProduct = productService.addProduct(product,imageFile);
            return new ResponseEntity<>(savedProduct,HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart products product, @RequestPart MultipartFile imageFile){
        products updatedProduct= null;
        try {
            updatedProduct = productService.updateProduct(product,imageFile);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        products product=productService.getAllProductById(id);
        if(product!=null){
            productService.deleteProductById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/products/search")
    public ResponseEntity<List<products>> searchProducts(@RequestParam String keyword){
        List<products> products = productService.searchProducts(keyword);
        System.out.println("searching with :" + keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
