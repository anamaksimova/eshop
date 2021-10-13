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
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.service.BrandService;
import ru.geekbrains.service.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/brand")
public class BrandController {
    private static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public String listPage(Model model){
        logger.info("brand list page requested");


        model.addAttribute("brands", brandService.findAll());
        return "brands";
    }

    @GetMapping("/new")
    public String newBrandForm(Model model) {
        logger.info("New brand page requested");

        model.addAttribute("brand", new Brand());
        return "brand_form";
    }

    @GetMapping("/{id}")
    public String editBrand(@PathVariable("id") Long id, Model model) {

        model.addAttribute("brand", brandService.findById(id)
                .orElseThrow(() -> new NotFoundException("brand not found")));
        return "brand_form";
    }

    @PostMapping
    public String update(@Valid BrandDto brand, BindingResult result) {
        logger.info("Saving brand");
        if (result.hasErrors()) {
            return "brand_form";
        }

        brandService.save(brand);

        return "redirect:/brand";
    }

    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable("id") Long id) {
        logger.info("Deleting brand with id {}", id);

        brandService.deleteById(id);
        return "redirect:/brand";
    }
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}

