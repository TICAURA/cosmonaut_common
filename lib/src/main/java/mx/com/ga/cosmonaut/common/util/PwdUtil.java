package mx.com.ga.cosmonaut.common.util;

import java.security.SecureRandom;
import java.util.*;

public class PwdUtil {

    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBER = "0123456789";
    private static final String SPECIAL = "!@#$%&*_+-=,.?"; // !@#$%&*()_+-=[]|,./?><

    public static String generatePwd(int length) {
        if (length <= 0) {
            return "";
        }

        StringBuilder pwd = new StringBuilder(length);
        SecureRandom secureRandom = new SecureRandom();

        List<String> categorias = new ArrayList<>(4);
        categorias.add(LOWER);
        categorias.add(UPPER);
        categorias.add(NUMBER);
        categorias.add(SPECIAL);

        for (int i = 0; i < length; i++) {
            String categoria = categorias.get(secureRandom.nextInt(categorias.size()));
            int position = secureRandom.nextInt(categoria.length());
            pwd.append(categoria.charAt(position));
        }
        return pwd.toString();
    }

    private PwdUtil() {
        super();
    }

}
