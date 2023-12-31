package br.com.geradordedevs.expensecontrol.services;

import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SpreadsheetService {

    SpreadsheetEntity saveExcelUploadToDataBase(SpreadsheetEntity entity);
    Iterable<SpreadsheetEntity> findAll();
    boolean isValidExcelFile(MultipartFile file);
}
