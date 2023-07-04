package org.ejemplo.servicios;

import org.ejemplo.exceptions.ProductException;
import org.ejemplo.modelos.Product;
import org.ejemplo.repository.ProductRepository;
import org.ejemplo.validations.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductValidator productValidator;

    public ProductService(ProductRepository productRepository, ProductValidator productValidator) {
        this.productRepository = productRepository;
        this.productValidator = productValidator;
    }

    public void register(Product product) throws ProductException {
        productValidator.validateExistingProduct(product.getCode());
        productValidator.validateProductData(product);

        productRepository.save(product);
    }

    public void update(String code, Product product) throws ProductException {
        productValidator.validateProductData(product);

        Product existingProduct = productRepository.findById(code)
                .orElseThrow(() -> new ProductException(HttpStatus.PRECONDITION_FAILED, "El producto no existe.", "ProductoException"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setStock(product.getStock());
        existingProduct.setPrice(product.getPrice());

        productRepository.save(existingProduct);
    }

    public void delete(String codigo) throws ProductException {
        if (!productValidator.productExists(codigo)) {
            throw new ProductException(HttpStatus.PRECONDITION_FAILED, "El producto no existe.", "ProductoException");
        }

        productRepository.deleteById(codigo);
    }
}
