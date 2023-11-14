package com.example.employeesjson;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonControllerTest {

    private MockMvc mockMvc;

    @Test
    public void testPersonJsonFields() throws Exception {
        // Invoke the controller method directly
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/persons/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);

        assertThat(response.getContentAsString()).contains("\"name\"");
        assertThat(response.getContentAsString()).contains("\"surname\"");
        assertThat(response.getContentAsString()).contains("\"department\"");
        assertThat(response.getContentAsString()).contains("\"id\"");
        assertThat(response.getContentAsString()).contains("\"salary\"");
    }
}
