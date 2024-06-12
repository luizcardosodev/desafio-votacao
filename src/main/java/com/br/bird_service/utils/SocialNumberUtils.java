package com.br.bird_service.utils;

import java.util.InputMismatchException;
import java.util.Objects;

public class SocialNumberUtils {

    public static boolean cpfValidate(final String cpf) {
        if(Objects.isNull(cpf))
            return false;
        var cpfOnlyNumbers = removeSpecialCharacter(cpf);

        if (cpfOnlyNumbers.equals("00000000000")
                || cpfOnlyNumbers.equals("11111111111")
                || cpfOnlyNumbers.equals("22222222222")
                || cpfOnlyNumbers.equals("33333333333")
                || cpfOnlyNumbers.equals("44444444444")
                || cpfOnlyNumbers.equals("55555555555")
                || cpfOnlyNumbers.equals("66666666666")
                || cpfOnlyNumbers.equals("77777777777")
                || cpfOnlyNumbers.equals("88888888888")
                || cpfOnlyNumbers.equals("99999999999")
                || (cpfOnlyNumbers.length() != 11)
        )
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpfOnlyNumbers.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48);

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpfOnlyNumbers.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            return (dig10 == cpfOnlyNumbers.charAt(9)) && (dig11 == cpfOnlyNumbers.charAt(10));
        } catch (InputMismatchException err) {
            return (false);
        }
    }

    private static String removeSpecialCharacter(String doc) {
        if (doc.contains(".")) {
            doc = doc.replace(".", "");
        }
        if (doc.contains("-")) {
            doc = doc.replace("-", "");
        }
        if (doc.contains("/")) {
            doc = doc.replace("/", "");
        }
        return doc;
    }
}
