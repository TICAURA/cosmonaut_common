package mx.com.ga.cosmonaut.common.entity.cliente;

import io.micronaut.data.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatSumaHorasJornada;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoJornada;

@Data
@MappedEntity(value = "ncl_jornada")
public class NclJornada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "jornada_id")
    private Integer jornadaId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="tipo_jornada_id")
    @MappedProperty(value = "tipo_jornada_id")
    private CsTipoJornada tipoJornadaId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "mismo_horario")
    private Boolean mismoHorario;
    @MappedProperty(value = "horario_comida")
    private Boolean horarioComida;
    @MappedProperty(value = "incidir_asistencias")
    private Boolean incidirAsistencias;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="suma_horas_jornada_id")
    @MappedProperty(value = "suma_horas_jornada_id")
    private CatSumaHorasJornada sumaHorasJornadaId;
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
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy="centroc_cliente_id")
    @MappedProperty(value = "centroc_cliente_id")
    private NclCentrocCliente centrocClienteId;
    @MappedProperty(value = "an_minutos_tolerancia")
    private Integer anMinutosTolerancia;
    @MappedProperty(value = "an_hay_tolerancia")
    private Boolean anHayTolerancia;
    @MappedProperty(value = "registro_prima_dominical_auto")
    private Boolean registroPrimaDominicalAuto;
    @MappedProperty(value = "registro_descanso_laboral_auto")
    private Boolean registroDescansoLaboralAuto;
    @MappedProperty(value = "an_permite_justificar_retardo")
    private Boolean anPermiteJustificarRetardo;
    @MappedProperty(value = "solo_un_registro_dia")
    private String soloUnRegistroDia;
    @MappedProperty(value = "para_dias_parcialmente_laborados")
    private String paraDiasParcialmenteLaborados;
    @MappedProperty(value = "he_solicitud_horas_extra")
    private Boolean heSolicitudHorasExtra;
    @MappedProperty(value = "he_minutos")
    private Integer heMinutos; 
    
}
