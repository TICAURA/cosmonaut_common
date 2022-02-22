package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "cs_regimen_fiscal")
public class CsRegimenFiscal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @MappedProperty(value = "c_regimenfiscal")
    private String regimenfiscalId;
    @MappedProperty(value = "descripcion")
    private String descripcion;
    @MappedProperty(value = "ind_persona_fisica")
    private Boolean indPersonaFisica;
    @MappedProperty(value = "ind_persona_moral")
    private Boolean indPersonaMoral;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fec_inicio")
    private Date fecInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fec_fin")
    private Date fecFin;
    @MappedProperty(value = "es_activo")
    private Boolean activo;

}
