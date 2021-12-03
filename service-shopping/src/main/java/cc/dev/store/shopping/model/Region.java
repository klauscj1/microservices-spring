package cc.dev.store.shopping.model;

import lombok.Data;
import org.hibernate.annotations.Proxy;

@Data
public class Region {
    private Long id;
    private String name;
}
