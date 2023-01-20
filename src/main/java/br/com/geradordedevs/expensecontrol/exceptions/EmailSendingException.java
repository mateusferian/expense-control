package br.com.geradordedevs.expensecontrol.exceptions;

import br.com.geradordedevs.expensecontrol.exceptions.enums.EmailSendingEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EmailSendingException extends ExpenseControlException {

    public EmailSendingException(EmailSendingEnum error){
        super(error.getMessage());
        this.error =  error;
    }

    private  final EmailSendingEnum error;
}
