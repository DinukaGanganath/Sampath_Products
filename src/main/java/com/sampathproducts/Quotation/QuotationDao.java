package com.sampathproducts.Quotation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuotationDao extends JpaRepository<Quotation, Integer> {

    // creating the code
    @Query(value = "select concat('quot/',lpad((substring(max(quotation_code),(position('/'in max(quotation_code)))+1, 3)+1),3,0)) FROM sampathproducts.quotation_details;", nativeQuery = true)
    public String getNextQuotationCode();

    // get Requested Quotation
    @Query(value = "select * from sampathproducts.quotation_details order by quotation_id desc", nativeQuery = true)
    public List<Quotation> getAllQuotation();

    // get Requested Quotation
    @Query(value = "select * from sampathproducts.quotation_details where quotation_deleted=1 order by quotation_id desc", nativeQuery = true)
    public List<Quotation> getRequestedQuotation();

    // get Valid Quotation
    @Query(value = "select * from sampathproducts.quotation_details where quotation_deleted=1 order by quotation_id desc", nativeQuery = true)
    public List<Quotation> getValidQuotation();

    // get Expired Quotation
    @Query(value = "select * from sampathproducts.quotation_details where quotation_deleted=1 order by quotation_id desc", nativeQuery = true)
    public List<Quotation> getExpiredQuotation();

    // get Ending Quotation
    @Query(value = "select * from sampathproducts.quotation_details where quotation_deleted=1 order by quotation_id desc", nativeQuery = true)
    public List<Quotation> getEndingQuotation();

}
