package mx.com.ga.cosmonaut.common.entity.temporal;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.Data;
import java.math.BigInteger;
import java.util.Date;

@Data
@MappedEntity(value = "tmp_carga_colaborador")
public class CargaMasivaEmpleado {

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "tmp_carga_colaborador_id")
    private Long tmpCargaColaboradorId;
    @MappedProperty(value = "numeroempleado")
    private String numeroEmpleado;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "apellidopaterno")
    private String apellidoPaterno;
    @MappedProperty(value = "apellidomaterno")
    private String apellidoMaterno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fechanacimiento")
    private Date fechaNacimiento;
    @MappedProperty(value = "genero")
    private String genero;
    @MappedProperty(value = "rfc")
    private String rfc;
    @MappedProperty(value = "curp")
    private String curp;
    @MappedProperty(value = "nss")
    private String nss;
    @MappedProperty(value = "contactoinicialemailpersonal")
    private String contactoInicialEmailPersonal;
    @MappedProperty(value = "emailcorporativo")
    private String emailCorporativo;
    @MappedProperty(value = "celular")
    private BigInteger celular;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fechaantiguedad")
    private Date fechaAntiguedad;
    @MappedProperty(value = "areaid")
    private Integer areaId;
    @MappedProperty(value = "puestoid")
    private Integer puestoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fechainicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fechafin")
    private Date fechaFin;
    @MappedProperty(value = "estadoid")
    private Integer estadoId;
    @MappedProperty(value = "tiporegimencontratacionid")
    private Integer tipoRegimenContratacionId;
    @MappedProperty(value = "tipoContratoid")
    private Integer tipoContratoId;
    @MappedProperty(value = "politicaid")
    private Integer politicaId;
    @MappedProperty(value = "essindicalizado")
    private Boolean esSindicalizado;
    @MappedProperty(value = "areaGeograficaid")
    private Integer areaGeograficaId;
    @MappedProperty(value = "jornadaid")
    private Integer jornadaId;
    @MappedProperty(value = "tipo_jornada_id")
    private Integer tipoJornadaId;
    @MappedProperty(value = "tipocompensacionid")
    private Integer tipoCompensacionId;
    @MappedProperty(value = "sueldobrutomensual")
    private Double sueldoBrutoMensual;
    @MappedProperty(value = "sueldonetomensual")
    private Double sueldoNetoMensual;
    @MappedProperty(value = "salario_diario")
    private Double salarioDiario;
    /**Falta el campo de PPP*/
    @MappedProperty(value = "grupoNominaid")
    private Integer grupoNominaId;
    @MappedProperty(value = "metodoPagoid")
    private Integer metodoPagoId;
    @MappedProperty(value = "bancoid")
    private Integer bancoId;
    @MappedProperty(value = "numerocuenta")
    private String numeroCuenta;
    @MappedProperty(value = "clabe")
    private String clabe;
    @MappedProperty(value = "diasvacaciones")
    private Integer diasVacaciones;
    @MappedProperty(value = "numinformacion")
    private String numeroInformacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_baja")
    private Date fechaBaja;
    @MappedProperty(value = "escorrecto")
    private boolean esCorrecto;
    @MappedProperty(value = "errores")
    private String errores;
    @MappedProperty(value = "centrocClienteId")
    private Integer centrocClienteId;
    @MappedProperty(value = "sbc")
    private Double sbc;

}
