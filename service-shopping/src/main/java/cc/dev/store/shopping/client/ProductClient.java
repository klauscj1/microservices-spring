package cc.dev.store.shopping.client;

import cc.dev.store.shopping.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")

public interface ProductClient {

    @GetMapping(value = "/api/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);

    @GetMapping(value = "/api/products/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,@RequestParam(value = "quantity", required = true)  Double quantity);
}
