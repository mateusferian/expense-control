package br.com.geradordedevs.expensecontrol.controllers;

import br.com.geradordedevs.expensecontrol.facades.SpreadsheetFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(SpreadsheetController.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
public class SpreadsheetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpreadsheetFacade spreadsheetFacade;

    private final String ROULE_SPREADSHEET = "/api/v1/expenses-controls";

    private final String ROULE_SPREADSHEETA_UPLOAD = "/api/v1/expenses-controls/upload-spreadsheet-data";

    @Test
    public  void findAllSpreadsheetReturnOk() throws Exception{
        mockMvc.perform(get(ROULE_SPREADSHEET))
                .andExpect(status().isOk());
    }

    @Test
    public void uploadCustomersDataReturnCreated() throws Exception {
        MockMultipartFile mmf = new MockMultipartFile("file", "test.xlsx", "text/plain", "testesasa".getBytes());
        mockMvc.perform(multipart(ROULE_SPREADSHEETA_UPLOAD).file(mmf)).andExpect(status().isCreated());
    }
}
