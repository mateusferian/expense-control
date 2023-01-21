package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.facades.impl.SpreadsheetFacadeImpl;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
import br.com.geradordedevs.expensecontrol.repositories.SpreadsheetRepository;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SpreadsheetServiceImplTest {

    @InjectMocks
    private SpreadsheetServiceImpl spreadsheetService;

    @Mock
    private SpreadsheetRepository spreadsheetRepository;

    private final String MOCK_JANUARY_MONTH = "Janeiro";
    private final BigDecimal MOCK_JANUARY_PROHIBITED = new BigDecimal(1600);
    private final BigDecimal MOCK_JANUARY_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_JANUARY_TOTAL = new BigDecimal(800);

    @Before
    public void setupMoc() {
        MockitoAnnotations.openMocks(this);
        when(spreadsheetRepository.findAll()).thenReturn(returnListAllSpreadsheetEntity());

    }

    @Test
    public void findAllOfficeMustReturnOk() throws Exception {
        assertEquals(returnListAllSpreadsheetEntity(), spreadsheetService.findAll());
    }

    private List<SpreadsheetEntity> returnListAllSpreadsheetEntity() {

        List<SpreadsheetEntity> listEntity = new ArrayList<>();
        listEntity.add(returnObjectJanuarySpreadsheetEntity());

        return listEntity;
    }

    private SpreadsheetEntity returnObjectJanuarySpreadsheetEntity() {
        return new SpreadsheetEntity(MOCK_JANUARY_MONTH, MOCK_JANUARY_PROHIBITED, MOCK_JANUARY_OUTPUT, MOCK_JANUARY_TOTAL);
    }
}
