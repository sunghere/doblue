package ga.doblue.port.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ga.doblue.port.PortApplication;
import ga.doblue.port.model.Port;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PortApplication.class)
@WebAppConfiguration
@Transactional
public class PortControllerTest {

    @Autowired
    WebApplicationContext wac;
    MockMvc mvc;
    @Autowired
    PortDao dao;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(this.wac)
                .alwaysDo(print(System.out))
                .build();
    }

    @Test
    public void shouldCreate() throws Exception {
        String requestBody = "{\"todo\":\"할일추가 테스트\"}";

        mvc.perform(
                post("/api/todos/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("SUCS"));
    }

    @Test
    public void shouldUpdate() throws Exception {
        String requestBody = "{\"completed\":\"1\"}";

        mvc.perform(
                put("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDelete() throws Exception {
        mvc.perform(
                delete("/api/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSelectAll() {
        List<Port> list = dao.listAll();
        assertThat(list, is(notNullValue()));
    }


}