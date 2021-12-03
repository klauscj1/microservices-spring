package cc.dev.store.product.repository;

import cc.dev.store.product.entity.Category;
import cc.dev.store.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Long> {
    public List<Product> findByCategory(Category category);
}
