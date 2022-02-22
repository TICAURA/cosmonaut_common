package mx.com.ga.cosmonaut.common.dto.consultas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

@Introspected
@Data
public class CentroCostosClienteConsulta {

    private Integer centrocClienteId;

    private String curp;
    private String rfc;
    private String razonSocial;
    private String nombre;
    private String urlLogo;

    private Timestamp fechaAlta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaConstitucion;

    private Boolean esActivo;
    private Boolean multiempresa;

    private URL url;

}
