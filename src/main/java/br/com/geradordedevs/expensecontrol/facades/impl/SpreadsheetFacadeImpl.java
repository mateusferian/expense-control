package br.com.geradordedevs.expensecontrol.facades.impl;

import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import br.com.geradordedevs.expensecontrol.facades.SpreadsheetFacade;
import br.com.geradordedevs.expensecontrol.mappers.SpreadsheetMapper;
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
    private SpreadsheetMapper mapper;

    @Override
    public void saveExcelUploudToDataBase(MultipartFile file) {
        log.info("criando novo arquivo");
        spreadsheetService.saveExcelUploudToDataBase(file);
    }

    @Override
    public List<SpreadsheetResponseDTO> findAll() {
        return mapper.toDtoList(spreadsheetService.findAll());
    }
}
