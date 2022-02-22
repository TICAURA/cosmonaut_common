package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncl_colaborador_xgrupo_nomina")
public class NclColaboradorXgrupoNomina implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;

    @Id
    @MappedProperty(value = "persona_id")
    private int personaId;

    @MappedProperty(value = "centroc_cliente_id")
    private int centrocClienteId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;

    @MappedProperty(value = "grupo_nomina_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "grupo_nomina_id")
    private NclGrupoNomina grupoNominaId;

}
