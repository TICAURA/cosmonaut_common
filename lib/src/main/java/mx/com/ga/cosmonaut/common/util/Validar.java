package mx.com.ga.cosmonaut.common.util;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class Validar {

    private Validar() {
        super();
    }

    public static boolean rfc(String rfc){
        boolean es;
        String regex;
        if (rfc.length() == 12){
            regex = "^(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))";
        }else{
            regex = "^(([A-Z]|[a-z]|'\'s){1})(([A-Z]|[a-z]){3})([0-9]{6})((([A-Z]|[a-z]|[0-9]){3}))";
        }
        es = Pattern.matches(regex, rfc);
        return es;
    }

    public static boolean correo(String mail){
        String regexMail = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regexMail, mail);
    }

    public static boolean curp(String curp){
        String regexCurp= "[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9,A-Z]{2}";
        return Pattern.matches(regexCurp, curp);
    }

    public static boolean caracteresEspeciales(String texto){
        String regexCurp= "[A-Z]";
        return Pattern.matches(regexCurp, texto);
    }

    public static RespuestaGenerica numeroSerguroSocial(String nss,Date fecha){
        RespuestaGenerica respuesta = new RespuestaGenerica(null,Constantes.RESULTADO_EXITO,Constantes.EXITO);
        String regexCurp= "[0-9]{11}";
        if (Pattern.matches(regexCurp, nss)){
            respuesta = validaFechaNss(fecha, nss);
            if (respuesta.isResultado()){
                respuesta = validaDigitoValidadorNss(nss);
            }
        }else {
            respuesta.setResultado(Constantes.RESULTADO_ERROR);
            respuesta.setMensaje(Constantes.NUMERO_SEGURO_SOCIAL_NO_VALIDO);
        }
        return respuesta;
    }

    private static RespuestaGenerica validaFechaNss(Date fecha, String nss) {
        RespuestaGenerica respuesta = new RespuestaGenerica(null,Constantes.RESULTADO_EXITO,Constantes.EXITO);
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.setTime(fecha);
        Integer anioNacimiento = fechaNacimiento.get(Calendar.YEAR);
        String sAnioNacimiento = anioNacimiento.toString().substring(2,4);
        anioNacimiento = Integer.valueOf(sAnioNacimiento);
        String sAnioNss =  nss.substring(4,6);
        Integer anioNss= Integer.valueOf(sAnioNss);
        if (!anioNacimiento.equals(anioNss)){
            respuesta.setResultado(Constantes.RESULTADO_ERROR);
            respuesta.setMensaje("El campo seguro social es inválido.");
        }
        return respuesta;
    }

    private static RespuestaGenerica validaDigitoValidadorNss(String nss) {

        RespuestaGenerica respuesta = new RespuestaGenerica(null,Constantes.RESULTADO_EXITO,Constantes.EXITO);
        String sDigitoValidador = nss.substring(nss.length() - 1, nss.length());
        nss = nss.substring(0, nss.length()-1);

        Integer digitoValidador = Integer.valueOf(sDigitoValidador);
        String sNumero = "";
        Integer digito = 0;
        for (int i = 0; i < nss.length(); i++){
            sNumero = String.valueOf(nss.charAt(i));
            Integer numero = Integer.valueOf(sNumero);
            if (i % 2 == 0){
                numero = numero * 1;
            }else {
                numero = numero * 2;
            }
            sNumero = numero.toString();
            if (sNumero.length() != 1){
                String sNumeroUno = String.valueOf(sNumero.charAt(0));
                Integer numUno = Integer.valueOf(sNumeroUno);
                String sNumeroDos = String.valueOf(sNumero.charAt(1));
                Integer numDos = Integer.valueOf(sNumeroDos);
                numero = numUno + numDos;
            }
            digito = digito + numero;
        }
        String sDigito = digito.toString();
        sNumero = String.valueOf(sDigito.charAt(1));
        digito = Integer.valueOf(sNumero);
        digito = 10 - digito;

        if (digito > 9){
            digito = 0;
        }

        if (!digitoValidador.equals(digito)){
            respuesta.setResultado(Constantes.RESULTADO_ERROR);
            respuesta.setMensaje("El dígito verificador es incorrecto, debería ser:" + digito +".");
        }
        return respuesta;
    }

    public static boolean validaTexto (String texto){
        return texto != null && !texto.isEmpty();
    }

    public static boolean telefono(Long phone){
        return phone.toString().length() <= Constantes.NUMERO_TELEFONO;
    }

    public static boolean numeroFolio(String numeroFolio){
        String regexCurp= "[A-Z]{2}[0-9]{6}";
        return Pattern.matches(regexCurp, numeroFolio);
    }

    public static boolean caracteresEspeciale(String numeroFolio){
        String regexCurp= "^[^<>%$@/]*$";
        return !Pattern.matches(regexCurp, numeroFolio);
    }
}
