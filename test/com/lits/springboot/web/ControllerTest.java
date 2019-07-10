package com.lits.springboot.web;

import com.lits.springboot.config.MockConfiguration;
import com.lits.springboot.dtos.FootballTeamDto;
import com.lits.springboot.dtos.UserDto;

import com.lits.springboot.entity.FootballTeam;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {MockConfiguration.class})
public class ControllerTest {

    private static final String SIGN_UP_URL = "/api/save";

    private UserDto getUserDto() {
        return new UserDto();
    }


    FootballTeam footballTeam = new FootballTeam();

    @Autowired
    private MockMvc mockMvc;


//    @Test
//    public void shouldSuccessSaveUser() throws Exception {
//        JSONObject user = new JSONObject(getUserDto());
//
//
//        mockMvc.perform(post(SIGN_UP_URL)
//                .accept("application/json")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(user.toString()))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void givenFootballTeamByID_whenGetFootballTeam_thenStatus200() throws Exception {
//
//        mockMvc.perform(get("/api/footballTeam")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content()
//                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect((ResultMatcher) jsonPath("$[0].name", is("bob")));
//
//
//        TestRestTemplate restTemplate = new TestRestTemplate();
//        ResponseEntity<FootballTeam> response = restTemplate.getForEntity("http://localhost:8181/api/footballTeam?id=1", FootballTeam.class);
//
//        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
//
//    }

    @Test
    public void givenFootballTeamWithQueryParameterId_whenMockMVC_thenResponseOK() throws Exception {
        this.mockMvc.perform(get("/api/footballTeam")
                .param("id", "1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("Liverpool"));
    }


}
