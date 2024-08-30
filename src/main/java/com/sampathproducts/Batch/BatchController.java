package com.sampathproducts.Batch;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sampathproducts.BatchHasProduct.BatchHasProduct;
import com.sampathproducts.Material.Material;
import com.sampathproducts.Material.MaterialDao;
import com.sampathproducts.Product.Product;
import com.sampathproducts.Product.ProductDao;
import com.sampathproducts.ProductHasMaterial.ProductHasMaterial;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BatchController {

    @Autowired
    private BatchDao dao;

    @Autowired
    private ProductDao proddao;

    @Autowired
    private MaterialDao matdao;

    @RequestMapping(value = "/batch")
    public ModelAndView batchUI() {
        ModelAndView viewBatch = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewBatch.addObject("logusername", auth.getName());
        viewBatch.setViewName("Batch/Batch.html");
        return viewBatch;
    }

    @RequestMapping(value = "/batchprocess")
    public ModelAndView batchAddUI() {
        ModelAndView viewBatchAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewBatchAdd.addObject("logusername", auth.getName());
        viewBatchAdd.setViewName("Batch/BatchProcess.html");
        return viewBatchAdd;
    }

    @RequestMapping(value = "/batchdeleted")
    public ModelAndView batchDeletedItems() {
        ModelAndView viewBatchAdd = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewBatchAdd.addObject("logusername", auth.getName());
        viewBatchAdd.setViewName("Batch/BatchDeleted.html");
        return viewBatchAdd;
    }

    @RequestMapping(value = "/batchedit")
    public ModelAndView batchEditUI() {
        ModelAndView viewBatchEdit = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewBatchEdit.addObject("logusername", auth.getName());
        viewBatchEdit.setViewName("Batch/BatchEdit.html");
        return viewBatchEdit;
    }

    @RequestMapping(value = "/batchview")
    public ModelAndView batchViewUI() {
        ModelAndView viewBatchView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        viewBatchView.addObject("logusername", auth.getName());
        viewBatchView.setViewName("Batch/BatchView.html");
        return viewBatchView;
    }

    // get database values as json data
    @GetMapping(value = "/batch/findall", produces = "application/json")
    public List<Batch> getAllData() {
        return dao.findAll();
    }

    // get database values as json data
    @GetMapping(value = "/batch/findall/finished", produces = "application/json")
    public List<Batch> findAllFinished() {
        return dao.getFinishedBatch();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/batch/findall/process", produces = "application/json")
    public List<Batch> findAllProcess() {
        return dao.getProcessingBatch();
    }

    // Save a Batch with post method
    @PostMapping(value = "/batch/save")
    public String save(@RequestBody Batch batch) {

        try {

            for (BatchHasProduct phq : batch.getBatchHasProductList()) {
                phq.setBatch_id(batch);

                Product p = phq.getProduct_id();
                // p.setProduct_id(p.getProduct_id());

                for (ProductHasMaterial phm : p.getProduct_has_material_list()) {
                    phm.setProduct_id(p);

                    Material mat = phm.getMaterial_id();
                    mat.setMaterial_id(mat.getMaterial_id());
                    mat.setMaterial_has(mat.getMaterial_has() - (phq.getQty() * phm.getQuantity_needed()));
                    matdao.save(mat);
                }

                p.setProduct_progress(p.getProduct_progress() + phq.getQty());

                proddao.save(p);
            }

            batch.setBatch_created_date(LocalDateTime.now());
            batch.setBatch_status(1);

            String nextBatchCode = dao.getNextBatchCode();

            if (nextBatchCode == "" || nextBatchCode == null) {
                batch.setBatch_code("Batch/001");
            } else {
                batch.setBatch_code(nextBatchCode);
            }

            dao.save(batch);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

    @PutMapping(value = "/batch/edit")
    public String edit(@RequestBody Batch batch) {

        try {

            for (BatchHasProduct phq : batch.getBatchHasProductList()) {
                phq.setBatch_id(batch);
                phq.setBatch_product_created(LocalDateTime.now());

                Product p = phq.getProduct_id();

                phq.setBatch_product_expired(LocalDateTime.now().plusMonths(p.getProduct_usable_time()));
                for (ProductHasMaterial phm : p.getProduct_has_material_list()) {
                    phm.setProduct_id(p);
                }

                // int x = ;
                p.setProduct_progress(p.getProduct_progress() - phq.getQty());
                p.setProduct_has(p.getProduct_has() + phq.getQty());

                proddao.save(p);

            }

            // batch.setBatch_created_date(LocalDateTime.now());
            batch.setBatch_status(0);

            dao.save(batch);

            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

}