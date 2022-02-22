package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatBasePeriodo;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsActividadEconomica;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsRegimenFiscal;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "ncl_centroc_cliente")
public class NclCentrocCliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;

    @MappedProperty(value = "curp")
    private String curp;
    @MappedProperty(value = "rfc")
    private String rfc;
    @MappedProperty(value = "razon_social")
    private String razonSocial;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "certificado_sello_digital_id")
    private String certificadoSelloDigitalId;
    @MappedProperty(value = "cc_url_logo")
    private String urlLogo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_constitucion")
    private Date fechaConstitucion;

    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Timestamp fechaAlta;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "multiempresa")
    private boolean multiempresa;
    @MappedProperty(value = "pago_complementario")
    private boolean pagoComplementario;

    @MappedProperty(value = "em_c_regimenfiscal")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "em_c_regimenfiscal")
    private CsRegimenFiscal regimenfiscalId;
    @MappedProperty(value = "base_periodo_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "base_periodo_id")
    private CatBasePeriodo basePeriodoId;
    @MappedProperty(value = "c_actividad_economica")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "c_actividad_economica")
    private CsActividadEconomica actividadEconomicaId;
    @MappedProperty(value = "centro_costos_centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centro_costos_centroc_cliente_id")
    private NclCentrocCliente centroCostosCentrocClienteId;
}
