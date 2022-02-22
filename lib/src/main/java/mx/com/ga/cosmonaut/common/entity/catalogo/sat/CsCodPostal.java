package mx.com.ga.cosmonaut.common.entity.catalogo.sat;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Data
@MappedEntity(value = "cs_cod_postal")
public class CsCodPostal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    
    @MappedProperty(value = "codigo_postal")
    private String codigoPostal;
    @MappedProperty(value = "estimulo_franja_fronteriza")
    private Integer estimuloFranjaFronteriza;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @MappedProperty(value = "descripcion_huso_horario")
    private String descripcionHusoHorario;
    @MappedProperty(value = "mes_inicio_horario_verano")
    private String mesInicioHorarioVerano;
    @MappedProperty(value = "dia_inicio_horario_verano")
    private String diaInicioHorarioVerano;
    @MappedProperty(value = "hora_inicio_horario_verano")
    private String horaInicioHorarioVerano;
    @MappedProperty(value = "diferencia_horaria_verano")
    private BigInteger diferenciaHorariaVerano;
    @MappedProperty(value = "mes_inicio_horario_invierno")
    private String mesInicioHorarioInvierno;
    @MappedProperty(value = "dia_inicio_horario_invierno")
    private String diaInicioHorarioInvierno;
    @MappedProperty(value = "hora_inicio_horario_invierno")
    private String horaInicioHorarioInvierno;
    @MappedProperty(value = "diferencia_horaria_invierno")
    private BigInteger diferenciaHorariaInvierno;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "estado_id")
    @MappedProperty(value = "estado_id")
    private CsEstado estadoId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "localidad_id")
    @MappedProperty(value = "localidad_id")
    private CsLocalidad localidadId;
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "municipio_id")
    @MappedProperty(value = "municipio_id")
    private CsMunicipio municipioId;
    
    
}
