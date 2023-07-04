package org.ejemplo.repository;

import org.ejemplo.modelos.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
