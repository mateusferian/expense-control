package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import br.com.geradordedevs.expensecontrol.repositories.SpreadsheetRepository;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@Slf4j
public class SpreadsheetServiceImpl implements SpreadsheetService {

    @Autowired
    private SpreadsheetRepository spreadsheetRepository;

    @Override
    public SpreadsheetEntity saveExcelUploudToDataBase(SpreadsheetEntity entity) {
        log.info("registering a new file");
        return spreadsheetRepository.save(entity);
    }

    @Override
    public Iterable<SpreadsheetEntity> findAll() {
        return spreadsheetRepository.findAll();
    }

    @Override
    public boolean isValidExcelFile(MultipartFile file){
        log.info("doing validation");
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
}

