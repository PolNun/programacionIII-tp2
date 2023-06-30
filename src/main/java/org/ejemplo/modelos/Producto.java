package org.ejemplo.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Producto {
    @Id
    @Column(name = "codigo", nullable = false)
    private String codigo;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaActualizacion;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "precio", nullable = false)
    private Double precio;

    @PrePersist
    public void prePersist() {
        fechaActualizacion = new Date();
    }
}
