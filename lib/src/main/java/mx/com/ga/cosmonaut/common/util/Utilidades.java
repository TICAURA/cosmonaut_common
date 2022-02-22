package mx.com.ga.cosmonaut.common.util;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.dto.RespuestaGenericaGruop;
import mx.com.ga.cosmonaut.common.dto.utilidades.ExtractFilesFromTectelDto;
import mx.com.ga.cosmonaut.common.entity.temporal.CargaMasivaEmpleado;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Utilidades {

    private Utilidades() {
        super();
    }

    public static Date obtenerFechaSystema() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(Constantes.ZONA));
        calendar.add(Calendar.HOUR_OF_DAY,-5);
        return calendar.getTime();
    }

    public static UUID generateObjectId() {
        return UUID.randomUUID();
    }

    public static byte[] decodeContent(String texto) {
        return Base64.getDecoder().decode(texto);
    }

    public static String encodeContent(byte[] archivo) {
        return Base64.getEncoder().encodeToString(archivo);
    }

    public static String eliminarBucket(String url, String bucket) {
        return url.replace(bucket + "/", "");
    }

    public static RespuestaGenerica respuestaError() {
        RespuestaGenerica response = new RespuestaGenerica();
        response.setResultado(false);
        response.setMensaje(Constantes.ERROR);
        return response;
    }

    public static RespuestaGenericaGruop respuestaRepetidaGroup() {
        RespuestaGenericaGruop response = new RespuestaGenericaGruop();
        response.setResultado(false);
        response.setMensaje(Constantes.ERROR);
        return response;
    }

    public static RespuestaGenerica respuestaRepetida(String dato) {
        RespuestaGenerica response = new RespuestaGenerica();
        response.setResultado(false);
        response.setMensaje(dato);
        return response;
    }

    public static String validaString(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "";
        } else {
            return texto;
        }
    }

    public static Boolean validaTexto(String texto) {
        return texto != null && !texto.isEmpty();
    }

    public static String fechaTexto(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (fecha != null) {
            return sdf.format(fecha);
        } else {
            return "";
        }
    }

    public static String fechaTexto(Timestamp fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (fecha != null) {
            return sdf.format(fecha);
        } else {
            return "";
        }
    }

    public static String numeroTexto(Long numero) {
        if (numero != null) {
            return numero.toString();
        } else {
            return "";
        }
    }

    public static String numeroTexto(Integer numero) {
        if (numero != null) {
            return numero.toString();
        } else {
            return "";
        }
    }

    public static String numeroTexto(BigDecimal numero) {
        if (numero != null) {
            return numero.toString();
        } else {
            return "";
        }
    }

    public static String numeroTexto(Double numero) {
        if (numero != null) {
            Integer n = numero.intValue();
            return n.toString();
        } else {
            return "";
        }
    }

    public static Long bigDecimalLong(BigDecimal numero) {
        if (numero != null) {
            return numero.longValue();
        } else {
            return 0L;
        }
    }

    public static Long bigIntegerLong(BigInteger numero) {
        if (numero != null) {
            return numero.longValue();
        } else {
            return 0L;
        }
    }

    public static Timestamp covierteHorario(String horario) {
        LocalTime localTime = LocalTime.parse(horario);
        Date date = java.sql.Timestamp.valueOf(localTime.atDate(LocalDate.now()));
        return Timestamp.valueOf(date.toString());
    }

    public static String  formatoMoneda(BigDecimal numero) {
        String sNumero = "";
        if(numero != null){
            DecimalFormat objDF = new DecimalFormat ("$ ###,###.##");
            sNumero = objDF.format (numero);
        }
        return sNumero;
    }

    public static List<CargaMasivaEmpleado> eliminaRegistrosLista(List<CargaMasivaEmpleado> lista, Integer registros){
        for (int i = 0; i < registros; i++) {
            lista.remove(0);
        }
        return lista;
    }

    public static String genero(String genero) {
        if (genero != null) {
            if (genero.equalsIgnoreCase("H")) {
                return "Hombre";
            } else if (genero.equalsIgnoreCase("M")) {
                return "Mujer";
            }
            else {
                return "";
            }
        } else {
            return  "";
        }
    }

    public static String estadoCivil(String estadoCivil){
        String texto;
        switch (estadoCivil) {
            case "S":
                texto = "Solter@";
                break;
            case "C":
                texto = "Casad@";
                break;
            case "D":
                texto = "Divorciad@";
                break;
            case "V":
                texto = "Viud@";
                break;
            default:
                texto = "";
                break;
        }
        return texto;
    }

    public static boolean isNotValidPwdDate(Date lastPasswordModifiedDate) {
        Date today = obtenerFechaSystema();

        Calendar cal = Calendar.getInstance();
        cal.setTime(lastPasswordModifiedDate);
        cal.add(Calendar.DAY_OF_MONTH,60);
        lastPasswordModifiedDate = cal.getTime();

        // false la contraseña tienen menos de 60 dias, true tienen mas de 60 dias sin cambiar
        return today.after(lastPasswordModifiedDate);
    }

    public static Date textoFecha(String fecha) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(fecha);
    }

    public static Integer obtenAnio(Date fecha) {
        Calendar calendarioFecha = Calendar.getInstance();
        calendarioFecha.setTime(fecha);
        return calendarioFecha.get(Calendar.YEAR);
    }

    public static Date obtenPrimerDiaAnio(int year){
        Calendar calendario = Calendar.getInstance();
        calendario.clear();
        calendario.set(Calendar.YEAR, year);
        return calendario.getTime();
    }

    public static Date obtenUltimoDiaAnio(int year){
        Calendar calendario = Calendar.getInstance();
        calendario.clear();
        calendario.set(Calendar.YEAR, year);
        calendario.roll(Calendar.DAY_OF_YEAR, -1);
        return calendario.getTime();
    }

    public static String fileTxtToZipBase64 (String fileBase64) throws IOException {
        String uuid = UUID.randomUUID().toString();

        String tempFolder = "/temp/";
        if (!Files.exists(Paths.get(tempFolder))) {
            Files.createDirectory(Paths.get(tempFolder));
        }

        String filePhysical = tempFolder + uuid + ".txt";
        String filePhysicalZip = tempFolder + uuid + ".zip";

        Path filePath = Files.write(Paths.get(filePhysical), decodeContent(fileBase64));
        Path fileZipPath = Files.write(Paths.get(filePhysicalZip), decodeContent(fileBase64));

        zipSingleFile(filePath, filePhysicalZip);

        String zipEncoded = encodeContent(Files.readAllBytes(fileZipPath));

        Files.deleteIfExists(filePath);
        Files.deleteIfExists(fileZipPath);

        return zipEncoded;
    }

    private static void zipSingleFile(Path source, String zipFileName) throws IOException {
        if (!Files.isRegularFile(source)) {
            return;
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName));
             FileInputStream fis = new FileInputStream(source.toFile())) {

            ZipEntry zipEntry = new ZipEntry(source.getFileName().toString());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }
    }

    public static List<ExtractFilesFromTectelDto> extractFilesBase64FromTectelResponse(String soapBase64, String tag)
            throws IOException {
        String zipBase64 = null;
        String soapResponse = new String(decodeContent(soapBase64));

        Document doc = Jsoup.parse(soapResponse, "", Parser.xmlParser());
        for (Element e : doc.select(tag)) {
            zipBase64 = e.ownText();
        }

        List<ExtractFilesFromTectelDto> mapBase64Files = new ArrayList<>();
        if (zipBase64 != null) {
            try (ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(decodeContent(zipBase64)))) {
                for (ZipEntry entry; (entry = zipIn.getNextEntry()) != null; ) {
                    ExtractFilesFromTectelDto extractFilesFromTectelDto = new ExtractFilesFromTectelDto();
                    extractFilesFromTectelDto.setNombreArchivo(entry.getName());
                    extractFilesFromTectelDto.setArchivo(zipIn.readAllBytes());
                    mapBase64Files.add(extractFilesFromTectelDto);
                }
            }
        }
        return mapBase64Files;
    }

    public static String fechaImssToFechaEstandar20XX(String fechaImss) {
        String[] fragmentos = fechaImss.split("/");
        String dia = fragmentos[0];

        String mes;
        switch (fragmentos[1]) {
            default:
            case "ene":
                mes = "01";
                break;
            case "feb":
                mes = "02";
                break;
            case "mar":
                mes = "03";
                break;
            case "abr":
                mes = "04";
                break;
            case "may":
                mes = "05";
                break;
            case "jun":
                mes = "06";
                break;
            case "jul":
                mes = "07";
                break;
            case "ago":
                mes = "08";
                break;
            case "sep":
                mes = "09";
                break;
            case "oct":
                mes = "10";
                break;
            case "nov":
                mes = "11";
                break;
            case "dic":
                mes = "12";
                break;
        }

        String anio = "20" + fragmentos[2];
        return anio + "-" + mes + "-" + dia;
    }

    public static String redondeaDoubleStrin(Double monto) {
        BigDecimal bd = new BigDecimal(monto).setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

}
