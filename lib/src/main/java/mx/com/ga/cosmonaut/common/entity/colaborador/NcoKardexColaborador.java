package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "nco_kardex_colaborador")
public class NcoKardexColaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "kardex_colaborador_id")
    private Integer kardexColaboradorId;
    @MappedProperty(value = "movimiento_imss_id")
    private Integer movimientoImssId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "es_imss")
    private boolean esImss;
    @DateCreated
    @MappedProperty(value = "fecha_movimiento")
    private Timestamp fechaMovimiento;
    @MappedProperty(value = "persona_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "persona_id")
    private NcoPersona personaId;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "historico_compensacion_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "historico_compensacion_id")
    private NcoHistoricoCompensacion historicoCompensacionId;

}
