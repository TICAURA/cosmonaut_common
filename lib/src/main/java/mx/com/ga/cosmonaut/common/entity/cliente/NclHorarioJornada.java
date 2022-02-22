package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoJornada;

@Data
@MappedEntity(value = "ncl_horario_jornada")
public class NclHorarioJornada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "horario_jornada_id")
    private Integer horarioJornadaId;
    @MappedProperty(value = "dia")
    private Integer dia;
    @MappedProperty(value = "hora_entrada")
    private Timestamp horaEntrada;
    @MappedProperty(value = "hora_inicio_comida")
    private Timestamp horaInicioComida;
    @MappedProperty(value = "hora_fin_comida")
    private Timestamp horaFinComida;
    @MappedProperty(value = "hora_salida")
    private Timestamp horaSalida;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "jornada_id")
    @MappedProperty(value = "id_jornada")
    private NclJornada nclJornada;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_jornada_id")
    @MappedProperty(value = "tipo_jornada_id")
    private CsTipoJornada tipoJornadaId;

    
}
