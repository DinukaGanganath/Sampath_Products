package com.sampathproducts.Product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ProductController {

    private ProductDao dao;

    public ProductController(ProductDao dao) {
        this.dao = dao;
    }

    // create mapping ui
    @RequestMapping(value = "/products")
    public ModelAndView materialUI() {
        ModelAndView viewMaterial = new ModelAndView();
        viewMaterial.setViewName("Product/Product.html");
        return viewMaterial;
    }

    // get database values as json data
    @GetMapping(value = "/products/findall", produces = "application/json")
    public List<Product> findAll() {
        return dao.findAll();
    }
}
