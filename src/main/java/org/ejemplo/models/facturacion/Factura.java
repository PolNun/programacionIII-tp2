package org.ejemplo.models.facturacion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ejemplo.models.Client;
import org.ejemplo.models.users.User;

import java.util.List;

@Entity
@Table(name="facturas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nro1", nullable=false, length=4)
    private String nro1;

    @Column(name="nro2", nullable=false, length=8)
    private String nro2;

    @ManyToOne(optional = false)
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", nullable=false)
    private User seller;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factura")
    private List<DetalleFactura> detalleFacturas;

    @Column(name="total_price", nullable=false)
    private Double totalPrice;
}
