package com.example.todoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

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
        Todo todo = new Todo("服を買う");
        todo.setId(1);
        todo.setUser(user);
        Optional<Todo> todoOpt = Optional.ofNullable(todo);
        doReturn(todoOpt).when(todoRepository).findById(1);

        Optional<Todo> actual = todoService.findById(1);

        assertEquals("服を買う", actual.get().getTitle());
        assertEquals(1, actual.get().getId());
        assertEquals("tanaka", actual.get().getUser().getUserId());
    }
}
