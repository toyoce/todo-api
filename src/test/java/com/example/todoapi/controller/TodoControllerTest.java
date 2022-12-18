package com.example.todoapi.controller;

import static org.mockito.Mockito.doReturn;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.todoapi.TodoApiApplication;
import com.example.todoapi.entity.Todo;
import com.example.todoapi.entity.User;
import com.example.todoapi.service.TodoService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TodoApiApplication.class})
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    public void shouldReturnSpecificTodoWithStatusOk() throws Exception {
        User user = new User("tanaka", "password");
        Todo todo = new Todo(1, "服を買う", user);
        Optional<Todo> todoOpt = Optional.ofNullable(todo);
        doReturn(todoOpt).when(todoService).findById(1);

        this.mockMvc.perform(get("/todos/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.title").value("服を買う"))
            .andExpect(jsonPath("$.userId").value("tanaka"));
    }

    @Test
    public void shoudReturnAllTodosWithStatusOk() throws Exception {
        User user1 = new User("tanaka", "password");
        User user2 = new User("sasaki", "password");
        Todo todo1 = new Todo(1, "服を買う", user1);
        Todo todo2 = new Todo(11, "部屋を片付ける", user2);
        Todo todo3 = new Todo(21, "ブログを書く", user2);
        List<Todo> todos = List.of(todo1, todo2, todo3);
        doReturn(todos).when(todoService).findAll();

        this.mockMvc.perform(get("/todos"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.length()").value(3))
            .andExpect(jsonPath("$[2].id").value(21))
            .andExpect(jsonPath("$[2].title").value("ブログを書く"))
            .andExpect(jsonPath("$[2].userId").value("sasaki"));
    }
}
