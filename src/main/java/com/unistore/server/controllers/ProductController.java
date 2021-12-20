package com.unistore.server.controllers;

import com.unistore.server.models.Pagination;
import com.unistore.server.models.Product;
import com.unistore.server.models.ResponseObject;
import com.unistore.server.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    //DI = Dependency Injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllProducts(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "6") int limit,
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "catalog", required = false, defaultValue = "") String catalog,
            @RequestParam(name = "processer", required = false, defaultValue = "") String processer,
            @RequestParam(name = "hardDrive", required = false, defaultValue = "") String hardDrive
    ) {
        int offset = limit * (page - 1);
        List<Product> foundProducts = repository.findByQuery(search, catalog, processer, hardDrive);
        int totalRow = foundProducts.size();
        int totalPage = (int) Math.ceil( (float)totalRow / limit );
        Pagination pagination = new Pagination(page, totalPage, totalRow);

        if(page<0 || page>totalPage) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Page not found", "", "")
            );
        }

        List<Product> products = repository.findByPage(limit, offset, search, catalog, processer, hardDrive);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Request all products successfully", products, pagination)
        );
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    ResponseEntity<ResponseObject> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "6") int limit,
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "catalog", required = false, defaultValue = "") String catalog,
            @RequestParam(name = "processer", required = false, defaultValue = "") String processer,
            @RequestParam(name = "hardDrive", required = false, defaultValue = "") String hardDrive
    ) {
        int offset = limit * (page - 1);
        List<Product> foundProducts = repository.findByQuery(search, catalog, processer, hardDrive);
        int totalRow = foundProducts.size();
        int totalPage = (int) Math.ceil( (float)totalRow / limit );
        Pagination pagination = new Pagination(page, totalPage, totalRow);

        if(page<0 || page>totalPage) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Page not found", "", "")
            );
        }

        List<Product> products = repository.findByAdmin(limit, offset, search, catalog, processer, hardDrive);
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
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
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
                                            product.setQuantity(newProduct.getQuantity());
                                            product.setSold(newProduct.getSold());
                                            product.setStatus(newProduct.getStatus());
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

    @GetMapping("/mostsold")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> listProductMostSold() {
        List<Product> list = repository.findByMostSold();

        return ResponseEntity.ok().body(
                new ResponseObject("ok", "List product most sold", list)
        );
    }
}
