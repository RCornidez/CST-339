package com.example.milestone.controllers;

import com.example.milestone.models.Product;
import com.example.milestone.repositories.UserRepository;
import com.example.milestone.services.ProductService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final ProductService productService;
    private final UserRepository userRepository;

    public ProductController(ProductService productService, UserRepository userRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
    }

    private UUID getCurrentUserId(Authentication auth) {
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + auth.getName()))
                .getId();
    }

    @GetMapping("/list")
    public String productListPage(Model model, Authentication auth) {
        model.addAttribute("products", productService.getAllProducts(getCurrentUserId(auth)));
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
                                      BindingResult bindingResult, Model model, Authentication auth) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/products/new");
            return "pages/product";
        }
        product.setUserId(getCurrentUserId(auth));
        productService.addProduct(product);
        return "redirect:/products/list";
    }

    @GetMapping("/{id}/edit")
    public String editProductPage(@PathVariable UUID id, Model model, Authentication auth) {
        Product product = productService.getProductById(id, getCurrentUserId(auth));
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
                                      BindingResult bindingResult, Model model, Authentication auth) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/products/" + id + "/edit");
            return "pages/product";
        }
        UUID userId = getCurrentUserId(auth);
        product.setUserId(userId);
        productService.updateProduct(id, userId, product);
        return "redirect:/products/list";
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Product>> listProducts(Authentication auth) {
        return ResponseEntity.ok(productService.getAllProducts(getCurrentUserId(auth)));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProduct(@PathVariable UUID id, Authentication auth) {
        Product product = productService.getProductById(id, getCurrentUserId(auth));
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product,
                                                 BindingResult bindingResult, Authentication auth) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        product.setUserId(getCurrentUserId(auth));
        Product created = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @Valid @RequestBody Product product,
                                              BindingResult bindingResult, Authentication auth) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        boolean updated = productService.updateProduct(id, getCurrentUserId(auth), product);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable UUID id, Authentication auth) {
        productService.deleteProduct(id, getCurrentUserId(auth));
        return "redirect:/products/list";
    }
}
