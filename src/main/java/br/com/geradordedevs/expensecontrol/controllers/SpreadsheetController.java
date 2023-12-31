package br.com.geradordedevs.expensecontrol.controllers;

import br.com.geradordedevs.expensecontrol.dtos.responses.UploadExcelResponseDTO;
import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.facades.SpreadsheetFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses-controls")
public class SpreadsheetController {

    @Autowired
    private SpreadsheetFacade spreadsheetFacade;

    @PostMapping("/upload-spreadsheet-data")
    public ResponseEntity<UploadExcelResponseDTO> saveExcelUploadToDataBase(@RequestParam("file") MultipartFile file) throws IOException{
        return new ResponseEntity<>(spreadsheetFacade.saveExcelUploadToDataBase(file),HttpStatus.CREATED);
    }

    @GetMapping
    public List<SpreadsheetResponseDTO> findAll(){
        return spreadsheetFacade.findAll();
    }
}
