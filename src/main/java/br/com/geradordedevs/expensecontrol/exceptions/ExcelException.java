package br.com.geradordedevs.expensecontrol.exceptions;

import br.com.geradordedevs.expensecontrol.exceptions.enums.ExcelEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ExcelException extends ExpenseControlException {

    public ExcelException(ExcelEnum error){
        super(error.getMessage());
        this.error =  error;
    }

    private final ExcelEnum error;
}
