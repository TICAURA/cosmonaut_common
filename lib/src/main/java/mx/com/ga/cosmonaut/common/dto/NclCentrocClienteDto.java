package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaDomicilio;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatBasePeriodo;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsActividadEconomica;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsRegimenFiscal;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class NclCentrocClienteDto {

    private Integer centrocClienteId;
    private Integer claveDelegacionalImss;
    private Integer cuentaClabeStp;

    private BigDecimal primaRiesgo;

    private String curp;
    private String rfc;
    private String razonSocial;
    private String nombre;
    private String urlLogo;
    private String cuentaStp;
    private String contrasenia;
    private String certificadoSelloDigitalId;
    private Timestamp fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaConstitucion;

    private Boolean esActivo;
    private Boolean horasExtrasAuto;
    private Boolean calculoAutoPromedioVar;
    private Boolean usaStp;
    private Boolean imssObreroIntegradoApatronal;
    private Boolean enviarMovimientosImss;
    private Boolean viaEnvioMovimientosImss;
    private Boolean imssObreroIntegradoPatronal;
    private Boolean multiempresa;
    private Boolean pagoComplementario;

    private boolean cerKeyConstrasenia;

    private CsRegimenFiscal regimenfiscalId;
    private NclCentrocCliente centroCostosCentrocClienteId;
    private NmaDomicilio domicilio;
    private CatBasePeriodo basePeriodoId;
    private CsActividadEconomica actividadEconomicaId;
    private CsActividadEconomica padreActividadEconomicaId;

    private byte[] imagen;
    private byte[] cer;
    private byte[] key;

    private URL url;
}
