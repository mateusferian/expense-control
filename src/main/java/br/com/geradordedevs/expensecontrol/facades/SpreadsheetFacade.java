package br.com.geradordedevs.expensecontrol.facades;

import br.com.geradordedevs.expensecontrol.dtos.responses.UploadExcelResponseDtO;
import br.com.geradordedevs.expensecontrol.dtos.responses.SpreadsheetResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpreadsheetFacade {

    UploadExcelResponseDtO saveExcelUploudToDataBase(MultipartFile file);

    List<SpreadsheetResponseDTO> findAll();
}
