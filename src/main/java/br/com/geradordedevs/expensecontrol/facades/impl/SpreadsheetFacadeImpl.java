package br.com.geradordedevs.expensecontrol.facades.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.UploadExcelResponseDTO;
import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.exceptions.EmailSendingException;
import br.com.geradordedevs.expensecontrol.exceptions.ExcelException;
import br.com.geradordedevs.expensecontrol.exceptions.enums.EmailSendingEnum;
import br.com.geradordedevs.expensecontrol.exceptions.enums.ExcelEnum;
import br.com.geradordedevs.expensecontrol.facades.SpreadsheetFacade;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
import br.com.geradordedevs.expensecontrol.services.EmailJavaService;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
public class SpreadsheetFacadeImpl implements SpreadsheetFacade {

    @Autowired
    private SpreadsheetService spreadsheetService;

    @Autowired
    private SpreadsheetMapper mapper;

    @Autowired
    private EmailJavaService emailJavaService;

    @Override
    public UploadExcelResponseDTO saveExcelUploadToDataBase(MultipartFile file) throws IOException {
        UploadExcelResponseDTO excelUploadResponseDTO = new UploadExcelResponseDTO();

        if (spreadsheetService.isValidExcelFile(file)) {
            log.warn("valid worksheet");
            getCustomersDataFromExcel(file.getInputStream());
            excelUploadResponseDTO.setSuccess(true);
        } else {
            log.warn("invalid worksheet");
        }
        return excelUploadResponseDTO;
    }

    @Override
    public List<SpreadsheetResponseDTO> findAll() {
        return mapper.toDtoList(spreadsheetService.findAll());
    }

    public void getCustomersDataFromExcel(InputStream inputStream){
        log.info("reading the spreadsheet and passing it to the bank");

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Planilha1");

            int rowIndex = 0;
            for (Row row: sheet){
                if (rowIndex == 0){
                    rowIndex++;
                    continue;
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                SpreadsheetEntity spreadsheetEntity = new SpreadsheetEntity();

                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case  0 -> spreadsheetEntity.setMonth(cell.getStringCellValue());
                        case  1 -> spreadsheetEntity.setProhibited(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case  2 -> spreadsheetEntity.setOutput(BigDecimal.valueOf(cell.getNumericCellValue()));
                        case  3 -> spreadsheetEntity.setTotal(BigDecimal.valueOf(cell.getNumericCellValue()));
                    }
                    cellIndex++;
                }
                validNegativeTotal(spreadsheetEntity.getTotal(),spreadsheetEntity.getMonth(),spreadsheetEntity.getProhibited(),spreadsheetEntity.getOutput());
                spreadsheetService.saveExcelUploadToDataBase(spreadsheetEntity);
            }

        } catch (IOException e ) {
            throw  new ExcelException(ExcelEnum.INVALID_EXCEL_FILE);

        } catch (EmailException e) {
            throw new EmailSendingException(EmailSendingEnum.ERROR_SEND_EMAIL);
        }
    }

    public void validNegativeTotal( BigDecimal total,String month, BigDecimal prohibited , BigDecimal output) throws EmailException {
        double totalDouble = total.doubleValue();

        if (totalDouble < 0){
            log.info("the total of {} is negative",total);
            emailJavaService.sendEmail(totalDouble,month,prohibited,output);
        }
    }
}
