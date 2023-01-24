package br.com.geradordedevs.expensecontrol.facades;

import br.com.geradordedevs.expensecontrol.dtos.responses.UploadExcelResponseDTO;
import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SpreadsheetFacade {

    UploadExcelResponseDTO saveExcelUploudToDataBase(MultipartFile file) throws IOException;
    List<SpreadsheetResponseDTO> findAll();
}
