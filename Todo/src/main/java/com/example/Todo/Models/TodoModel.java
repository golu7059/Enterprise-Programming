package com.example.Todo.Models;
public class TodoModel {
    private int id;
    private String content;

    public TodoModel(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public TodoModel() {

    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
