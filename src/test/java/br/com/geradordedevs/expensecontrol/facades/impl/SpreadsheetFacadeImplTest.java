package br.com.geradordedevs.expensecontrol.facades.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import br.com.geradordedevs.expensecontrol.services.impl.EmailJavaServiceImpl;
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
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SpreadsheetFacadeImplTest {

    @InjectMocks
    private SpreadsheetFacadeImpl spreadsheetFacade;

    @Mock
    private SpreadsheetService spreadsheetService;

    @Mock
    private EmailJavaServiceImpl emailJavaService;

    @Mock
    private SpreadsheetMapper mapper;

    private final Long MOCK_ID= 1L;
    private final String MOCK_MONTH = "Janeiro";
    private final BigDecimal MOCK_PROHIBITED = new BigDecimal(1600);
    private final BigDecimal MOCK_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_TOTAL = new BigDecimal(800);

    private final BigDecimal MOCK_TOTAL_NEGATIVE = new BigDecimal(-800);
    private final double MOCK_TOTAL_CONVECTED_IN_DOUBLE_AND_NEGATIVE = -800;

    @Before
    public void setupMoc(){
        MockitoAnnotations.openMocks(this);
        when(spreadsheetService.findAll()).thenReturn(returnListAllSpreadsheetEntity());


        when(mapper.toDtoList(returnListAllSpreadsheetEntity())).thenReturn(returnListAllSpreadsheetResponseDTO());
    }

    @Test
    public void findAllSpreadsheetMustReturnOk() throws Exception {
        assertEquals(returnListAllSpreadsheetResponseDTO(), spreadsheetFacade.findAll());
    }

    @Test
    public void validNegativeTotalMustReturnOk() throws  Exception{
        spreadsheetFacade.validNegativeTotal(MOCK_TOTAL_NEGATIVE,MOCK_MONTH, MOCK_PROHIBITED, MOCK_OUTPUT);
        verify(emailJavaService,timeout(1)).sendEmail(MOCK_TOTAL_CONVECTED_IN_DOUBLE_AND_NEGATIVE,MOCK_MONTH, MOCK_PROHIBITED, MOCK_OUTPUT);
    }

    private List<SpreadsheetEntity> returnListAllSpreadsheetEntity(){
        List<SpreadsheetEntity> listEntity = new ArrayList<>();
        listEntity.add(returnObjectSpreadsheetEntity());

        return  listEntity;
    }

    private List<SpreadsheetResponseDTO> returnListAllSpreadsheetResponseDTO(){
        List<SpreadsheetResponseDTO> dtoList = new ArrayList<>();
        dtoList.add(returnObjectSpreadsheetResponseDTO());

        return  dtoList;
    }

    private SpreadsheetEntity returnObjectSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_ID,MOCK_MONTH,MOCK_PROHIBITED,MOCK_OUTPUT,MOCK_TOTAL);
    }

    //dtos
    private SpreadsheetResponseDTO returnObjectSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_MONTH,MOCK_PROHIBITED,MOCK_OUTPUT,MOCK_TOTAL);
    }
}
