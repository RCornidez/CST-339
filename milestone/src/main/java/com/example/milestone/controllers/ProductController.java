package com.example.milestone.controllers;

import com.example.milestone.models.Product;
import com.example.milestone.services.ProductService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/products")
public class ProductController {

    // TODO: we will replace with authenticated user from JWT later.
    // Hardcoded to temporarily handle the user constraint
    private static final UUID MOCK_USER_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        model.addAttribute("products", productService.getAllProducts(MOCK_USER_ID));
        return "pages/product-list";
    }

    @GetMapping("/new")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("formAction", "/products/new");
        return "pages/product";
    }

    @PostMapping("/new")
    public String createProductSubmit(@Valid @ModelAttribute("product") Product product,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/products/new");
            return "pages/product";
        }
        product.setUserId(MOCK_USER_ID);
        productService.addProduct(product);
        return "redirect:/products/list";
    }

    @GetMapping("/{id}/edit")
    public String editProductPage(@PathVariable UUID id, Model model) {
        Product product = productService.getProductById(id, MOCK_USER_ID);
        if (product == null) {
            return "redirect:/products/list";
        }
        model.addAttribute("product", product);
        model.addAttribute("formAction", "/products/" + id + "/edit");
        return "pages/product";
    }

    @PostMapping("/{id}/edit")
    public String updateProductSubmit(@PathVariable UUID id,
                                      @Valid @ModelAttribute("product") Product product,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/products/" + id + "/edit");
            return "pages/product";
        }
        product.setUserId(MOCK_USER_ID);
        productService.updateProduct(id, MOCK_USER_ID, product);
        return "redirect:/products/list";
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Product>> listProducts() {
        return ResponseEntity.ok(productService.getAllProducts(MOCK_USER_ID));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        
        Product product = productService.getProductById(id, MOCK_USER_ID);
        
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        product.setUserId(MOCK_USER_ID);

        Product created = productService.addProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @Valid @RequestBody Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        boolean updated = productService.updateProduct(id, MOCK_USER_ID, product);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id, MOCK_USER_ID);
        return "redirect:/products/list";
    }
}
