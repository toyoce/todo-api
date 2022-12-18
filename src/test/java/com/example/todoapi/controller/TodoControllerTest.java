package com.example.todoapi.controller;

import static org.mockito.Mockito.doReturn;

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
}
