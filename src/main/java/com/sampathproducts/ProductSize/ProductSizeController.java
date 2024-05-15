package com.sampathproducts.ProductSize;

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
public class ProductSizeController {

    @Autowired
    private ProductSizeDao dao;

    /*
     * public ProductSizeController(ProductSizeDao dao) {
     * this.dao = dao;
     * }
     */

    @RequestMapping(value = "/productsizes")
    public ModelAndView productsizeUI() {
        ModelAndView viewProductSize = new ModelAndView();
        viewProductSize.setViewName("ProductSize/ProductSize.html");
        return viewProductSize;
    }

    @RequestMapping(value = "/productsizedeleted")
    public ModelAndView productsizeDeletedUI() {
        ModelAndView viewProductSizeAdd = new ModelAndView();
        viewProductSizeAdd.setViewName("ProductSize/ProductSizeDeleted.html");
        return viewProductSizeAdd;
    }

    @RequestMapping(value = "/productsizeAdd")
    public ModelAndView productsizeAddUI() {
        ModelAndView viewProductSizeAdd = new ModelAndView();
        viewProductSizeAdd.setViewName("ProductSize/ProductSizeAdd.html");
        return viewProductSizeAdd;
    }

    // get database values as json data
    @GetMapping(value = "/productsizes/findall", produces = "application/json")
    public List<ProductSize> findAll() {
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/productsizes/findall/deleted", produces = "application/json")
    public List<ProductSize> findAllDeleted() {
        return dao.getDeletedProductSize();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/productsizes/findall/exist", produces = "application/json")
    public List<ProductSize> findAllExist() {
        return dao.getExistingProductSize();
    }

    // Save a Material with post method
    @PostMapping(value = "/productsize/save")
    public String save(@RequestBody ProductSize productsize) {

        try {
            productsize.setProductsize_added_date(LocalDateTime.now());

            String nextProductSizeCode = dao.getNextProductSizeCode();

            if (nextProductSizeCode == "" || nextProductSizeCode == null) {
                productsize.setProductsize_code("ptype/001");
            } else {
                productsize.setProductsize_code(nextProductSizeCode);
            }
            productsize.setProductsize_added_date(LocalDateTime.now());
            productsize.setProductsize_deleted(0);

            dao.save(productsize);
            System.out.println(productsize);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/productsize/restore")
    public String restore(@RequestBody ProductSize productsize) {
        System.out.println(productsize.getProductsize_id());
        try {
            // @SuppressWarnings("null")
            ProductSize extProductSize = dao.getReferenceById(productsize.getProductsize_id());
            extProductSize.setProductsize_deleted(0);
            dao.save(extProductSize);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @DeleteMapping(value = "/productsize/delete")
    public String delete(@RequestBody ProductSize productsize) {
        try {
            @SuppressWarnings("null")
            ProductSize extProductSize = dao.getReferenceById(productsize.getProductsize_id());
            extProductSize.setProductsize_deleted(1);
            extProductSize.setProductsize_deleted_date(LocalDateTime.now());
            dao.save(extProductSize);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }
    }

    @SuppressWarnings("null")
    @PutMapping("/productsize/edit")
    public String updateProductSize(@RequestBody ProductSize productsize) {

        try {
            ProductSize extProductSize = dao.getReferenceById(productsize.getProductsize_id());
            extProductSize = productsize;
            extProductSize.setProductsize_updated_date(LocalDateTime.now());
            dao.save(productsize);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }

}
