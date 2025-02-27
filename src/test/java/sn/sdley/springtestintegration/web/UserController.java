package sn.sdley.springtestintegration.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import sn.sdley.springtestintegration.model.User;
import sn.sdley.springtestintegration.service.implementation.UserServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserServiceImpl service;

    User user;
    List<User> users;

    @BeforeEach
    void setUp() {
        user = new User(1L, "bass", "ngom", 69, true);
        users = List.of(
                new User(1L, "bass", "ngom", 69, true),
                new User(2L, "Fatou", "Diop", 9, true),
                new User(3L, "Alioune", "Fall", 6, true)
        );
    }


    @Test
    void shouldReturnOneUserViaController() throws Exception {
        when(service.getUserById(1L)).thenReturn(Optional.ofNullable(user));

        mockMvc.perform(get("/test/integrations/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("ngom"));
    }


    @Test
    void shouldReturnAllUsers() throws Exception {
        when(service.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/test/integrations/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(users.size()))
                .andExpect(jsonPath("$[0].nom").value("ngom"))
                .andExpect(jsonPath("$[1].nom").value("Diop"))
                .andExpect(jsonPath("$[2].nom").value("Fall"));
    }

    @Test
    void shouldCreateUser() throws Exception {
        doNothing().when(service).createUser(any(User.class));

        mockMvc.perform(post("/test/integrations/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"nom\":\"ngom\", \"prenom\":\"bass\", \"age\":69, \"actif\":true}"))
                .andExpect(status().isOk());

        verify(service, times(1)).createUser(any(User.class));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        doNothing().when(service).updateUser(any(User.class));

        mockMvc.perform(put("/test/integrations/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"nom\":\"ngom\", \"prenom\":\"bass\", \"age\":69, \"actif\":true}"))
                .andExpect(status().isOk());

        verify(service, times(1)).updateUser(any(User.class));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        //doNothing().when(service).deleteUser(1L);

        mockMvc.perform(delete("/test/integrations/v1/users/1"))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteUser(1L);
    }
}
