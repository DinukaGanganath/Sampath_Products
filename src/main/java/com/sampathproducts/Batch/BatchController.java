package com.sampathproducts.Batch;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
        viewBatch.setViewName("Batch/Batch.html");
        return viewBatch;
    }

    @RequestMapping(value = "/batchadd")
    public ModelAndView batchAddUI() {
        ModelAndView viewBatchAdd = new ModelAndView();
        viewBatchAdd.setViewName("Batch/BatchAdd.html");
        return viewBatchAdd;
    }

    @RequestMapping(value = "/batchdeleted")
    public ModelAndView batchDeletedItems() {
        ModelAndView viewBatchAdd = new ModelAndView();
        viewBatchAdd.setViewName("Batch/BatchDeleted.html");
        return viewBatchAdd;
    }

    @RequestMapping(value = "/batchedit")
    public ModelAndView batchEditUI() {
        ModelAndView viewBatchEdit = new ModelAndView();
        viewBatchEdit.setViewName("Batch/BatchEdit.html");
        return viewBatchEdit;
    }

    @RequestMapping(value = "/batchview")
    public ModelAndView batchViewUI() {
        ModelAndView viewBatchView = new ModelAndView();
        viewBatchView.setViewName("Batch/BatchView.html");
        return viewBatchView;
    }

    // get database values as json data
    @GetMapping(value = "/batch/findall", produces = "application/json")
    public List<Batch> getAllData() {
        return dao.findAll();
    }

    // get database values as json data
    @GetMapping(value = "/batch/findall/deleted", produces = "application/json")
    public List<Batch> findAllDeleted() {
        return dao.getDeletedBatch();
    }

    // get database exsisting values as json data
    @GetMapping(value = "/batch/findall/exist", produces = "application/json")
    public List<Batch> findAllExist() {
        return dao.getExistingBatch();
    }

    // Save a Batch with post method
    @PostMapping(value = "/batch/save")
    public String save(@RequestBody Batch batch) {

        try {

            for (BatchHasProduct phq : batch.getBatchHasProductList()) {
                Product p = phq.getProduct_id();
                p.setProduct_id(p.getProduct_id());

                for (ProductHasMaterial phm : p.getProduct_has_material_list()) {
                    Material mat = phm.getMaterial_id();
                    phm.setProduct_id(p);
                    mat.setMaterial_id(mat.getMaterial_id());
                    mat.setMaterial_has(mat.getMaterial_has() - (phq.getQty() * phm.getQuantity_needed()));
                    matdao.save(mat);
                }

                phq.setBatch_id(batch);
                p.setProduct_progress(phq.getQty());
                proddao.save(p);
            }

            batch.setBatch_created_date(LocalDateTime.now());
            batch.setBatch_status(1);

            String nextBatchCode = dao.getNextBatchCode();

            if (nextBatchCode == "" || nextBatchCode == null) {
                batch.setBatch_code("cust/001");
            } else {
                batch.setBatch_code(nextBatchCode);
            }

            dao.save(batch);
            /*
             * List<BatchHasProduct> list = batch.getBatchHasProductList();
             * 
             * for (BatchHasProduct phq : list) {
             * Request request = phq.getRequest_id();
             * Supplier supplier = request.getSupplier_id();
             * String business = supplier.getSupplier_business_name();
             * String material_name = supplier.getSupplier_material_id().getMaterial_name();
             * String material_unit = supplier.getSupplier_material_id().getMaterial_unit();
             * Integer req_qty = phq.getRequested_qty();
             * LocalDateTime req_date = request.getRequest_created_date();
             * Integer req_unit = request.getRequest_price() / request.getRequest_units();
             * String email = supplier.getSupplier_email();
             * 
             * EmailDetails emailDetails = new EmailDetails();
             * emailDetails.setSendTo(email);
             * emailDetails.setMsgBody("To " + business.replace('_', ' ')
             * + ", \n\n\t According to the Quotation you have sent us on " + req_date
             * + ", we are pleased to purchase " + req_qty + material_unit + "s of " +
             * material_name
             * + " at your unit price, Rs. " + req_unit
             * +
             * ". \n\n\t Full payment for the Products will be paid on the purchasing date. \n\n\tYour sincerly,\nSampath Products."
             * );
             * emailDetails.setSubject("Batch order for buying " + material_name);
             * 
             * emailServiceImpl.sendSimpleMail(emailDetails);
             * 
             * }
             */
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

}
