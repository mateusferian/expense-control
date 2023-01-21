package br.com.geradordedevs.expensecontrol.facades.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SpreadsheetFacadeImplTest {

    @InjectMocks
    private SpreadsheetFacadeImpl spreadsheetFacade;

    @Mock
    private SpreadsheetService spreadsheetService;

    @Mock
    private SpreadsheetMapper mapper;

    private final Long MOCK_JANUARY_ID= 1L;

    private final String MOCK_JANUARY_MONTH = "Janeiro";
    private final BigDecimal MOCK_JANUARY_PROHIBITED = new BigDecimal(1600);
    private final BigDecimal MOCK_JANUARY_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_JANUARY_TOTAL = new BigDecimal(800);

    private final Long MOCK_FEBRUARY_ID= 2L;
    private final String MOCK_FEBRUARY_MONTH = "Fevereiro";
    private final BigDecimal MOCK_FEBRUARY_PROHIBITED = new BigDecimal(2500);
    private final BigDecimal MOCK_FEBRUARY_OUTPUT = new BigDecimal(600);
    private final BigDecimal MOCK_FEBRUARY_TOTAL = new BigDecimal(1900);

    private final Long MOCK_MARCH_ID= 3L;
    private final String MOCK_MARCH_MONTH = "Março";
    private final BigDecimal MOCK_MARCH_PROHIBITED = new BigDecimal(66);
    private final BigDecimal MOCK_MARCH_OUTPUT = new BigDecimal(20);
    private final BigDecimal MOCK_MARCH_TOTAL = new BigDecimal(46);


    private final Long MOCK_APRIL_ID= 4L;
    private final String MOCK_APRIL_MONTH = "Abril";
    private final BigDecimal MOCK_APRIL_PROHIBITED = new BigDecimal(66);
    private final BigDecimal MOCK_APRIL_OUTPUT = new BigDecimal(20);
    private final BigDecimal MOCK_APRIL_TOTAL = new BigDecimal(49);


    private final Long MOCK_MAY_ID= 5L;
    private final String MOCK_MAY_MONTH = "Maio";
    private final BigDecimal MOCK_MAY_PROHIBITED = new BigDecimal(287);
    private final BigDecimal MOCK_MAY_OUTPUT = new BigDecimal(20);
    private final BigDecimal MOCK_MAY_TOTAL = new BigDecimal(267);

    private final Long MOCK_JUNE_ID= 6L;
    private final String MOCK_JUNE_MONTH = "Junho";
    private final BigDecimal MOCK_JUNE_PROHIBITED = new BigDecimal(582);
    private final BigDecimal MOCK_JUNE_OUTPUT = new BigDecimal(20);
    private final BigDecimal MOCK_JUNE_TOTAL = new BigDecimal(562);

    private final Long MOCK_JULY_ID= 7L;
    private final String MOCK_JULY_MONTH = "Julho";
    private final BigDecimal MOCK_JULY_PROHIBITED = new BigDecimal(2);
    private final BigDecimal MOCK_JULY_OUTPUT = new BigDecimal(20);
    private final BigDecimal MOCK_JULY_TOTAL = new BigDecimal(-18);

    private final Long MOCK_AUGUST_ID= 8L;
    private final String MOCK_AUGUST_MONTH = "Agosto";
    private final BigDecimal MOCK_AUGUST_PROHIBITED = new BigDecimal(2.787);
    private final BigDecimal MOCK_AUGUST_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_AUGUST_TOTAL = new BigDecimal(1.987);

    private final Long MOCK_OCTOBER_ID= 9L;
    private final String MOCK_OCTOBER_MONTH = "Setembro";
    private final BigDecimal MOCK_OCTOBER_PROHIBITED = new BigDecimal(857.867);
    private final BigDecimal MOCK_OCTOBER_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_OCTOBER_TOTAL = new BigDecimal(857.067);

    private final Long MOCK_SEPTEMBER_ID= 10L;
    private final String MOCK_SEPTEMBER_MONTH = "Outubro";
    private final BigDecimal MOCK_SEPTEMBER_PROHIBITED = new BigDecimal(287.287);
    private final BigDecimal MOCK_SEPTEMBER_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_SEPTEMBER_TOTAL = new BigDecimal(286.487);

    private final Long MOCK_NOVEMBER_ID= 11L;
    private final String MOCK_NOVEMBER_MONTH = "Novembro";
    private final BigDecimal MOCK_NOVEMBER_PROHIBITED = new BigDecimal(66);
    private final BigDecimal MOCK_NOVEMBER_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_NOVEMBER_TOTAL = new BigDecimal(-734);

    private final Long MOCK_DECEMBER_ID= 12L;
    private final String MOCK_DECEMBER_MONTH = "Dezembro";
    private final BigDecimal MOCK_DECEMBER_PROHIBITED = new BigDecimal(2.477);
    private final BigDecimal MOCK_DECEMBER_OUTPUT = new BigDecimal(800);
    private final BigDecimal MOCK_DECEMBER_TOTAL = new BigDecimal(1.677);

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

    private List<SpreadsheetEntity> returnListAllSpreadsheetEntity(){
        List<SpreadsheetEntity> listEntity = new ArrayList<>();

        listEntity.add(returnObjectJanuarySpreadsheetEntity());
            listEntity.add(returnObjectFebruarySpreadsheetEntity());
                listEntity.add(returnObjectMarchSpreadsheetEntity());
                    listEntity.add(returnObjectAprilSpreadsheetEntity());
                        listEntity.add(returnObjectMaySpreadsheetEntity());
                            listEntity.add(returnObjectJuneSpreadsheetEntity());

                            listEntity.add(returnObjectJulySpreadsheetEntity());
                        listEntity.add(returnObjectAugustSpreadsheetEntity());
                    listEntity.add(returnObjectSeptemberSpreadsheetEntity());
                listEntity.add(returnObjectOctoberSpreadsheetEntity());
            listEntity.add(returnObjectNovemberSpreadsheetEntity());
        listEntity.add(returnObjectDecemberSpreadsheetEntity());

        return  listEntity;
    }

    private List<SpreadsheetResponseDTO> returnListAllSpreadsheetResponseDTO(){
        List<SpreadsheetResponseDTO> dtoList = new ArrayList<>();

        dtoList.add(returnObjectJanuarySpreadsheetResponseDTO());
            dtoList.add(returnObjectFebruarySpreadsheetResponseDTO());
                dtoList.add(returnObjectMarchSpreadsheetResponseDTO());
                    dtoList.add(returnObjectAprilSpreadsheetResponseDTO());
                        dtoList.add(returnObjectMaySpreadsheetResponseDTO());
                            dtoList.add(returnObjectJuneSpreadsheetResponseDTO());

                            dtoList.add(returnObjectJulySpreadsheetResponseDTO());
                        dtoList.add(returnObjectAugustSpreadsheetResponseDTO());
                    dtoList.add(returnObjectSeptemberSpreadsheetResponseDTO());
                dtoList.add(returnObjectOctoberSpreadsheetResponseDTO());
            dtoList.add(returnObjectNovemberSpreadsheetResponseDTO());
        dtoList.add(returnObjectDecemberSpreadsheetResponseDTO());

        return  dtoList;
    }

    private SpreadsheetEntity returnObjectJanuarySpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_JANUARY_ID,MOCK_JANUARY_MONTH,MOCK_JANUARY_PROHIBITED,MOCK_JANUARY_OUTPUT,MOCK_JANUARY_TOTAL);
    }

    private SpreadsheetEntity returnObjectFebruarySpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_FEBRUARY_ID,MOCK_FEBRUARY_MONTH,MOCK_FEBRUARY_PROHIBITED,MOCK_FEBRUARY_OUTPUT,MOCK_FEBRUARY_TOTAL);
    }

    private SpreadsheetEntity returnObjectMarchSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_MARCH_ID,MOCK_MARCH_MONTH,MOCK_MARCH_PROHIBITED,MOCK_MARCH_OUTPUT,MOCK_MARCH_TOTAL);
    }

    private SpreadsheetEntity returnObjectAprilSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_APRIL_ID,MOCK_APRIL_MONTH,MOCK_APRIL_PROHIBITED,MOCK_APRIL_OUTPUT,MOCK_APRIL_TOTAL);
    }

    private SpreadsheetEntity returnObjectMaySpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_MAY_ID,MOCK_MAY_MONTH,MOCK_MAY_PROHIBITED,MOCK_MAY_OUTPUT,MOCK_MAY_TOTAL);
    }

    private SpreadsheetEntity returnObjectJuneSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_JUNE_ID,MOCK_JUNE_MONTH,MOCK_JUNE_PROHIBITED,MOCK_JUNE_OUTPUT,MOCK_JUNE_TOTAL);
    }

    private SpreadsheetEntity returnObjectJulySpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_JULY_ID,MOCK_JULY_MONTH,MOCK_JULY_PROHIBITED,MOCK_JULY_OUTPUT,MOCK_JULY_TOTAL);
    }

    private SpreadsheetEntity returnObjectAugustSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_AUGUST_ID,MOCK_AUGUST_MONTH,MOCK_AUGUST_PROHIBITED,MOCK_AUGUST_OUTPUT,MOCK_AUGUST_TOTAL);
    }

    private SpreadsheetEntity returnObjectSeptemberSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_SEPTEMBER_ID,MOCK_SEPTEMBER_MONTH,MOCK_SEPTEMBER_PROHIBITED,MOCK_SEPTEMBER_OUTPUT,MOCK_SEPTEMBER_TOTAL);
    }

    private SpreadsheetEntity returnObjectOctoberSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_OCTOBER_ID,MOCK_OCTOBER_MONTH,MOCK_OCTOBER_PROHIBITED,MOCK_OCTOBER_OUTPUT,MOCK_OCTOBER_TOTAL);
    }

    private SpreadsheetEntity returnObjectNovemberSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_NOVEMBER_ID,MOCK_NOVEMBER_MONTH,MOCK_NOVEMBER_PROHIBITED,MOCK_NOVEMBER_OUTPUT,MOCK_NOVEMBER_TOTAL);
    }

    private SpreadsheetEntity returnObjectDecemberSpreadsheetEntity(){
        return new SpreadsheetEntity(MOCK_DECEMBER_ID,MOCK_DECEMBER_MONTH,MOCK_DECEMBER_PROHIBITED,MOCK_DECEMBER_OUTPUT,MOCK_DECEMBER_TOTAL);
    }

    //dtos
    private SpreadsheetResponseDTO returnObjectJanuarySpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_JANUARY_MONTH,MOCK_JANUARY_PROHIBITED,MOCK_JANUARY_OUTPUT,MOCK_JANUARY_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectFebruarySpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_FEBRUARY_MONTH,MOCK_FEBRUARY_PROHIBITED,MOCK_FEBRUARY_OUTPUT,MOCK_FEBRUARY_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectMarchSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_MARCH_MONTH,MOCK_MARCH_PROHIBITED,MOCK_MARCH_OUTPUT,MOCK_MARCH_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectAprilSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_APRIL_MONTH,MOCK_APRIL_PROHIBITED,MOCK_APRIL_OUTPUT,MOCK_APRIL_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectMaySpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_MAY_MONTH,MOCK_MAY_PROHIBITED,MOCK_MAY_OUTPUT,MOCK_MAY_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectJuneSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_JUNE_MONTH,MOCK_JUNE_PROHIBITED,MOCK_JUNE_OUTPUT,MOCK_JUNE_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectJulySpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_JULY_MONTH,MOCK_JULY_PROHIBITED,MOCK_JULY_OUTPUT,MOCK_JULY_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectAugustSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_AUGUST_MONTH,MOCK_AUGUST_PROHIBITED,MOCK_AUGUST_OUTPUT,MOCK_AUGUST_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectSeptemberSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_SEPTEMBER_MONTH,MOCK_SEPTEMBER_PROHIBITED,MOCK_SEPTEMBER_OUTPUT,MOCK_SEPTEMBER_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectOctoberSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_OCTOBER_MONTH,MOCK_OCTOBER_PROHIBITED,MOCK_OCTOBER_OUTPUT,MOCK_OCTOBER_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectNovemberSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_NOVEMBER_MONTH,MOCK_NOVEMBER_PROHIBITED,MOCK_NOVEMBER_OUTPUT,MOCK_NOVEMBER_TOTAL);
    }

    private SpreadsheetResponseDTO returnObjectDecemberSpreadsheetResponseDTO(){
        return new SpreadsheetResponseDTO(MOCK_DECEMBER_MONTH,MOCK_DECEMBER_PROHIBITED,MOCK_DECEMBER_OUTPUT,MOCK_DECEMBER_TOTAL);
    }
}
