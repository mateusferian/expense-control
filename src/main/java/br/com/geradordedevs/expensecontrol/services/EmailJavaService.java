package br.com.geradordedevs.expensecontrol.services;

import org.apache.commons.mail.EmailException;

public interface EmailJavaService {
    void sendEmail(double totalDouble) throws EmailException;
}
