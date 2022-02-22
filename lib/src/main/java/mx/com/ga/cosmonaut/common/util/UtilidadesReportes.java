package mx.com.ga.cosmonaut.common.util;

import mx.com.ga.cosmonaut.common.dto.RespuestaGenerica;
import mx.com.ga.cosmonaut.common.exception.ServiceException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class UtilidadesReportes {

    private UtilidadesReportes() {
        super();
    }

    public static String generaPeriodo(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfPeriodo = new SimpleDateFormat("ddd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String fechaInicio = sdf.format(calendar.getTime());
        String fechaPeriodo = sdfPeriodo.format(calendar.getTime());
        calendar.set(Calendar.MONTH,Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        String fechaFinal =  sdf.format(calendar.getTime());
        return "Período " + fechaPeriodo + ": " + fechaInicio + " al " + fechaFinal;
    }

    public static Object tipoCelda(Cell celda){
        switch (celda.getCellType()) {
            case BOOLEAN:
                return celda.getBooleanCellValue();
            case STRING:
                return celda.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(celda)) {
                    return celda.getDateCellValue();
                } else {
                    return celda.getNumericCellValue();
                }
            case FORMULA:
                return celda.getCellFormula();
            default:
                return "";
        }
    }

    public static String generaMensajeError(String campo, RespuestaGenerica respuesta, String mensaje){
        if (!respuesta.isResultado()){
            if (mensaje == null){
                mensaje = "";
            }
            mensaje += campo + ": " + respuesta.getMensaje() + " \n"  ;
        }
        return mensaje;
    }

    public static Boolean validaContenidoLista(List<?> lista) {
        return lista != null && !lista.isEmpty();
    }

    public static void crearTitulosListasDinamicas(List<String> listaValores,
                                                   AtomicReference<XSSFRow> headerfila, AtomicInteger i,
                                                   XSSFWorkbook workbook, XSSFSheet hojaActual) throws ServiceException {
        try {
            if (Boolean.TRUE.equals(validaContenidoLista(listaValores))) {
                listaValores
                        .stream()
                        .forEach(a -> {
                            XSSFCell cell = headerfila.get().createCell(i.get());
                            cell.setCellValue(a);
                            cell.setCellStyle(crearEstiloCeldas(workbook));
                            hojaActual.setColumnWidth(i.get(), 8000);
                            i.getAndIncrement();
                        });
            }
        } catch (Exception ex) {
            throw new ServiceException(mx.com.ga.cosmonaut.common.util.Constantes.ERROR_CLASE +
                    UtilidadesReportes.class.getSimpleName()
                    + mx.com.ga.cosmonaut.common.util.Constantes.ERROR_METODO +
                    " crearTitulosListasDinamicas " + mx.com.ga.cosmonaut.common.util.Constantes.ERROR_EXCEPCION, ex);
        }
    }

    public static XSSFCellStyle crearEstiloCeldas(XSSFWorkbook workbook) {
        XSSFCellStyle xssfCellStyle = workbook.createCellStyle();
        XSSFFont fuente = workbook.createFont();
        fuente.setBold(true);
        /**color*/
        XSSFColor color = new XSSFColor(new java.awt.Color(37,222,212),null);
        xssfCellStyle.setFillForegroundColor(color);
        xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        xssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        xssfCellStyle.setLocked(true);
        xssfCellStyle.setWrapText(true);
        xssfCellStyle.setFont(fuente);
        return xssfCellStyle;
    }

    public static String validaCadenaComplementaria(String valor, Integer longitud, Integer lado, Integer ceros) throws ServiceException{
        try {
            valor = validaContenido(valor);

            if (valor.length() > longitud) {
                valor = valor.substring(0,longitud);
            }

            if (valor.length() < longitud) {

                /** lado derecho.*/
                if (lado == 1) {
                    valor = String.format("%-".concat(String.valueOf(longitud).concat("s")) , valor);
                } else { /**lado izquierdo*/
                    valor = String.format("%".concat(longitud.toString()).concat("s"), valor);
                }
                if (ceros == 1) {
                    valor = valor.replace(" ", "0");
                }

            }
        } catch (Exception ex) {
            throw new ServiceException(mx.com.ga.cosmonaut.common.util.Constantes.ERROR_CLASE +
                    UtilidadesReportes.class.getSimpleName()
                    + mx.com.ga.cosmonaut.common.util.Constantes.ERROR_METODO + " validaCadena " + mx.com.ga.cosmonaut.common.util.Constantes.ERROR_EXCEPCION, ex);
        }
        return valor;
    }

    public static String validaContenido(String valor) throws ServiceException {
        try {
            return (Boolean.TRUE.equals(validaCadena(valor)) ? valor : "");
        } catch (Exception ex) {
            throw new ServiceException(mx.com.ga.cosmonaut.common.util.Constantes.ERROR_CLASE +
                    UtilidadesReportes.class.getSimpleName()
                    + mx.com.ga.cosmonaut.common.util.Constantes.ERROR_METODO + " validaContenido " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    public static Boolean validaCadena(String cadena) {
        return (cadena != null && !cadena.isEmpty() && !cadena.equalsIgnoreCase("null")
                ? Boolean.TRUE : Boolean.FALSE);
    }

}
