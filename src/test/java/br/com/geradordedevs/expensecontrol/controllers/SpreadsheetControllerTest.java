package br.com.geradordedevs.expensecontrol.controllers;

import br.com.geradordedevs.expensecontrol.facades.SpreadsheetFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Test
    public  void findAllSpreadsheetReturnOk() throws Exception{
        mockMvc.perform(get(ROULE_SPREADSHEET))
                .andExpect(status().isOk());
    }
}
