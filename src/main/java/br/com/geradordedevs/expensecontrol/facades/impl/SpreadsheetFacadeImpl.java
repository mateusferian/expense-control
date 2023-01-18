package br.com.geradordedevs.expensecontrol.facades.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.UploadExcelResponseDTO;
import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.facades.SpreadsheetFacade;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
import br.com.geradordedevs.expensecontrol.services.ExcelUploadService;
import br.com.geradordedevs.expensecontrol.services.SpreadsheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Component
public class SpreadsheetFacadeImpl implements SpreadsheetFacade {

    @Autowired
    private SpreadsheetService spreadsheetService;

    @Autowired
    private ExcelUploadService excelUploadService;

    @Autowired
    private SpreadsheetMapper mapper;

    @Override
    public UploadExcelResponseDTO saveExcelUploudToDataBase(MultipartFile file) {
        UploadExcelResponseDTO excelUploudResponseDTO = new UploadExcelResponseDTO();

        if (excelUploadService.isValidExcelFile(file)) {
            log.warn("valid worksheet");
            spreadsheetService.saveExcelUploudToDataBase(file);
            excelUploudResponseDTO.setSuccess(true);
        } else {
            log.warn("invalid worksheet, no value was passed");
        }
        return excelUploudResponseDTO;
    }

    @Override
    public List<SpreadsheetResponseDTO> findAll() {
        return mapper.toDtoList(spreadsheetService.findAll());
    }
}
