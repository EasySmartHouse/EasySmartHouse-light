package net.easysmarthouse.controller;

import net.easysmarthouse.shared.domain.user.User;
import net.easysmarthouse.shared.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean(name = "userService")
    private UserService userService;

    private String exampleUser = "{" +
            "\"username\":\"user\"," +
            "\"password\":\"my pass\"," +
            "\"firstname\":\"Mike\"," +
            "\"lastname\":\"Rusakovich\"" +
            "}";

    @Test
    public void registerTest() throws Exception {
        System.out.println("***** register test *****");
        User mockUser = new User("my userName",
                "my password",
                "my firstname",
                "my lastname");

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/register")
                .accept(MediaType.APPLICATION_JSON)
                .content(exampleUser)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}