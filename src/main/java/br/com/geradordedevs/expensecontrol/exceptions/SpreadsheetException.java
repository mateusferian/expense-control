package br.com.geradordedevs.expensecontrol.exceptions;

import br.com.geradordedevs.expensecontrol.exceptions.enums.SpreadsheetEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SpreadsheetException extends  ExpenseControlProviderException{

    public  SpreadsheetException(SpreadsheetEnum error){
        super(error.getMessage());
        this.error = error;
    }

    private  final  SpreadsheetEnum error;}
