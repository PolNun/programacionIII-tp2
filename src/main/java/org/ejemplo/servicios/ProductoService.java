package org.ejemplo.servicios;

import org.ejemplo.exceptions.ProductoException;
import org.ejemplo.modelos.Producto;
import org.ejemplo.repository.ProductoRepository;
import org.ejemplo.validations.ProductoValidations;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoValidations productoValidations;

    public ProductoService(ProductoRepository productoRepository, ProductoValidations productoValidations) {
        this.productoRepository = productoRepository;
        this.productoValidations = productoValidations;
    }

    public void registrarProducto(Producto producto) throws ProductoException {
        productoValidations.validateExistingProduct(producto.getCodigo());

        productoRepository.save(producto);
    }

    public void actualizarProducto(Producto producto) throws ProductoException {
        productoValidations.validateExistingProduct(producto.getCodigo());

        productoRepository.save(producto);
    }

    public void eliminarProducto(String codigo) throws ProductoException {
        productoValidations.validateExistingProduct(codigo);

        productoRepository.deleteById(codigo);
    }
}
