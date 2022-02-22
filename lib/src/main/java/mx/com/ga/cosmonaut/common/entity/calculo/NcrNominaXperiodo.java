package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclGrupoNomina;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_nomina_xperiodo")
public class NcrNominaXperiodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "nomina_xperiodo_id")
    private Integer nominaXperiodoId;
    @MappedProperty(value = "es_extraordinaria")
    private boolean esExtraordinaria;
    @MappedProperty(value = "moneda_id")
    private int monedaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio_incidencias")
    private Date fechaInicioIncidencias;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin_incidencias")
    private Date fechaFinIncidencias;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "num_empleados")
    private int numEmpleados;
    @MappedProperty(value = "total_neto")
    private BigDecimal totalNeto;
    @MappedProperty(value = "es_activo")
    private Boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_gral_timbrado")
    private Date fechaGralTimbrado;
    @MappedProperty(value = "total_percepciones")
    private BigDecimal totalPercepciones;
    @MappedProperty(value = "total_deducciones")
    private BigDecimal totalDeducciones;

    @MappedProperty(value = "provision_isn")
    private BigDecimal provisionIsn;
    @MappedProperty(value = "provision_vacaciones")
    private BigDecimal provisionVacaciones;
    @MappedProperty(value = "provision_imss_patronal")
    private BigDecimal provisionImssPatronal;
    @MappedProperty(value = "provision_prima_vacacional")
    private BigDecimal provisionPrimaVacacional;
    @MappedProperty(value = "provision_aguinaldo")
    private BigDecimal provisionAguinaldo;
    @MappedProperty(value = "nombre_nomina")
    private String nombreNomina;

    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "cuenta_banco_id")
    private Integer cuentaBancoId;
    @MappedProperty(value = "tipo_nomina_id")
    private Integer tipoNominaId;
    @MappedProperty(value = "clave_periodo")
    private String clavePeriodo;
    @MappedProperty(value = "estado_nomina_id_actual")
    private Integer estadoNominaIdActual;
    @MappedProperty(value = "anio_fiscal")
    private Integer anioFiscal;
    @MappedProperty(value = "total_exento")
    private BigDecimal totalExento;
    @MappedProperty(value = "total_gravable")
    private BigDecimal totalGravable;


    @MappedProperty(value = "grupo_nomina_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "grupo_nomina_id")
    private NclGrupoNomina grupoNominaId;

}
