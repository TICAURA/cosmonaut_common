package mx.com.ga.cosmonaut.common.dto.administracion.noticias;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.administracion.noticias.AdmCategoriaNoticias;

import java.net.URL;
import java.util.Date;
import java.util.Set;

@Data
public class AdmNoticiasDto {

    private Integer noticiaId;
    private Integer usuarioId;
    private String titulo;
    private String subtitulo;
    private AdmCategoriaNoticias categoriaId;
    private String contenido;
    private URL thumbnail;
    private byte[] imagen;
    private String rutaBucket;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC-5", timezone = "UTC-5")
    private Date fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC-5", timezone = "UTC-5")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC-5", timezone = "UTC-5")
    private Date fechaFin;
    private boolean esActivo;
    private String enlace;
    private Set<Integer> clientesId;
    private Integer centrocClienteId;
    private boolean todos;
    private boolean todosEmpleados;
    private Integer grupoNominaId;
    private Set<Integer> personasId;
}
