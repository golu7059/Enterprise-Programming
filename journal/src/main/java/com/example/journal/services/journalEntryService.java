package com.example.journal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.journal.entity.journalEntry;
import com.example.journal.repository.journalEntryRepository;

@Component
public class journalEntryService {
    @Autowired
    private journalEntryRepository repo;

    public void saveEntry(journalEntry entry) {
        repo.save(entry);
    }
}
