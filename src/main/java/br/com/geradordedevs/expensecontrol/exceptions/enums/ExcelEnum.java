package br.com.geradordedevs.expensecontrol.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExcelEnum {

    INVALID_EXCEL_FILE("INVALID_EXCEL_FILE","este arquivo é um arquivo excel invalido",401);

    private  String code;
    private String message;
    private  Integer statusCode;
}
