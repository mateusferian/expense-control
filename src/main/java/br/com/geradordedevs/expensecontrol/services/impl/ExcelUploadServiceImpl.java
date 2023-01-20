package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.exceptions.EmailSendingException;
import br.com.geradordedevs.expensecontrol.exceptions.ExcelException;
import br.com.geradordedevs.expensecontrol.exceptions.enums.EmailSendingEnum;
import br.com.geradordedevs.expensecontrol.exceptions.enums.ExcelEnum;
import br.com.geradordedevs.expensecontrol.repositories.SpreadsheetRepository;
import br.com.geradordedevs.expensecontrol.services.EmailJavaService;
import br.com.geradordedevs.expensecontrol.services.ExcelUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;
@Service
@Slf4j
public class ExcelUploadServiceImpl implements ExcelUploadService {

    @Autowired
    private SpreadsheetRepository spreadsheetRepository;

    @Autowired
    EmailJavaService emailJavaService;

    @Override
    public boolean isValidExcelFile(MultipartFile file){
        log.info("doing validation");

        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    @Override
    public  void getCustomersDataFromExcel(InputStream inputStream){
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
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                validNegativeTotal(spreadsheetEntity.getTotal());
                spreadsheetRepository.save(spreadsheetEntity);
            }
        } catch (IOException e ) {
            throw  new ExcelException(ExcelEnum.INVALID_EXCEL_FILE);

        } catch (EmailException e) {
            throw new EmailSendingException(EmailSendingEnum.ERROR_SEND_EMAIL);
        }
    }

    public  void validNegativeTotal( BigDecimal total) throws EmailException {
        double  totalDouble = total.doubleValue();
        if (totalDouble < 0){
            log.info("the total of {} is negative",total);
            emailJavaService.sendEmail(totalDouble);
        }
    }
}
