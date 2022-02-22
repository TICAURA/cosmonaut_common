package mx.com.ga.cosmonaut.common.dto.consultas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Introspected
public class IncidenciasConsulta {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroEmpleado;
    private String incidenciaDescripcion;
    private String politicaNombre;
    private String comentarios;
    private String comentarioAcepta;
    private String estadoDescripcion;
    private String areaDescripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAplicacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaContrato;
    private Integer tiempo;
    private Integer clienteId;
    private Integer personaId;
    private Integer politicaId;
    private Integer incidenciaId;
    private Integer tipoIncidenciaId;
    private Integer estadoId;
    private Integer areaId;
    private Integer duracion;
    private Boolean esActivo;
    private BigDecimal monto;
    private Integer unidadMedidaId;
    private Integer heTiempo;
}
