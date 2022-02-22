package mx.com.ga.cosmonaut.common.entity.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@MappedEntity("ncl_registro_patronal")
public class NclRegistroPatronal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "registro_patronal_id")
    private Integer registroPatronalId;
    @MappedProperty(value = "registro_patronal")
    private String registroPatronal;
    @MappedProperty(value = "em_prima_riesgo")
    private BigDecimal emPrimaRiesgo;
    @MappedProperty(value = "em_clave_delegacional_imss")
    private Integer emClaveDelegacionalImss;
    @MappedProperty(value = "em_imss_obrero_integrado_apatronal")
    private Boolean emImssObreroIntegradoApatronal;
    @MappedProperty(value = "em_enviar_movs_imss")
    private Boolean emEnviarMovsImss;
    @MappedProperty(value = "em_via_envio_movs_imss")
    private Boolean emViaEnvioMovsImss;
    @MappedProperty(value = "em_calculo_auto_promedio_var")
    private boolean emCalculoAutoPromedioVar;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "centroc_cliente_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;

    // TECTEL
    @MappedProperty(value = "credenciales_imss_id")
    private String credencialesImssId;

}
