package com.sampathproducts.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.ModuleRole.ModuleRoleController;
import com.sampathproducts.ProductHasMaterial.ProductHasMaterial;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

@RestController
public class ProductController {

    @Autowired
    private ProductDao dao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    /*
     * public ProductController(ProductDao dao) {
     * this.dao = dao;
     * }
     */

    // create mapping ui
    @RequestMapping(value = "/product")
    public ModelAndView productUI() {
        ModelAndView viewProduct = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProduct.addObject("logusername", auth.getName());
        viewProduct.setViewName("Product/Product.html");
        return viewProduct;
    }

    @RequestMapping(value = "/productadd")
    public ModelAndView productAddUI() {
        ModelAndView viewProductAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductAdd.addObject("logusername", auth.getName());
        viewProductAdd.setViewName("Product/ProductAdd.html");
        return viewProductAdd;
    }

    @RequestMapping(value = "/productdeleted")
    public ModelAndView productDeletedItems() {
        ModelAndView viewProductAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductAdd.addObject("logusername", auth.getName());
        viewProductAdd.setViewName("Product/ProductDeleted.html");
        return viewProductAdd;
    }

    @RequestMapping(value = "/productedit")
    public ModelAndView productEditUI() {
        ModelAndView viewProductEdit = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductEdit.addObject("logusername", auth.getName());
        viewProductEdit.setViewName("Product/ProductEdit.html");
        return viewProductEdit;
    }

    @RequestMapping(value = "/productview")
    public ModelAndView productViewUI() {
        ModelAndView viewProductView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductView.addObject("logusername", auth.getName());
        viewProductView.setViewName("Product/ProductView.html");
        return viewProductView;
    }

    @RequestMapping(value = "/productstores")
    public ModelAndView productStoresUI() {
        ModelAndView viewProductView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductView.addObject("logusername", auth.getName());
        viewProductView.setViewName("Product/ProductStore.html");
        return viewProductView;
    }

    // get database values as json data
    @GetMapping(value = "/product/findall", produces = "application/json")
    public List<Product> findAll() {
        return dao.findAll();
    }

    // get database values as json data
    @GetMapping(value = "/product/findall/deleted", produces = "application/json")
    public List<Product> findAllDeleted() {
        return dao.getDeletedProduct();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/product/findall/exist", produces = "application/json")
    public List<Product> findAllExist() {
        return dao.getExistingProduct();
    }

    // Save a Product with post method
    @PostMapping(value = "/product/save")
    public String save(@RequestBody Product product) {

        try {
            product.setProduct_created_date(LocalDateTime.now());
            product.setProduct_deleted(0);

            String nextProductCode = dao.getNextProductCode();

            if (nextProductCode == "" || nextProductCode == null) {
                product.setProduct_code("prod/001");
            } else {
                product.setProduct_code(nextProductCode);
            }

            for (ProductHasMaterial phm : product.getProduct_has_material_list()) {
                phm.setProduct_id(product);
            }

            dao.save(product);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @DeleteMapping(value = "/product/delete")
    public String delete(@RequestBody Product product) {
        try {
            @SuppressWarnings("null")
            Product extProduct = dao.getReferenceById(product.getProduct_id());
            extProduct.setProduct_deleted(1);
            extProduct.setProduct_deleted_date(LocalDateTime.now());

            for (ProductHasMaterial phm : extProduct.getProduct_has_material_list()) {
                phm.setProduct_id(extProduct);
            }
            dao.save(extProduct);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/product/restore")
    public String restore(@RequestBody Product product) {
        System.out.println(product.getProduct_id());
        try {
            @SuppressWarnings("null")
            Product extProduct = dao.getReferenceById(product.getProduct_id());
            extProduct.setProduct_deleted(0);
            for (ProductHasMaterial phm : extProduct.getProduct_has_material_list()) {
                phm.setProduct_id(extProduct);
            }
            dao.save(extProduct);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @PutMapping(value = "/product/edit")
    public String edit(@RequestBody Product product) {

        try {
            System.out.println((product));
            product.setProduct_updated_date(LocalDateTime.now());
            for (ProductHasMaterial phm : product.getProduct_has_material_list()) {
                phm.setProduct_id(product);
            }

            dao.save(product);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed" + e.getMessage();
        }
    }
}
