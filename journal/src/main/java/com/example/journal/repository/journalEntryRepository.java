package com.example.journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.journal.entity.journalEntry;

public interface journalEntryRepository extends MongoRepository<journalEntry, String> {

} 
