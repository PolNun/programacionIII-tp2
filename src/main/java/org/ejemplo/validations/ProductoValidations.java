package org.ejemplo.validations;

import org.ejemplo.exceptions.ProductoException;
import org.ejemplo.repository.ProductoRepository;

public class ProductoValidations {
    private final ProductoRepository productoRepository;

    public ProductoValidations(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public void validateExistingProduct(String codigo) throws ProductoException {
        if (productoRepository.findById(codigo).isPresent()) {
            throw new ProductoException("El producto ya está registrado");
        }
    }
}
