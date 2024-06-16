package com.sampathproducts.QuotationRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuotationRequestDao extends JpaRepository<QuotationRequest, Integer> {
    @Query(value = "select concat('req/',lpad((substring(max(request_code),(position('/'in max(request_code)))+1, 3)+1),3,0)) FROM sampathproducts.request_quotation;", nativeQuery = true)
    public String getNextQuotationRequestCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.request_quotation where request_created=1 and request_deleted=0 order by request_id desc", nativeQuery = true)
    public List<QuotationRequest> getCreatedQuotationRequest();

    // get non deleted values
    @Query(value = "select * from sampathproducts.request_quotation where request_created=0 and request_deleted=0 order by request_id desc", nativeQuery = true)
    public List<QuotationRequest> getRequestedQuotationRequest();

    // get expired quotation
    @Query(value = "select * from sampathproducts.request_quotation where (DATE_ADD(request_created_date, Interval request_validity DAY) < CURDATE()) and request_created=1 and request_deleted=0 order by request_id desc;", nativeQuery = true)
    public List<QuotationRequest> getExpiredQuotations();

    // get ending quotations
    @Query(value = "select * from sampathproducts.request_quotation where (CURDATE() between DATE_ADD(request_created_date, INTERVAL (request_validity - 3) DAY) AND DATE_ADD(request_created_date, INTERVAL request_validity DAY)) and request_created=1 and request_deleted=0 order by request_id desc;", nativeQuery = true)
    public List<QuotationRequest> getEndingQuotations();

    // get valid quotations
    @Query(value = "select * from sampathproducts.request_quotation where (DATE_ADD(request_created_date, Interval (request_validity-3) DAY) > CURDATE()) and request_created=1 and request_deleted=0 order by request_id desc;", nativeQuery = true)
    public List<QuotationRequest> getvalidQuotations();
}
