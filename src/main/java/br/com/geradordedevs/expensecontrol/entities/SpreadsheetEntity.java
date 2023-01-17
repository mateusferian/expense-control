package br.com.geradordedevs.expensecontrol.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpreadsheetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String month;

    private BigDecimal prohibited;

    private  BigDecimal output;

    private  BigDecimal total;
}
