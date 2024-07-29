package com.sampathproducts.RecieveNote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecieveNoteDao extends JpaRepository<RecieveNote, Integer> {

    // get Deleted values
    @Query(value = "select * from sampathproducts.purchaseorder where purchaseorder_status=0 order by purchaseorder_id desc", nativeQuery = true)
    public List<RecieveNote> getDeletedRecieveNote();

    // get non deleted values
    @Query(value = "select * from sampathproducts.purchaseorder where purchaseorder_status=1 order by purchaseorder_id desc", nativeQuery = true)
    public List<RecieveNote> getExistingRecieveNote();

    @Query(value = "select concat('note/',lpad((substring(max(recieve_note_code),(position('/'in max(recieve_note_code)))+1, 3)+1),3,0)) FROM sampathproducts.recieve_note;", nativeQuery = true)
    public String getNextNoteCode();

}
