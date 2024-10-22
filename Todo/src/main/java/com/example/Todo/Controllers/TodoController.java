package com.example.Todo.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.Todo.Models.TodoModel;
import com.example.Todo.Services.TodoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // create todo
    @PostMapping // No need to specify /todos again
    public TodoModel createTodo(@RequestBody TodoModel todo) {
        return todoService.createTodo(todo);
    }

    // get all todos
    @GetMapping
    public List<TodoModel> getAllTodos() {
        return todoService.getAllTodos();
    }

    // get todo by id
    @GetMapping("/{id}")
    public TodoModel getTodoById(@PathVariable int id) {
        return todoService.getTodoById(id);
    }
}

