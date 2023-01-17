package br.com.geradordedevs.expensecontrol.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpreadsheetResponseDTO {


    private String month;

    private BigDecimal prohibited;

    private  BigDecimal output;

    private  BigDecimal total;
}
