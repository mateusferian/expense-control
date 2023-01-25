package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.exceptions.EmailSendingException;
import br.com.geradordedevs.expensecontrol.exceptions.enums.EmailSendingEnum;
import br.com.geradordedevs.expensecontrol.services.EmailJavaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class EmailJavaServiceImpl implements EmailJavaService {

    @Value("${email.setHostName}")
    private String hostName;

    @Value("${email.setSmtpPort}")
    private int smtpPort;

    @Value("${email.userName}")
    private String userName;

    @Value("${email.setFrom}")
    private String emailFrom;

    @Value("${email.password}")
    private String password;

    @Value("${email.addTo}")
    private String to;

    @Override
    public void sendEmail(double totalDouble, String month, BigDecimal prohibited , BigDecimal output) {

        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostName);
        email.setSmtpPort(smtpPort);
        email.setDebug(true);
        email.setAuthentication(userName, password);
        email.setSSLOnConnect(true);
        email.setSSLCheckServerIdentity(true);

        try {
            double total = totalDouble*-1;
            email.setFrom(emailFrom);
            email.setSubject("valor anual");
            email.setSubject("controle de negocios");
            email.addTo(to);

            email.setHtmlMsg("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>email</title>\n" +
                    "</head>\n" +
                    "<body style=\"padding: 0; margin: 0;\">\n" +
                    "    <table style=\"text-align: center ; background-color: #ECECEC; width: 55%; height: 587px;\">\n" +
                    "        <tr>\n" +
                    "            <td style=\"margin-top: 70px; display: inline-block; width: 600px; height: 341px; border-radius: 12px; background-color: #F5F5F5;\">\n" +
                    "                <h2 style=\"margin-top: 24px; margin-left: 50px; width: 500px; height: 39px; font-size: 29px; font-family: 'Quicksand', sans-serif; font-weight: 600; line-height: 37px; color: #628D96; text-transform: capitalize;\">Gd Expense Controll informa!</h2>\n" +
                    "                <h4 style=\"margin-top: 24px; margin-left: 55px; width: 500px; height: 22px; font-family: 'Quicksand', sans-serif; font-weight: 600; font-size: 16px; color: #334E68\">marge final do mês de "+month+ " negativa!</h4>\n" +
                    "                <h4 style=\"margin-top: 24px; margin-left: 94px; width: 400px; height: 20px; font-family: 'Quicksand', sans-serif; font-weight: 400; font-size: 12px; color: #243B53\">" +
                    "                   com base nas informações passadas para o nosso sistemas <br> fizemos uma analise anual </h4>\n" +
                    "                <h4 style=\"margin-top: 24px; margin-left: 2px; width: 600px; height: 20px; font-family: 'Quicksand', sans-serif; font-weight: 400; font-size: 13px; color: #283D52\">" +
                    "                    <br><b>Podemos relatar que nesse mês teve uma entrada de R$" +prohibited+
                    "                    <br>com saída de R$"+output+" contendo uma marge negativa de R$" +total+ "</br></h4>\n"+
                    "            </td>\n" +
                    "        </tr>\n" +
                    "    </table>\n" +
                    "</body>");

            email.send();
            log.warn("email successfully sent");
        } catch (EmailException e ) {
            throw  new EmailSendingException(EmailSendingEnum.ERROR_SEND_EMAIL);
        }
    }
}
