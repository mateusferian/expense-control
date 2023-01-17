package br.com.geradordedevs.expensecontrol.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpreadsheetRequestDTO {

    private String month;

    private BigDecimal prohibited;

    private  BigDecimal output;

    private  BigDecimal total;
}
