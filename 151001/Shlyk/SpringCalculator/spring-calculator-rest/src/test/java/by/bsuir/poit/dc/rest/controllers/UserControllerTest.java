package by.bsuir.poit.dc.rest.controllers;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Paval Shlyk
 * @since 24/02/2024
 */
public class UserControllerTest extends AbstractControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    @Order(0)
    public void JsonTest() throws Exception {
	mockMvc.perform(get("/api/v1.0/users/1"))
	    .andExpectAll(
		status().is2xxSuccessful(),
		content().json("""
		    {
			"id": 1,
			"login": "m_rgan",
			"firstname": "Morgan",
			"lastname": "Paulsen"
		    }
		    """)
	    );
    }

    @Test
    @Order(1)
    public void findAllUsers_ReturnUserList() throws Exception {
	mockMvc.perform(delete("/api/v1.0/users/1"))
	    .andExpect(
		status().is(204)
	    );
    }
}