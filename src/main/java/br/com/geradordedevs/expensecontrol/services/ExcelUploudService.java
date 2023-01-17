package br.com.geradordedevs.expensecontrol.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ExcelUploudService {

    boolean isValidExcelFile(MultipartFile file);
    void getCustomersDataFromExcel(InputStream inputStream);
}
