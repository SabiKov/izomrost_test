package hu.sabikov.idomsoft.assignment.microservice1.controller;

import hu.sabikov.idomsoft.assignment.microservice1.service.DataErrorService;
import hu.sabikov.idomsoft.assignment.microservice1.service.RequestProcessStep;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.properties")
class IdCardControllerTest {

    private MockMvc mockMvc;

    @Mock
    DataErrorService dataErrorService;

    @Mock
    RequestProcessStep requestProcessStep;

    @InjectMocks
    private IdCardController idCardController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(idCardController)
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void processRequestedDataPost() throws Exception {
    //    SzemelyDTO szemelyDTO = new SzemelyDTO();
    //    when(requestProcessStep.processWorkFlow(szemelyDTO));

        String json = "{\n" +
                "    \"visNev\": \"Kovacs Szabolcs\",\n" +
                "    \"szulNev\": \"Kovacs Szabolcs\",\n" +
                "    \"anyjaNeve\": \"Kavacs Szabi\",\n" +
                "    \"szulDat\": \"1978-03-11\",\n" +
                "    \"neme\": \"F\",\n" +
                "    \"allampKod\": \"KIR\",\n" +
                "    \"allampDekod\": \"\",\n" +
                "    \"okmLista\": [\n" +
                "        {\"okmTipus\": \"1\"},\n" +
                "        {\"okmanySzam\": \"1\"},\n" +
                "        {\"okmanyKep\": [12, 4, 4]},\n" +
                "        {\"lejarDat\": \"2020-03-11\"},\n" +
                "        {\"ervenyes\": true}\n" +
                "    ]\n" +
                "\n" +
                "}";

        mockMvc.perform(post("/mcrsrv1/szemelydto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        //        .andExpect(jsonPath()));
    }
}
