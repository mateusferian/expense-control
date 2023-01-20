package br.com.geradordedevs.expensecontrol.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EmailSendingEnum {

    ERROR_SEND_EMAIL ("ERROR_SEND_EMAIL","erro ao enviar email",500);

    private  String code;
    private String message;
    private  Integer statusCode;
}
