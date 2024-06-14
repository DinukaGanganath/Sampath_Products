package com.sampathproducts.QuotationRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuotationRequestDao extends JpaRepository<QuotationRequest, Integer> {
    @Query(value = "select concat('req/',lpad((substring(max(request_code),(position('/'in max(request_code)))+1, 3)+1),3,0)) FROM sampathproducts.request_quotation;", nativeQuery = true)
    public String getNextQuotationRequestCode();

    // get Deleted values
    @Query(value = "select * from sampathproducts.request_quotation where request_created=1 order by request_id desc", nativeQuery = true)
    public List<QuotationRequest> getCreatedQuotationRequest();

    // get non deleted values
    @Query(value = "select * from sampathproducts.request_quotation where request_created=0 order by request_id desc", nativeQuery = true)
    public List<QuotationRequest> getRequestedQuotationRequest();
}
