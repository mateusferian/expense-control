package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.services.EmailJavaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailJavaServiceImpl implements EmailJavaService {

    public EmailJavaServiceImpl() {

    }

    public void sendEmail(double totalDouble) throws EmailException {

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(587);
        email.setDebug(true);
        email.setAuthentication("carlosantoniocleiton@gmail.com", "fukyxbclhjwokydm");
        email.setSSLOnConnect(true);

        try {
            email.setFrom("carlosantoniocleiton@gmail.com");
            email.setSubject("valor anual");
            email.setMsg("o valor final foi negativo com um valor anual de R$"+ totalDouble);
            email.addTo("carlosantoniocleiton@gmail.com");
            email.send();
            log.warn("email successfully sent");

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
