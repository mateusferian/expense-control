package br.com.geradordedevs.expensecontrol.services.impl;

import br.com.geradordedevs.expensecontrol.exceptions.EmailSendingException;
import br.com.geradordedevs.expensecontrol.exceptions.enums.EmailSendingEnum;
import br.com.geradordedevs.expensecontrol.services.EmailJavaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class EmailJavaServiceImpl implements EmailJavaService {

    public EmailJavaServiceImpl() {

    }

    public void sendEmail(double totalDouble, String month, BigDecimal prohibited , BigDecimal output) {

        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(587);
        email.setDebug(true);
        email.setAuthentication("carlosantoniocleiton@gmail.com", "vrvuggyswdjsmkls");
        email.setSSLOnConnect(true);

        try {
            double total = totalDouble*-1;
            email.setFrom("carlosantoniocleiton@gmail.com");
            email.setSubject("valor anual");
            email.setSubject("controle de negocios");
            email.addTo("gooddesiger10@gmail.com");
            email.setHtmlMsg("<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                    "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                    "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap\" rel=\"stylesheet\">\n" +
                    "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                    "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                    "    <link href=\"https://fonts.googleapis.com/css2?family=Quicksand:wght@300;400;500;600;700&family=Roboto+Condensed:ital,wght@0,300;0,400;0,700;1,300;1,400;1,700&display=swap\" rel=\"stylesheet\">\n" +
                    "    <title>Document</title>\n" +
                    "</head>\n" +
                    "<body style=\"padding: 0; margin: 0;\">\n" +
                    "    <table style=\"text-align: center ; background-color: #ECECEC; width: 55%; height: 587px;\">\n" +
                    "        <tr>\n" +
                    "            <td style=\"margin-top: 70px; display: inline-block; width: 600px; height: 341px; border-radius: 12px; background-color: #F5F5F5;\">\n" +
                    "                <h2 style=\"margin-top: 24px; margin-left: 50px; width: 500px; height: 39px; font-size: 29px; font-family: 'Quicksand', sans-serif; font-weight: 600; line-height: 37px; color: #628D96; text-transform: capitalize;\">Gd Expense Controll informa!</h2>\n" +
                    "                <h4 style=\"margin-top: 24px; margin-left: 55px; width: 500px; height: 22px; font-family: 'Quicksand', sans-serif; font-weight: 600; font-size: 16px; color: #334E68\">marge final do mês de "+month+ " negativa!</h4>\n" +
                    "                <h4 style=\"margin-top: 24px; margin-left: 94px; width: 400px; height: 20px; font-family: 'Quicksand', sans-serif; font-weight: 400; font-size: 12px; color: #243B53\">" +
                    "                    com base nas informações passadas para o nosso sistemas <br> fizemos uma analise anual"+
                    "                </h4>\n" +

                    "                <h3 style=\"margin-top: 24px; font-family: Roboto Condensed; font-size: 29px; font-weight: 600; line-height: 34px; letter-spacing: 11px ;text-align: center; color: #7BA0A8;</h3>\n" +
                    "                <h4 style=\"margin-top: 24px; margin-left: 124px; width: 344px; height: 20px; font-family: 'Quicksand', sans-serif; font-weight: 400; font-size: 16px; color: #243B53\">" +
                    "                    <br> Podemos relatar que nesse mes teve uma entrada de R$" +prohibited+"<br> com saida de R$"+output+
                    "                    contendo uma marge negativa de R$" +total+
                    "                </h4>\n" +
                    "                <h4 style=\"margin-top: 24px; margin-left: 40px; width: 500px; height: 20px; font-family: 'Quicksand', sans-serif; font-weight: 400; font-size: 12px; color: #243B53\">" +
                    "                    </b>gostaria de mais informações?<br><b>entre em contato e faça mais outras analise do seu negocio!</b><br> telefone:(11)1111-1111.  Avenida Paulista, nº 550, São Paulo, cep:01153 000"+
                    "                </h4>\n" +
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
