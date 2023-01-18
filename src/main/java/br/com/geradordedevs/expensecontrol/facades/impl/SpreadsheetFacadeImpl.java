package br.com.geradordedevs.expensecontrol.facades.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.UploadExcelResponseDtO;
import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.facades.SpreadsheetFacade;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
import br.com.geradordedevs.expensecontrol.services.ExcelUploudService;
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
    private ExcelUploudService excelUploudService;

    @Autowired
    private SpreadsheetMapper mapper;

    @Override
    public UploadExcelResponseDtO saveExcelUploudToDataBase(MultipartFile file) {
        log.info("criando novo arquivo");
        UploadExcelResponseDtO excelUploudResponseDTO = new UploadExcelResponseDtO();

        if (excelUploudService.isValidExcelFile(file)) {
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
