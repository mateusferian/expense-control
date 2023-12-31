package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.repositories.SpreadsheetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SpreadsheetServiceImplTest {

    @InjectMocks
    private SpreadsheetServiceImpl spreadsheetService;

    @Mock
    private SpreadsheetRepository spreadsheetRepository;

    private final String MOCK_MONTH = "Janeiro";
    private final BigDecimal MOCK_PROHIBITED = new BigDecimal(1600);
    private final BigDecimal MOCK_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_TOTAL = new BigDecimal(800);

    private final String MOCK_FILE = "file";

    private final String MOCK_ORIGINAL_FILE_NAME= "desafio2.xlsx";

    private final String MOCK_NAME = "src/test/java/resources/desafio2.xlsx";

    private final String MOCK_SHEET = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @Before
    public void setupMoc() {
        MockitoAnnotations.openMocks(this);
        when(spreadsheetRepository.findAll()).thenReturn(returnListSpreadsheetEntity());
        when(spreadsheetRepository.save(returnObjectSpreadsheetEntity())).thenReturn(returnObjectSpreadsheetEntity());
    }

    @Test
    public void findAllSpreadsheetMustReturnOk() throws Exception {
        assertEquals(returnListSpreadsheetEntity(), spreadsheetService.findAll());
    }

    @Test
    public void saveExcelUploadToDataBaseMustReturnOk() throws Exception{
        assertEquals(returnObjectSpreadsheetEntity(),spreadsheetService.saveExcelUploadToDataBase(returnObjectSpreadsheetEntity()));
    }

    @Test
    public void isValidExcelFileMustReturnOk() throws Exception{
        assertTrue(spreadsheetService.isValidExcelFile(returnIsValidExcelFile()));
    }

    private List<SpreadsheetEntity> returnListSpreadsheetEntity() {

        List<SpreadsheetEntity> listEntity = new ArrayList<>();
        listEntity.add(returnObjectSpreadsheetEntity());

        return listEntity;
    }

    private SpreadsheetEntity returnObjectSpreadsheetEntity() {
        return new SpreadsheetEntity(MOCK_MONTH, MOCK_PROHIBITED, MOCK_OUTPUT, MOCK_TOTAL);
    }

    private MultipartFile returnIsValidExcelFile () throws IOException {
        FileInputStream file = new FileInputStream(MOCK_NAME);

        return new MockMultipartFile(
                MOCK_FILE,
                MOCK_ORIGINAL_FILE_NAME,
                MOCK_SHEET,
                file);
    }
}
