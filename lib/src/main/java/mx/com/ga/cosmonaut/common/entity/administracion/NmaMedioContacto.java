package mx.com.ga.cosmonaut.common.entity.administracion;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.*;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoContacto;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "nma_medio_contacto")
public class NmaMedioContacto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @MappedProperty(value = "numero_ocuenta")
    private String numeroOcuenta;
    @MappedProperty(value = "url")
    private String url;
    @MappedProperty(value = "es_principal")
    private boolean esPrincipal;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    @DateCreated
    private Date fechaAlta;

    @MappedProperty(value = "tipo_contacto_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_contacto_id")
    private CatTipoContacto tipoContactoId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;
    
}
