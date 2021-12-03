package cc.dev.store.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "tbl_products")
@Proxy(lazy = false)
@AllArgsConstructor @NoArgsConstructor @Builder
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;

    private String description;
    private Double price;

    @Positive(message = "El stock debe ser positivo")
    private Double stock;

    private String status;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @NotNull(message = "La categoria no puede ser nula/vacia")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;
}

