package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import java.sql.Date;
import java.util.List;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatSumaHorasJornada;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoJornada;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

@Data
@Introspected
public class JornadasDto {
    
    private Integer jornadaId;
    private CsTipoJornada tipoJornadaId;
    private String nombre;
    private Boolean mismoHorario;
    private Boolean horarioComida;
    private Boolean incidirAsistencias;
    private CatSumaHorasJornada sumaHorasJornadaId;
    private String horaEntrada;
    private String horaInicioComida;
    private String horaFinComida;
    private String horaSalida;
    private Boolean esActivo;
    private NclCentrocCliente centrocClienteId;
    private Integer anMinutosTolerancia;
    private Boolean anHayTolerancia;
    private Boolean registroPrimaDominicalAuto;
    private Boolean registroDescansoLaboralAuto;
    private Boolean anPermiteJustificarRetardo;
    private String soloUnRegistroDia;
    private String paraDiasParcialmenteLaborados;
    private Boolean heSolicitudHorasExtra;
    private Integer heMinutos; 
    private List<HorarioJornadaDto> nclHorarioJornada;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;    
    private Integer count;
    
}
