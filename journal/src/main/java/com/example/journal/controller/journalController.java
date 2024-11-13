package com.example.journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journal.services.journalEntryService;
import com.example.journal.entity.journalEntry;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class journalController {

    @Autowired
    private journalEntryService service;

    @GetMapping
    public String getAll(){
        return "YAA it works";
    }

    @PostMapping
    public void saveEntry(journalEntry newEntry){
        service.saveEntry(newEntry);
    }


}
