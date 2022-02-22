package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatFacultadPoder;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatParentesco;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatNacionalidad;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoPersona;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoRepresentante;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "nco_persona")
public class NcoPersona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @MappedProperty(value = "iba_num_hijos")
    private Integer numeroHijos;

    @MappedProperty(value = "ce_telefono")
    private Long contactoEmergenciaTelefono;
    @MappedProperty(value = "ci_telefono")
    private Long contactoInicialTelefono;
    @MappedProperty(value = "ci_extension")
    private Long contactoInicialExtension;
    @MappedProperty(value = "celular")
    private BigInteger celular;

    @MappedProperty(value = "curp")
    private String curp;
    @MappedProperty(value = "rfc")
    private String rfc;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "apellido_pat")
    private String apellidoPaterno;
    @MappedProperty(value = "apellido_mat")
    private String apellidoMaterno;
    @MappedProperty(value = "email_corp")
    private String emailCorporativo;
    @MappedProperty(value = "genero")
    private String genero;
    @MappedProperty(value = "nss")
    private String nss;
    @MappedProperty(value = "ce_nombre")
    private String contactoEmergenciaNombre;
    @MappedProperty(value = "ce_e_mail")
    private String contactoEmergenciaEmail;
    @MappedProperty(value = "rl_url_firma")
    private String urlFirma;
    @MappedProperty(value = "ci_email_personal")
    private String contactoInicialEmailPersonal;
    @MappedProperty(value = "ce_apellido_paterno")
    private String contactoEmergenciaApellidoPaterno;
    @MappedProperty(value = "ce_apellido_materno")
    private String contactoEmergenciaApellidoMaterno;
    @MappedProperty(value = "iba_estado_civil")
    private String estadoCivil;
    @MappedProperty(value = "url_linkedin")
    private String urlLinkedin;
    @MappedProperty(value = "poder_notarial")
    private String poderNotarial;
    @MappedProperty(value = "ci_puesto")
    private String contactoInicialPuesto;
    @MappedProperty(value = "url_imagen")
    private String urlImagen;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_nace")
    private Date fechaNacimiento;
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Timestamp fechaAlta;

    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "tiene_curp")
    private boolean tieneCurp;
    @MappedProperty(value = "tiene_rfc")
    private boolean tieneRfc;
    @MappedProperty(value = "tiene_nss")
    private boolean tieneNss;
    @MappedProperty(value = "iba_tiene_hijos")
    private boolean tieneHijos;
    @MappedProperty(value = "invitar_empleado")
    private boolean invitarEmpleado;

    @MappedProperty(value = "iba_nacionalidad_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "iba_nacionalidad_id")
    private CatNacionalidad nacionalidadId;
    @MappedProperty(value = "tipo_persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_persona_id")
    private CatTipoPersona tipoPersonaId;
    @MappedProperty(value = "tipo_representante_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_representante_id")
    private CatTipoRepresentante tipoRepresentanteId;
    @MappedProperty(value = "representante_legal_centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "representante_legal_centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "ce_parentesco_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "ce_parentesco_id")
    private CatParentesco parentescoId;
    @MappedProperty(value = "facultad_poder_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "facultad_poder_id")
    private CatFacultadPoder facultadPoderId;

}
