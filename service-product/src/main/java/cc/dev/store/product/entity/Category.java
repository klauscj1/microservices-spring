package cc.dev.store.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;


import javax.persistence.*;

@Entity
@Table(name = "tbl_categories")
@Proxy(lazy = false)
@AllArgsConstructor @NoArgsConstructor @Builder
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
