package mx.com.ga.cosmonaut.common.entity.colaborador;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import io.micronaut.data.annotation.Relation;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatEstatusIdse;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "nco_bitacora_kardex_estatus_idse")
public class NcoBitacoraKardexEstatusIdse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "bitacora_kardex_estatus_id")
    private Long bitacoraKardexEstatusId;

    @MappedProperty(value = "kardex_colaborador_id")
    private Long kardexColaboradorId;

    @MappedProperty(value = "id_proceso")
    private String idProceso;

    @MappedProperty(value = "estatus_idse_id")
    private Long estatusIdseId;

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
