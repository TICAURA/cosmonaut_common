package mx.com.ga.cosmonaut.common.service.impl;

import mx.com.ga.cosmonaut.common.exception.ServiceException;
import mx.com.ga.cosmonaut.common.service.ReporteService;
import mx.com.ga.cosmonaut.common.util.Constantes;
import mx.com.ga.cosmonaut.common.util.Utilidades;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.w3c.dom.Document;

import javax.inject.Singleton;
import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;

@Singleton
public class ReporteServiceImpl implements ReporteService {

    @Override
    public String generaReportePDF(JasperPrint reporte) throws ServiceException {
        try {
            ByteArrayOutputStream salida = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(reporte));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(salida));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            return Utilidades.encodeContent(salida.toByteArray());
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaReportePDF " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public String generaReporteExcel(JasperPrint reporte, String[] nombreHoja) throws ServiceException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = generaJasperXls(reporte, nombreHoja);
            return Utilidades.encodeContent(byteArrayOutputStream.toByteArray());
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaReporteExcel " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public JasperPrint generaJasper(InputStream reporteJasper, HashMap<String, Object> parametros) throws ServiceException {
        try {
         return  JasperFillManager.fillReport(
                JasperCompileManager.compileReport(reporteJasper), parametros, new JREmptyDataSource());
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaJasperParametros " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public boolean generaReportePdfRuta(JasperPrint reporte, String ruta) throws ServiceException {
        try {
            JasperExportManager.exportReportToPdfFile(reporte, ruta);
            return true;
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaReportePdfRuta " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public boolean generaReporteXlsRuta(JasperPrint reporte, String ruta, String[] nombreHoja) throws ServiceException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = generaJasperXls(reporte, nombreHoja);
            File outputFile = new File(ruta);
            OutputStream fileOutputStream = new FileOutputStream(outputFile);
            byteArrayOutputStream.writeTo(fileOutputStream);
            return true;
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaReporteXlsRuta " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public JasperPrint generaJasper(InputStream reporteJasper,HashMap<String, Object> parametros, JRBeanCollectionDataSource datos)
            throws ServiceException {
        try {
            return JasperFillManager.fillReport(
                    JasperCompileManager.compileReport(reporteJasper), parametros, datos);
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaJasper " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public void generaXMLRuta(Document cfdi, String ruta) throws ServiceException {
        try {
            Transformer transformador = generaFabrica();
            DOMSource source = new DOMSource(cfdi);
            StreamResult result = new StreamResult(new File(ruta));
            transformador.transform(source, result);
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaXMLRuta " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    @Override
    public StringWriter generaXML(Document cfdi, StringWriter escritor) throws ServiceException {
        try {
            Transformer transformador = generaFabrica();
            transformador.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformador.setOutputProperty(OutputKeys.METHOD, "xml");
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformador.transform(new DOMSource(cfdi), new StreamResult(escritor));
            return escritor;
        } catch (Exception ex) {
            throw new ServiceException(Constantes.ERROR_CLASE + this.getClass().getSimpleName()
                    + Constantes.ERROR_METODO +" generaXML " + Constantes.ERROR_EXCEPCION, ex);
        }
    }

    private Transformer generaFabrica() throws TransformerConfigurationException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        return factory.newTransformer();
    }

    private ByteArrayOutputStream generaJasperXls(JasperPrint reporte, String[] nombreHoja) throws JRException {
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setIgnoreGraphics(false);
        configuration.setWhitePageBackground(false);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setSheetNames(nombreHoja);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(reporte));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        return byteArrayOutputStream;
    }


}
