package org.ejemplo.servicios;

import org.ejemplo.exceptions.ProductException;
import org.ejemplo.modelos.Product;
import org.ejemplo.repository.ProductRepository;
import org.ejemplo.validations.ProductValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        product.setLastUpdated(new Date());

        productRepository.save(product);
    }

    public void update(String code, Product product) throws ProductException {
        productValidator.validateProductData(product);

        Product productToUpdate = productRepository.findById(code)
                .orElseThrow(() -> new ProductException(HttpStatus.PRECONDITION_FAILED, "El producto no existe.", "ProductoException"));

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setStock(product.getStock());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setLastUpdated(new Date());

        productRepository.save(productToUpdate);
    }

    public void delete(String codigo) throws ProductException {
        productValidator.validateExistingProduct(codigo);

        productRepository.deleteById(codigo);
    }

    public List<Product> getAllProducts() throws ProductException {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new ProductException(HttpStatus.NOT_FOUND, "No hay productos registrados.", "ProductoException");
        }

        return products;
    }

    public Product getProduct(String code) throws ProductException {
        return productRepository.findById(code)
                .orElseThrow(() -> new ProductException(HttpStatus.NOT_FOUND, "El producto no existe.", "ProductoException"));
    }
}
