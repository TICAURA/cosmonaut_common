package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoIncapacidad;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoIncidencia;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatUnidad;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstadoIncidencia;
import mx.com.ga.cosmonaut.common.entity.colaborador.servicios.NscCargaIncidencias;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
public class NscIncidenciaDto {

    private Integer incidenciaId;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private Integer duracion;
    private Date fechaContrato;
    private Integer personaId;
    private Integer clienteId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ultimaActualizacion;
    private String comentarios;
    private Integer heTiempo;
    private Integer pagarPor;
    private BigDecimal monto;
    private String comentarioAceptaRechza;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAplicacion;
    private String numeroFolio;
    private String urlArchivo;
    private String urlDocumento;
    private String nombreArchivo;
    private boolean esActivo;

    private CatEstadoIncidencia ultimoEstadoIncidenciaId;
    private CatTipoIncapacidad tipoIncapacidadId;
    private CatTipoIncidencia tipoIncidenciaId;
    private CatUnidad unidadMedidaId;
    private NscCargaIncidencias cargaIncidenciasId;

    private byte[] archivo;

}
