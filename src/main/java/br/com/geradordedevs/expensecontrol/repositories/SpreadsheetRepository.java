package br.com.geradordedevs.expensecontrol.repositories;

import br.com.geradordedevs.expensecontrol.entities.SpreadsheetEntity;
import org.springframework.data.repository.CrudRepository;

public interface SpreadsheetRepository extends CrudRepository<SpreadsheetEntity,Long> {
}
