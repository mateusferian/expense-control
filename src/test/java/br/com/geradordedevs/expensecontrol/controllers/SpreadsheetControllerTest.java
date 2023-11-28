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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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

    private final String SPREADSHEET_ROUTE = "/api/v1/expenses-controls";

    private final String SPREADSHEET_UPLOAD_ROUTE = "/api/v1/expenses-controls/upload-spreadsheet-data";

    private final String MOCK_FILE = "file";

    private final String MOCK_ORIGINAL_FILE_NAME= "test.xlsx";

    private final String MOCK_CONTENT_TYPE = "text/plain";

    private final String MOCK_SHEET = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Test
    public void findAllSpreadsheetReturnOk() throws Exception{
        mockMvc.perform(get(SPREADSHEET_ROUTE))
                .andExpect(status().isOk());
    }

    @Test
    public void uploadCustomersDataReturnCreated() throws Exception {
        MockMultipartFile mmf = new MockMultipartFile(
                MOCK_FILE,MOCK_ORIGINAL_FILE_NAME,MOCK_CONTENT_TYPE,
                MOCK_SHEET.getBytes());

        mockMvc.perform(multipart(SPREADSHEET_UPLOAD_ROUTE).file(mmf)).andExpect(status().isCreated());
    }
}
