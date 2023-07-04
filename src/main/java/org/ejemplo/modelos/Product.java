package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @Column(name = "code", nullable = false)
    private String code;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "last_updated", nullable = false)
    private Date lastUpdated;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "price", nullable = false)
    private Double price;

    @PrePersist
    public void prePersist() {
        lastUpdated = new Date();
    }
}
