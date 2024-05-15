package com.sampathproducts.ProductType;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProductTypeController {

    @Autowired
    private ProductTypeDao dao;

    /*
     * public ProductTypeController(ProductTypeDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/producttypes")
    public ModelAndView producttypeUI() {
        ModelAndView viewProductType = new ModelAndView();
        viewProductType.setViewName("ProductType/ProductType.html");
        return viewProductType;
    }

    @RequestMapping(value = "/producttypedeleted")
    public ModelAndView producttypeDeletedUI() {
        ModelAndView viewProductTypeAdd = new ModelAndView();
        viewProductTypeAdd.setViewName("ProductType/ProductTypeDeleted.html");
        return viewProductTypeAdd;
    }

    @RequestMapping(value = "/producttypeAdd")
    public ModelAndView producttypeAddUI() {
        ModelAndView viewProductTypeAdd = new ModelAndView();
        viewProductTypeAdd.setViewName("ProductType/ProductTypeAdd.html");
        return viewProductTypeAdd;
    }

    // get database values as json data
    @GetMapping(value = "/producttypes/findall", produces = "application/json")
    public List<ProductType> findAll() {
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/producttypes/findall/deleted", produces = "application/json")
    public List<ProductType> findAllDeleted() {
        return dao.getDeletedProductType();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/producttypes/findall/exist", produces = "application/json")
    public List<ProductType> findAllExist() {
        return dao.getExistingProductType();
    }

    // Save a Material with post method
    @PostMapping(value = "/producttype/save")
    public String save(@RequestBody ProductType producttype) {

        try {
            producttype.setProducttype_added_date(LocalDateTime.now());

            String nextProductTypeCode = dao.getNextProductTypeCode();

            if (nextProductTypeCode == "" || nextProductTypeCode == null) {
                producttype.setProducttype_code("ptype/001");
            } else {
                producttype.setProducttype_code(nextProductTypeCode);
            }
            producttype.setProducttype_added_date(LocalDateTime.now());
            producttype.setProducttype_deleted(0);

            dao.save(producttype);
            System.out.println(producttype);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/producttype/restore")
    public String restore(@RequestBody ProductType producttype) {
        System.out.println(producttype.getProducttype_id());
        try {
            // @SuppressWarnings("null")
            ProductType extProductType = dao.getReferenceById(producttype.getProducttype_id());
            extProductType.setProducttype_deleted(0);
            dao.save(extProductType);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @DeleteMapping(value = "/producttype/delete")
    public String delete(@RequestBody ProductType producttype) {
        try {
            @SuppressWarnings("null")
            ProductType extProductType = dao.getReferenceById(producttype.getProducttype_id());
            extProductType.setProducttype_deleted(1);
            extProductType.setProducttype_deleted_date(LocalDateTime.now());
            dao.save(extProductType);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @SuppressWarnings("null")
    @PutMapping("/producttype/edit")
    public String updateProductType(@RequestBody ProductType producttype) {

        try {
            ProductType extProductType = dao.getReferenceById(producttype.getProducttype_id());
            extProductType = producttype;
            extProductType.setProducttype_updated_date(LocalDateTime.now());
            dao.save(producttype);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }

}
