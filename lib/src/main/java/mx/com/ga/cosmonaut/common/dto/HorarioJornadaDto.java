package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoJornada;
import mx.com.ga.cosmonaut.common.entity.cliente.NclJornada;


@Data
@Introspected
public class HorarioJornadaDto {
    
    private Integer horarioJornadaId;
    private Integer dia;
    private String horaEntrada;
    private String horaInicioComida;
    private String horaFinComida;
    private String horaSalida;
    private Boolean esActivo;
    private NclJornada nclJornada;
    private CsTipoJornada tipoJornadaId;
    
}
