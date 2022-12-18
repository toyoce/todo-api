package com.example.todoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todoapi.TodoApiApplication;
import com.example.todoapi.entity.Todo;
import com.example.todoapi.entity.User;
import com.example.todoapi.repository.TodoRepository;

@SpringBootTest(classes = {TodoApiApplication.class})
public class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void shouldReturnSpecificTodo() throws Exception {
        User user = new User("tanaka", "password");
        Todo todo = new Todo(1, "服を買う", user);
        Optional<Todo> todoOpt = Optional.ofNullable(todo);
        doReturn(todoOpt).when(todoRepository).findById(1);

        Optional<Todo> actual = todoService.findById(1);

        assertEquals(1, actual.get().getId());
        assertEquals("服を買う", actual.get().getTitle());
        assertEquals("tanaka", actual.get().getUser().getUserId());
    }

    @Test
    public void shoudReturnAllTodos() throws Exception {
        User user1 = new User("tanaka", "password");
        User user2 = new User("sasaki", "password");
        Todo todo1 = new Todo(1, "服を買う", user1);
        Todo todo2 = new Todo(11, "部屋を片付ける", user2);
        Todo todo3 = new Todo(21, "ブログを書く", user2);
        List<Todo> todos = List.of(todo1, todo2, todo3);
        doReturn(todos).when(todoRepository).findAll();

        List<Todo> actual = todoService.findAll();

        assertEquals(3, actual.size());
        assertEquals(21, actual.get(2).getId());
        assertEquals("ブログを書く", actual.get(2).getTitle());
        assertEquals("sasaki", actual.get(2).getUser().getUserId());
    }
}
