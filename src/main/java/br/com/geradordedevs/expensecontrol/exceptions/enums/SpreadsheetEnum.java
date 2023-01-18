package br.com.geradordedevs.expensecontrol.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum SpreadsheetEnum {

    EXCEL_INVALID("EXCEL_INVALID","este arquivo não é um arquivo excel válido",400);

    private  String code;
    private  String message;
    private  Integer statusCode;
}
