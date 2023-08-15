package org.ejemplo.controllers.products;

import org.ejemplo.exceptions.ProductException;
import org.ejemplo.models.Product;
import org.ejemplo.services.products.ProductService;
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
            String code = product.getId();
            productService.update(code, product);
            return ResponseEntity.ok("Producto actualizado correctamente.");
        } catch (ProductException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @PostMapping("/delete") // TODO: modificar para recibir el id en URL
    public ResponseEntity<String> deleteProduct(@RequestBody String code) {
        try {
            productService.delete(code);
            return ResponseEntity.status(HttpStatus.OK).body("Producto eliminado correctamente");
        } catch (ProductException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> listProducts() {
        try {
            return ResponseEntity.ok(productService.getAllProducts());
        } catch (ProductException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }

    @GetMapping("/list/{code}")
    public ResponseEntity<?> listProduct(@PathVariable String code) {
        try {
            return ResponseEntity.ok(productService.getProduct(code));
        } catch (ProductException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
