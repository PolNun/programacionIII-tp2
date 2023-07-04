package org.ejemplo.controladores;

import org.ejemplo.exceptions.ProductException;
import org.ejemplo.modelos.Product;
import org.ejemplo.servicios.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> registerProduct(@RequestBody Product product) {
        try {
            productService.register(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto registrado correctamente");
        } catch (ProductException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        try {
            String code = product.getCode();
            productService.update(code, product);
            return ResponseEntity.ok("Producto actualizado correctamente.");
        } catch (ProductException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody String code) {
        try {
            productService.delete(code);
            return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado correctamente");
        } catch (ProductException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
