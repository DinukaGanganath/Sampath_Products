package com.sampathproducts.ProductSize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.ModuleRole.ModuleRoleController;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProductSizeController {

    @Autowired
    private ModuleRoleController moduleRoleController;

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductSize.addObject("logusername", auth.getName());
        viewProductSize.setViewName("ProductSize/ProductSize.html");
        return viewProductSize;
    }

    @RequestMapping(value = "/productsizedeleted")
    public ModelAndView productsizeDeletedUI() {
        ModelAndView viewProductSizeAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductSizeAdd.addObject("logusername", auth.getName());
        viewProductSizeAdd.setViewName("ProductSize/ProductSizeDeleted.html");
        return viewProductSizeAdd;
    }

    @RequestMapping(value = "/productsizeAdd")
    public ModelAndView productsizeAddUI() {
        ModelAndView viewProductSizeAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewProductSizeAdd.addObject("logusername", auth.getName());
        viewProductSizeAdd.setViewName("ProductSize/ProductSizeAdd.html");
        return viewProductSizeAdd;
    }

    // get database values as json data
    @GetMapping(value = "/productsizes/findall", produces = "application/json")
    public List<ProductSize> findAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Product_Size");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.findAll();
    }

    // get database deleted values as json data
    @GetMapping(value = "/productsizes/findall/deleted", produces = "application/json")
    public List<ProductSize> findAllDeleted() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Product_Size");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getDeletedProductSize();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/productsizes/findall/exist", produces = "application/json")
    public List<ProductSize> findAllExist() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Product_Size");

        if (!logUserPrivi.get("select")) {
            return null;
        }
        return dao.getExistingProductSize();
    }

    // Save a Material with post method
    @PostMapping(value = "/productsize/save")
    public String save(@RequestBody ProductSize productsize) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Product_Size");

        if (!logUserPrivi.get("insert")) {
            return "Not Completed. No Privilages";
        }

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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Product_Size");

        if (!logUserPrivi.get("update")) {
            return "Not Completed. No Privilages";
        }
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Product_Size");

        if (!logUserPrivi.get("delete")) {
            return "Not Completed. No Privilages";
        }
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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Boolean> logUserPrivi = moduleRoleController.getPrivilageByUserModule(auth.getName(),
                "Product_Size");

        if (!logUserPrivi.get("update")) {
            return "Not Completed. No Privilages";
        }
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
