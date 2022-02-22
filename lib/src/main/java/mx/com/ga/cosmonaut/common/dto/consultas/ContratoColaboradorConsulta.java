package mx.com.ga.cosmonaut.common.dto.consultas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.net.URL;
import java.util.Date;

@Data
@Introspected
public class ContratoColaboradorConsulta {

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroEmpleado;
    private String empresa;
    private String razonSocial;
    private String puesto;
    private String area;
    private String sede;
    private String urlImagen;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaContrato;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAltaImss;

    private boolean estatus;

    private Long porcentaje;
    private Long idArea;
    private Long idPuesto;
    private Long idEmpresa;
    private Long idSede;
    private Long idPersona;

    private URL url;

}
