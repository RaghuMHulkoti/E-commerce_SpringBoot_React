package com.SpringProject.SpringBoot_Ecom.service;

import com.SpringProject.SpringBoot_Ecom.model.products;
import com.SpringProject.SpringBoot_Ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<products> getAllProducts(){
       return repo.findAll();
    }

    public products getAllProductById(int id) {
      return repo.findById(id).orElse(new products());
    }

    public products addProduct(products product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public products updateProduct(products product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public void deleteProductById(int id) {
        repo.deleteById(id);

    }

    public List<products> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }
}
