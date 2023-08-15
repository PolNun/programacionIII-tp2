package org.ejemplo.models.facturacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ejemplo.models.Product;

@Entity
@Table(name="detalle_facturas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @Column(name="stock", nullable=false)
    private Integer stock;

    @Column(name="price_per_unit", nullable=false)
    private Double pricePerUnit;

    @Column(name="total_price", nullable=false)
    private Double totalPrice;
}
