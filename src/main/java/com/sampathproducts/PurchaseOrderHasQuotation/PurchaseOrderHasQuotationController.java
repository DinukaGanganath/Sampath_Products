package com.sampathproducts.PurchaseOrderHasQuotation;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.ModuleRole.ModuleRoleController;

@RestController
public class PurchaseOrderHasQuotationController {
    private PurchaseOrderHasQuotationDao dao;

    @Autowired
    private ModuleRoleController moduleRoleController;

    public PurchaseOrderHasQuotationController(PurchaseOrderHasQuotationDao dao) {
        this.dao = dao;
    }

    @GetMapping(value = "/purchaseorderLine/findall", produces = "application/json")
    public List<PurchaseOrderHasQuotation> findAll() {
        return dao.getPurchaseOrdered();
    }

    @RequestMapping(value = "/purchaseorderLine")
    public ModelAndView purchaseorderUI() {
        ModelAndView viewPurchaseOrder = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewPurchaseOrder.addObject("logusername", auth.getName());
        viewPurchaseOrder.setViewName("Material/MaterialOrdered.html");
        return viewPurchaseOrder;
    }

    @PutMapping("/purchaseorderLine/edit")
    public String updateArea(@RequestBody PurchaseOrderHasQuotation pohq) {

        try {
            PurchaseOrderHasQuotation extPohq = dao.getReferenceById(pohq.getOrder_line_id());
            pohq.setPurchase_order_id(extPohq.getPurchase_order_id());

            dao.save(pohq);
            return "Ok";
        } catch (Exception e) {
            return "Update not completed : " + e.getMessage();
        }
    }
}
