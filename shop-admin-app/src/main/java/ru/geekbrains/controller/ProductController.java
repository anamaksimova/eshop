package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.service.BrandService;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;
    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @GetMapping
    public String listPage(Model model,
                         ProductListParams productListParams){
        logger.info("Product list page requested");


        model.addAttribute("products", productService.findWithFilter(productListParams));
        return "products";
    }

    @GetMapping("/new")
    public String newProductForm(Model model) {
        logger.info("New product page requested");

        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        return "product_form";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("brands", brandService.findAll());
        return "product_edit";
    }

    @PostMapping
    public String update(@Valid ProductDto product, BindingResult result) {
        logger.info("Saving product");
        if (result.hasErrors()&&product.getId()==null) {
            return "product_form";
        } else if (result.hasErrors()&&product.getId()!=null){
            return "product_edit";
        }

         //   if (product.getId()==null){
        productService.save(product);
            //}
//       else{
//           productRepository.update(product);
//       }
        return "redirect:/product";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Deleting product with id {}", id);

        productService.deleteById(id);
        return "redirect:/product";
    }
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}

