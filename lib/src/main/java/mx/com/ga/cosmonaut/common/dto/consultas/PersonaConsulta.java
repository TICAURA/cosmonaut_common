package mx.com.ga.cosmonaut.common.dto.consultas;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatFacultadPoder;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatNacionalidad;

import java.sql.Timestamp;

@Data
@Introspected
public class PersonaConsulta {

    private Long personaId;
    private Long centrocClienteId;
    private Long contactoInicialTelefono;
    private Long contactoInicialExtension;
    private Long celular;
    private Long nacionalidad;
    private Long facultadPoder;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String contactoInicialEmailPersonal;
    private String rfc;
    private String curp;
    private String emailCorporativo;
    private String razonSocial;
    private String contactoInicialPuesto;
    private String poderNotarial;

    private Timestamp fechaAlta;

    private Boolean activo;

    private CatNacionalidad nacionalidadId;
    private CatFacultadPoder facultadPoderId;


}
