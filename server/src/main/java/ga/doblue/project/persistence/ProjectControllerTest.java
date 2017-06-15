package ga.doblue.project.persistence;

import ga.doblue.project.ProjectApplication;
import ga.doblue.project.model.Project;
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

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProjectApplication.class)
@WebAppConfiguration
@Transactional
public class ProjectControllerTest {

    @Autowired
    WebApplicationContext wac;
    MockMvc mvc;

    @Autowired
    ProjectDAO dao;

    @Before
    public void setUp() {
        this.mvc = webAppContextSetup(this.wac)
                .alwaysDo(print(System.out))
                .build();
    }

    @Test
    public void shouldCreate() throws Exception {
        String requestBody = "{\"content\":\"프로젝트 추가 테스트\"}";

        mvc.perform(
                post("/api/projects/")
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
                put("/api/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDelete() throws Exception {
        mvc.perform(
                delete("/api/projects/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSelectAll() throws Exception {
        List<Project> list = dao.listAll();
        assertThat(list, is(notNullValue()));
    }


}