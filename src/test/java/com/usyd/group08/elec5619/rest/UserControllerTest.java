package com.usyd.group08.elec5619.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usyd.group08.elec5619.models.User;
import com.usyd.group08.elec5619.repositries.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    MockHttpSession mockSession;

    @MockBean
    private UserRepository userRepository;
    @BeforeEach
    public void init(){
        User user = new User();
        user.setId(10L);
        user.setStatus("active");
        user.setEmail("usertest@qq.com");
        user.setType("owner");
        user.setFirstName("testUserFirstName");
        user.setLastName("testUserLastName");
        user.setPassword("test");

        mockSession = new MockHttpSession();
        mockSession.setAttribute("currentUser", user);
    }
    @Test
    void getCurrentUser() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/users/getCurrentUserInfo").session(mockSession)).andExpect(status().isOk()).andReturn();
        String jsonResponse = result.getResponse().getContentAsString();
        assertTrue(jsonResponse.contains("testUserFirstName") && jsonResponse.contains("testUserLastName"));
    }

    @Test
    void getOrganisers() throws Exception {
        // Given
        User userExample = new User();
        userExample.setType("organiser");

        List<User> organisers = Arrays.asList(
                new User(),
                new User()
        );

        when(userRepository.findAll(any(Example.class))).thenReturn(organisers);

        mockMvc.perform(get("/api/users/organisers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getOwners() throws Exception {
        // Given
        User userExample = new User();
        userExample.setType("owner");

        List<User> owners = Arrays.asList(
                new User(),
                new User()
        );

        when(userRepository.findAll(any(Example.class))).thenReturn(owners);

        mockMvc.perform(get("/api/users/owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }
}