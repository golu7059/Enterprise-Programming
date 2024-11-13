package com.example.journal.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection =  "journal_entries")
public class journalEntry {
    private String id;
    private String title;
    private String content;

    @Id
    public String getId() {
        return id;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }    

    public void setContent(String content) {
        this.content = content;
    }   

    @Override
    public String toString() {
        return "journalEntity [id=" + id + ", title=" + title + ", content=" + content + "]";
    }

}
