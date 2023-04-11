package com.mediscreen.client.proxies;

import com.mediscreen.client.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "history", url = "http://mediscreen-history:8092")
//@FeignClient(name = "history", url = "http://localhost:8092")
public interface NoteProxy {
    @GetMapping("/history/{idPatient}")
    public List<NoteBean> getHistory(@PathVariable("idPatient") String idPatient);
    @GetMapping("history/note/{idNote}")
    public NoteBean getNoteById(@PathVariable("idNote") String idNote);
    @PostMapping("/history/add")
    public NoteBean addNote(@RequestBody NoteBean noteBean);
    @PutMapping("/history/update")
    public NoteBean updateNote(@RequestBody NoteBean noteBean);
    @DeleteMapping("/history/delete/{id}")
    public NoteBean deleteNoteById(@PathVariable("id")String id);
}
