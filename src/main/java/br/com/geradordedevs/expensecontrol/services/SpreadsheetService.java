package br.com.geradordedevs.expensecontrol.services;

import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import org.springframework.web.multipart.MultipartFile;

public interface SpreadsheetService {

    void saveExcelUploudToDataBase(MultipartFile file);
    Iterable<SpreadsheetEntity> findAll();
}
