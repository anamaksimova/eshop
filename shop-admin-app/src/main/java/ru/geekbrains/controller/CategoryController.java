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
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.Product;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listPage(Model model){
        logger.info("Category list page requested");


        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/new")
    public String newCategoryForm(Model model) {
        logger.info("New Category page requested");

        model.addAttribute("category", new Category());
        return "category_form";
    }

    @GetMapping("/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {

        model.addAttribute("category", categoryService.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found")));
        return "category_form";
    }

    @PostMapping
    public String update(@Valid Category category, BindingResult result) {
        logger.info("Saving category");
        if (result.hasErrors()) {
            return "category_form";
        }

        categoryService.save(category);

        return "redirect:/category";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        logger.info("Deleting Category with id {}", id);

        categoryService.deleteById(id);
        return "redirect:/category";
    }
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}

