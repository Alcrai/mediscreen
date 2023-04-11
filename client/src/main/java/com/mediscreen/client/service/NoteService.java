package com.mediscreen.client.service;

import com.mediscreen.client.beans.NoteBean;
import com.mediscreen.client.proxies.NoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteProxy noteProxy;

    @Autowired
    public NoteService(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }

    public List<NoteBean> getHistoryOfPatient(String idPatient){
        return noteProxy.getHistory(idPatient);
    }
    public NoteBean getNoteById(String idNote){
        return noteProxy.getNoteById(idNote);
    }

    public NoteBean addNote(NoteBean noteBean){
        return noteProxy.addNote(noteBean);
    }

    public NoteBean updateNote(NoteBean noteBean){
        return noteProxy.updateNote(noteBean);
    }

    public NoteBean deleteNoteById(String id){
        return noteProxy.deleteNoteById(id);
    }


}
