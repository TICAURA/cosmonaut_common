package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaDomicilio;
import mx.com.ga.cosmonaut.common.entity.administracion.NmaMedioContacto;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatFacultadPoder;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatParentesco;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoPersona;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoRepresentante;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatNacionalidad;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoContratoColaborador;

import java.math.BigInteger;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class NcoPersonaDto {

    private Integer personaId;
    private Integer numeroHijos;

    private Long contactoEmergenciaTelefono;
    private Long contactoInicialTelefono;
    private Long contactoInicialExtension;

    private BigInteger celular;

    private String curp;
    private String rfc;
    private String nombre;
    private String segundoNombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String emailCorporativo;
    private String genero;
    private String nss;
    private String estadoCivil;
    private String contactoEmergenciaNombre;
    private String contactoEmergenciaParentesco;
    private String contactoEmergenciaEmail;
    private String urlFirma;
    private String contactoInicialEmailPersonal;
    private String contactoEmergenciaApellidoPaterno;
    private String contactoEmergenciaApellidoMaterno;
    private String urlLinkedin;
    private String poderNotarial;
    private String contactoInicialPuesto;
    private String urlImagen;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private Timestamp fechaAlta;
    private Boolean esActivo;
    private boolean tieneCurp;
    private boolean tieneRfc;
    private boolean tieneNss;
    private boolean tieneHijos;
    private boolean invitarEmpleado;

    private CatNacionalidad nacionalidadId;
    private CatTipoPersona tipoPersonaId;
    private CatTipoRepresentante tipoRepresentanteId;
    private NclCentrocCliente centrocClienteId;
    private NmaDomicilio domicilio;
    private NmaMedioContacto medioContacto;
    private NcoContratoColaborador contratoColaborador;
    private CatParentesco parentescoId;
    private CatFacultadPoder facultadPoderId;

    private byte[] imagen;
    private URL url;

}