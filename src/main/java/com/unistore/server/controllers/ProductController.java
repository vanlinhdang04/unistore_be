package com.unistore.server.controllers;

import com.unistore.server.models.Pagination;
import com.unistore.server.models.Product;
import com.unistore.server.models.ResponseObject;
import com.unistore.server.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    //DI = Dependency Injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    //  This request is: http://localhost:8080/api/v1/Products/
//    List<Product> getAllProducts() {
//        return repository.findAll();
//    }
    ResponseEntity<ResponseObject> getAllProducts(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "2") int limit) {
        int offset = limit * (page - 1);
        int totalRow = repository.findAll().size();
        int totalPage = (int) Math.ceil(totalRow / limit);
        Pagination pagination = new Pagination(page, totalPage, totalRow);

        if(page<0 || page>totalPage) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Page not found", "", "")
            );
        }

        List<Product> products = repository.findByPage(limit, offset);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Request all products successfully", products, pagination)
        );
    }

    //Get detail product
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObject("ok", "Query product successfully", foundProduct)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
              new ResponseObject("failed", "Cannot find product with id = "+id, "")
            );
        }
    }

    //Insert new product with POST method http://localhost:8080/api/v1/Products/insert
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        //Example about case OS already taken
        List<Product> foundProduct = repository.findByNamee(newProduct.getName().trim());
        if(foundProduct.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert new product successfully", repository.save(newProduct))
        );
    }

    //update, upsert = update if found, otherwise insert http://localhost:8080/api/v1/Products/{id}
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updateProduct = repository.findById(id)
                                        .map(product -> {
                                            product.setName(newProduct.getName());
                                            product.setBattery(newProduct.getBattery());
                                            product.setCatalog(newProduct.getCatalog());
                                            product.setDescription(newProduct.getDescription());
                                            product.setGraphics(newProduct.getGraphics());
                                            product.setHardDrive(newProduct.getHardDrive());
                                            product.setImage(newProduct.getImage());
                                            product.setMemory(newProduct.getMemory());
                                            product.setPrice(newProduct.getPrice());
                                            product.setOS(newProduct.getOS());
                                            product.setProcesser(newProduct.getProcesser());
                                            product.setWireless(newProduct.getWireless());
                                            return repository.save(product);
                                        }).orElseGet(() -> {
                                            newProduct.setProductId(id);
                                            return repository.save(newProduct);
                                        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update product successfully", updateProduct)
        );
    }

    //Delete a product -> method DELETE
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean existProduct = repository.existsById(id);

        if(existProduct) {
            Optional<Product> foundProduct = repository.findById(id).map(
                    product -> {
                        product.setStatus("0");
                        return repository.save(product);
                    }
            );
//            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", foundProduct)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Connot find product to delete", "")
        );
    }
}
