package cc.dev.store.shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Double stock;
    private String status;
    private Date createAt;
    private Category category;
}
