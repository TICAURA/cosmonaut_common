package mx.com.ga.cosmonaut.common.dto.consultas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.Date;

@Introspected
@Data
public class TimbradoConsulta {

    private Integer nominaXperiodoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaContratoNogrupo;
    private Integer centrocClienteId;
    private Integer personaId;
    private String subTotal;
    private String descuento;
    private String moneda;
    private String total;
    private String tipoDeComprobante;
    private String metodoPago;
    private String lugarExpedicion;
    private String emisorRfc;
    private String emisorNombre;
    private String emisorRegimenFiscal;
    private String emisorCurp;
    private String receptorRfc;
    private String receptorNombre;
    private String receptorUsoCFDI;
    private String claveProdServ;
    private String cantidad;
    private String claveUnidad;
    private String descripcion;
    private String valorUnitario;
    private String importe;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFinalPago;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaInicialPago;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaPago;
    private String numDiasPagados;
    private String tipoNomina;
    private String totalDeducciones;
    private String totalOtrosPagos;
    private String totalPercepciones;
    private String version;
    private String registroPatronal;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date antiguedad;
    private String claveEntFed;
    private String cuentaBancaria;
    private String curp;
    private String departamento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaInicioRelLaboral;
    private String numEmpleado;
    private String numSeguridadSocial;
    private String periodicidadPago;
    private String puesto;
    private String riesgoPuesto;
    private String salarioBaseCotApor;
    private String salarioDiarioIntegrado;
    private boolean sindicalizado;
    private String tipoContrato;
    private String tipoJornada;
    private String tipoRegimen;
    private String totalExento;
    private String totalGravado;
    private String totalSueldos;
    private Integer metodoPagoId;
    private String zona;
    private String diasIncapacidad;
    private String tipoIncapacidadId;

}
