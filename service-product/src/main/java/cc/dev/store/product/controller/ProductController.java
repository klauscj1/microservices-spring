package cc.dev.store.product.controller;


import cc.dev.store.product.entity.Category;
import cc.dev.store.product.entity.Product;
import cc.dev.store.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId) {
        List<Product> products = new ArrayList<>();
        if (categoryId == null) {
            products = productService.listAllProduct();
        } else {
            products = productService.findByCategory(Category.builder().id(categoryId).build());
        }
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Product productCreated = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        Product productUpdated = productService.updateProduct(product);
        if (productUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
        Product productDeleted = productService.deleteProduct(id);
        if (productDeleted == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDeleted);
    }

    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,@RequestParam(value = "quantity", required = true)  Double quantity){
        Product productStockUpdated =productService.updateStock(id,quantity);
        if (productStockUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productStockUpdated);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors=result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error=new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage=ErrorMessage.builder().code("01").messages(errors).build();
        ObjectMapper mapper=new ObjectMapper();
        String jsonString="";
        try{
            jsonString=mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonString;
    }
}
