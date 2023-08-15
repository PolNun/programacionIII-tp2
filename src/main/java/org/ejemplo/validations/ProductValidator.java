package org.ejemplo.validations;

import org.ejemplo.exceptions.ProductException;
import org.ejemplo.models.Product;
import org.ejemplo.repositories.products.ProductRepository;
import org.springframework.http.HttpStatus;

public class ProductValidator {
    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validateExistingProduct(String code) throws ProductException {
        if (productRepository.existsById(code)) {
            throw new ProductException(HttpStatus.PRECONDITION_FAILED, "El producto ya existe", "ProductoException");
        }
    }

    public void validateProductData(Product product) throws ProductException {
        validateCode(product.getId());
        validateName(product.getName());
        validateDescription(product.getDescription());
        validateStock(product.getStock());
        validatePrice(product.getPrice());
    }

    private void validateCode(String code) throws ProductException {
        if (isBlank(code)) {
            throw new ProductException(HttpStatus.PRECONDITION_FAILED, "El código del producto es requerido", "ProductoException");
        }
    }

    private void validateName(String name) throws ProductException {
        if (isBlank(name)) {
            throw new ProductException(HttpStatus.PRECONDITION_FAILED, "El nombre del producto es requerido", "ProductoException");
        }
    }

    private void validateDescription(String description) throws ProductException {
        if (isBlank(description)) {
            throw new ProductException(HttpStatus.PRECONDITION_FAILED, "La descripción del producto es requerida", "ProductoException");
        }
    }

    private void validateStock(Integer stock) throws ProductException {
        if (stock == null || stock <= 0) {
            throw new ProductException(HttpStatus.PRECONDITION_FAILED, "El stock del producto es inválido", "ProductoException");
        }
    }

    private void validatePrice(Double price) throws ProductException {
        if (price == null || price <= 0) {
            throw new ProductException(HttpStatus.PRECONDITION_FAILED, "El precio del producto es inválido", "ProductoException");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
