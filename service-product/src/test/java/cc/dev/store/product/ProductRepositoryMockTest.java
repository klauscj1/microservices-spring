package cc.dev.store.product;

import cc.dev.store.product.entity.Category;
import cc.dev.store.product.entity.Product;
import cc.dev.store.product.repository.ProductRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest

public class ProductRepositoryMockTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    public void whenFindByCategory_thenReturnListProduct() {
        Product product1 = Product.builder().name("computer").category(
                Category.builder().id(1L).build()).description("").stock(Double.parseDouble("10")).price(Double.parseDouble("1240.99")).status("Created").createAt(new Date()).build();

        productRepository.save(product1);

        List<Product> founds=productRepository.findByCategory(product1.getCategory());


        Assertions.assertThat(founds.size()).isEqualTo(3);
    }
}
