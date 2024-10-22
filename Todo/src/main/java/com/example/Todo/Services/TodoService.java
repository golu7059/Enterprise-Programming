package com.example.Todo.Services;
import org.springframework.stereotype.Service;

import com.example.Todo.Models.TodoModel;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    List<TodoModel> todoList = new ArrayList<TodoModel>();

    public List<TodoModel> getAllTodos() {
        return todoList;
    }

    public TodoModel createTodo(TodoModel todo) {
        todoList.add(todo);
        return todo;
    }

    public TodoModel getTodoById(int id) {
        for (TodoModel todo : todoList) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }
}
