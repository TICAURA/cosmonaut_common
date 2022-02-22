package mx.com.ga.cosmonaut.common.dto.reportes;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Introspected
public class EmpleadoDto {

    private String id;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String resgistroPatronal;
    private String area;
    private String puesto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaInicioContrato;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAntiguedad;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaBaja;
    private String estatus;
    private String tipoEmpleado;
    private String tipoContrato;
    private String grupoNomina;
    private String politicas;
    private String jornada;
    private boolean sindicalizado;
    private String rfc;
    private String curp;
    private String seguroSocial;
    private BigDecimal sueldoBrutoMensual;
    private BigDecimal salarioDiarioIntegrado;
    private String salarioBaseCotizacion;
    private String tipoCompensacion;
    private String estadoRepublica;
    private String areaGeofrafica;
    private String metodoPago;
    private String banco;
    private String numeroCuenta;
    private String clabe;
    private String numeroCliente;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String genero;
    private String direccion;
    private Long telefono;
    private String emailPersonal;
    private String emailCorporativo;
    private String estadoCivil;
    private Integer numeroHijos;
    private String reporta;
    private String dulceSalado;
    private String cafeTe;
    private String bebida;
    private String dulce;
    private String color;
    private String alergias;
    private String emailParentesco;
    private String nombrePerfilLinkedin;
    private String alimentacion;
    private Long celular;
    private boolean tieneCurp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaVencimiento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaEmision;
    private String sincronizarEmailSlack;
    private boolean empleadoSubContratado;
    private String departamento;
    private String nivel;
    private String categoria;
    private String razonSocial;
    private String discapacidades;
    private String empresa;
    private String empleoAnterior;
    private String nombreEmpresaAnterior;
    private String antecedentesMedicos;
    private String detalleSubContratacion;

}
