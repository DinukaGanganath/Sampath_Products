package com.sampathproducts.RecieveNote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class RecieveNoteController {

    @Autowired
    private RecieveNoteDao dao;

    @RequestMapping(value = "/recievenote")
    public ModelAndView recievenoteUI() {
        ModelAndView viewRecieveNote = new ModelAndView();
        viewRecieveNote.setViewName("RecieveNote/RecieveNote.html");
        return viewRecieveNote;
    }

    @RequestMapping(value = "/recievenoteadd")
    public ModelAndView recievenoteAddUI() {
        ModelAndView viewRecieveNoteAdd = new ModelAndView();
        viewRecieveNoteAdd.setViewName("RecieveNote/RecieveNoteAdd.html");
        return viewRecieveNoteAdd;
    }

    @RequestMapping(value = "/recievenotedeleted")
    public ModelAndView recievenoteDeletedItems() {
        ModelAndView viewRecieveNoteAdd = new ModelAndView();
        viewRecieveNoteAdd.setViewName("RecieveNote/RecieveNoteDeleted.html");
        return viewRecieveNoteAdd;
    }

    @RequestMapping(value = "/recievenoteedit")
    public ModelAndView recievenoteEditUI() {
        ModelAndView viewRecieveNoteEdit = new ModelAndView();
        viewRecieveNoteEdit.setViewName("RecieveNote/RecieveNoteEdit.html");
        return viewRecieveNoteEdit;
    }

    @RequestMapping(value = "/recievenoteview")
    public ModelAndView recievenoteViewUI() {
        ModelAndView viewRecieveNoteView = new ModelAndView();
        viewRecieveNoteView.setViewName("RecieveNote/RecieveNoteView.html");
        return viewRecieveNoteView;
    }

    // get database values as json data
    @GetMapping(value = "/recievenote/findall", produces = "application/json")
    public List<RecieveNote> getAllData() {
        return dao.findAll();
    }

    // Save a RecieveNote with post method
    @PostMapping(value = "/recievenote/save")
    public String save(@RequestBody RecieveNote recievenote) {

        try {
            String nextProductCode = dao.getNextNoteCode();

            if (nextProductCode == "" || nextProductCode == null) {
                recievenote.setRecieve_note_code("note/001");
            } else {
                recievenote.setRecieve_note_code(nextProductCode);
            }
            dao.save(recievenote);
            return "Ok";
        } catch (Exception e) {
            return "Save not completed" + e.getMessage();
        }

    }

}