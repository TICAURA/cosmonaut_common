package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEstatusIdse;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "nco_kardex_estatus_idse")
public class NcoKardexEstatusIdse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @MappedProperty(value = "kardex_colaborador_id")
    private Long kardexColaboradorId;

    @MappedProperty(value = "id_proceso")
    private String idProceso;

    @MappedProperty(value = "estatus_idse_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "estatus_idse_id")
    private CatEstatusIdse estatusIdseId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_estatus")
    private Date fechaEstatus;

    @MappedProperty(value = "numero_lote")
    private String numeroLote;

    @MappedProperty(value = "referencia_acuse")
    private String referenciaAcuse;

    @MappedProperty(value = "referencia_patronal")
    private String referenciaPatronal;

}
