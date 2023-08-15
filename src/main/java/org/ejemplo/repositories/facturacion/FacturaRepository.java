package org.ejemplo.repositories.facturacion;

import org.ejemplo.models.facturacion.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
}
