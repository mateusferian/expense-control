package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.repositories.SpreadsheetRepository;
import br.com.geradordedevs.expensecontrol.services.ExcelUploadService;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class SpreadsheetServiceImpl implements SpreadsheetService {

    @Autowired
    private SpreadsheetRepository spreadsheetRepository;

    @Autowired
    private ExcelUploadService excelUploadService;

    @Override
    public void saveExcelUploudToDataBase(MultipartFile file) {
        log.info("registering a new file");
        if (excelUploadService.isValidExcelFile(file)){
            try {

                excelUploadService.getCustomersDataFromExcel(file.getInputStream());
            } catch (IOException e) {
                throw new IllegalArgumentException("this file is not a valid excel file");
            }
        }
    }

    @Override
    public Iterable<SpreadsheetEntity> findAll() {
        return spreadsheetRepository.findAll();
    }
}

