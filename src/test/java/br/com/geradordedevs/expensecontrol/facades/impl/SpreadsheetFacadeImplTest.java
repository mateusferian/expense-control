package br.com.geradordedevs.expensecontrol.facades.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
import br.com.geradordedevs.expensecontrol.services.EmailJavaService;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import br.com.geradordedevs.expensecontrol.services.impl.EmailJavaServiceImpl;
import com.google.common.base.Verify;
import org.apache.commons.mail.EmailException;
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

import javax.mail.Multipart;
import java.io.FileInputStream;
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

    private final Long MOCK_JANUARY_ID= 1L;
    private final String MOCK_JANUARY_MONTH = "Janeiro";
    private final BigDecimal MOCK_JANUARY_PROHIBITED = new BigDecimal(1600);
    private final BigDecimal MOCK_JANUARY_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_JANUARY_TOTAL = new BigDecimal(800);

    private final BigDecimal MOCK_JANUARY_TOTAL_NEGATIVE = new BigDecimal(-800);

    private final double RETURNS_TOTAL_NEGATIVE_CONVERTED = -800;

    @Before
    public  void  setupMoc(){
        MockitoAnnotations.openMocks(this);
        when(spreadsheetService.findAll()).thenReturn(returnListAllSpreadsheetEntity());


        when(mapper.toDtoList(returnListAllSpreadsheetEntity())).thenReturn(returnListAllSpreadsheetResponseDTO());
    }

    @Test
    public void findAllOfficeMustReturnOk() throws Exception {
        assertEquals(returnListAllSpreadsheetResponseDTO(), spreadsheetFacade.findAll());
    }

    @Test
    public void validNegativeTotalMustReturnOk() throws  Exception{
        spreadsheetFacade.validNegativeTotal(MOCK_JANUARY_TOTAL_NEGATIVE,MOCK_JANUARY_MONTH, MOCK_JANUARY_PROHIBITED, MOCK_JANUARY_OUTPUT);
        verify(emailJavaService,timeout(1)).sendEmail(RETURNS_TOTAL_NEGATIVE_CONVERTED,MOCK_JANUARY_MONTH, MOCK_JANUARY_PROHIBITED, MOCK_JANUARY_OUTPUT);
    }

    private List<SpreadsheetEntity> returnListAllSpreadsheetEntity(){
        List<SpreadsheetEntity> listEntity = new ArrayList<>();
        listEntity.add(returnObjectJanuarySpreadsheetEntity());

        return  listEntity;
    }

    private List<SpreadsheetResponseDTO> returnListAllSpreadsheetResponseDTO(){
        List<SpreadsheetResponseDTO> dtoList = new ArrayList<>();
        dtoList.add(returnObjectJanuarySpreadsheetResponseDTO());

        return  dtoList;
    }


    private SpreadsheetEntity returnObjectJanuarySpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_JANUARY_ID,MOCK_JANUARY_MONTH,MOCK_JANUARY_PROHIBITED,MOCK_JANUARY_OUTPUT,MOCK_JANUARY_TOTAL);
    }

    //dtos
    private SpreadsheetResponseDTO returnObjectJanuarySpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_JANUARY_MONTH,MOCK_JANUARY_PROHIBITED,MOCK_JANUARY_OUTPUT,MOCK_JANUARY_TOTAL);
    }

}
