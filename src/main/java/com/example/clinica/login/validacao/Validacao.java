package com.example.clinica.login.validacao;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Validacao {

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
