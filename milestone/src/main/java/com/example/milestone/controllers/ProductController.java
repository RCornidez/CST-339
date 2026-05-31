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

/**
 * Processes product page requests.
 * 
 * Controller that creates products, edits, deleting,
 * and listing products for authenticated users.
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UserRepository userRepository;

    /**
     * Creates a product controller.
     * 
     * @param productService manages product operations
     * @param userRepository repository of users
     */
    public ProductController(ProductService productService, UserRepository userRepository) {
        this.productService = productService;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves user from repository based on ID.
     * 
     * @param auth Authentication object of the user's session
     * @return UUID of authenticated user
     * @throws UsernameNotFoundException if user is not found
     */
    private UUID getCurrentUserId(Authentication auth) {
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + auth.getName()))
                .getId();
    }

    /**
     * Displays list of products page for authenticated user.
     * 
     * @param model of product data passed to view
     * @param auth authentication object of session
     * @return product list view
     */
    @GetMapping("/list")
    public String productListPage(Model model, Authentication auth) {
        model.addAttribute("products", productService.getAllProducts(getCurrentUserId(auth)));
        return "pages/product-list";
    }

    /**
     * Displays product creation page.
     * 
     * @param model of form data
     * @return product form view
     */
    @GetMapping("/new")
    public String createProductPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("formAction", "/products/new");
        return "pages/product";
    }

    /**
     * Handles submission of product creation form.
     * 
     * On validation, user is saved to database. Invalid submission 
     * reroutes to the same product creation view.
     * 
     * @param product is submitted product
     * @param bindingResult contains validation result of product
     * @param model this is used to return form if validation fails
     * @param auth the authentication object for session
     * @return product list if successful, if not return product form view
     */
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

    /**
     * Displays edit product form for specified user.
     * 
     * @param id of the product to edit
     * @param model used to pass product data to view
     * @param auth authentication object for session
     * @return product edit form if successful, if no product found then redirect
     * back to list
     */
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

    /**
     * Handles edit form submission for specified product.
     * 
     * Success on submit updates product, if not successful then reroute back
     * to form.
     * 
     * @param id of product being updated
     * @param product data of updated product
     * @param bindingResult validation result of product
     * @param model used to return to form if error occurs
     * @param auth authentication object for current session
     * @return product list on success, or product form view if invalid submission
     */
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

    /**
     * Returns all products owned by user as a JSON.
     * 
     * @param auth authentication object for current session
     * @return response containing user's products
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Product>> listProducts(Authentication auth) {
        return ResponseEntity.ok(productService.getAllProducts(getCurrentUserId(auth)));
    }

    /**
     * Returns a single product in JSON format.
     * 
     * @param id of product to retrieve
     * @param auth authentication object for current session
     * @return requested product or 404 error if not found
     */
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProduct(@PathVariable UUID id, Authentication auth) {
        Product product = productService.getProductById(id, getCurrentUserId(auth));
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * Creates a new product through JSON API
     * 
     * If success, product is saved to database for current user.
     * 
     * @param product the product that is submitted through request
     * @param bindingResult validation results for product
     * @param auth authentication object for current session
     * @return created product on success, 400 on failure
     */
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

    /**
     * Updates existing product through JSON format
     * 
     * @param id of the product to update
     * @param product updated product data from request body
     * @param bindingResult contains validation results for product
     * @param auth authentication object for current session
     * @return 204 success message if valid update, 400 if validation fails, 404 if product
     * not found
     */
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

    /**
     * Deletes specific product for current user.
     * 
     * @param id of product to delete
     * @param auth authentication object for the session
     * @return redirect to product list page
     */
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable UUID id, Authentication auth) {
        productService.deleteProduct(id, getCurrentUserId(auth));
        return "redirect:/products/list";
    }
}
