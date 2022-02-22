package mx.com.ga.cosmonaut.common.dto.csd;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ContenidoDto {

    private String csd_id;
    private String advertencia;
    private String no_certificado;
    private String rfc;
    private String status_certificado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_final;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fecha_inicio;

}
