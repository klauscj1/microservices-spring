package cc.dev.store.shopping.client;

import cc.dev.store.shopping.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping(value = "/api/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) ;

}
