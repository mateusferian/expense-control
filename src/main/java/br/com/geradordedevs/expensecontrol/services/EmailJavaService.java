package br.com.geradordedevs.expensecontrol.services;

import org.apache.commons.mail.EmailException;

import java.math.BigDecimal;

public interface EmailJavaService {

    void sendEmail(double totalDouble, String month, BigDecimal prohibited , BigDecimal output) throws EmailException;
}
