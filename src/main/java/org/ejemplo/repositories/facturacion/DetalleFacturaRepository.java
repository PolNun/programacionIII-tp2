package org.ejemplo.repositories.facturacion;

import org.ejemplo.models.facturacion.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Integer> {
}
