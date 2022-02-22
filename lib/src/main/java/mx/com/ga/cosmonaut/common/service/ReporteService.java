package mx.com.ga.cosmonaut.common.service;

import mx.com.ga.cosmonaut.common.exception.ServiceException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.w3c.dom.Document;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;

public interface ReporteService {

    String generaReportePDF(JasperPrint reporte) throws ServiceException;

    String generaReporteExcel(JasperPrint reporte, String[] nombreHoja) throws ServiceException;

    JasperPrint generaJasper(InputStream reporteJasper, HashMap<String, Object> parametros) throws ServiceException;

    boolean generaReportePdfRuta(JasperPrint reporte, String ruta) throws ServiceException;

    boolean generaReporteXlsRuta(JasperPrint reporte, String ruta, String[] nombreHoja) throws ServiceException;

    JasperPrint generaJasper(InputStream reporteJasper,HashMap<String, Object> parametros, JRBeanCollectionDataSource datos)
            throws ServiceException;

    void generaXMLRuta(Document cfdi, String ruta) throws ServiceException;

    StringWriter generaXML(Document cfdi, StringWriter escritor) throws ServiceException;

}
