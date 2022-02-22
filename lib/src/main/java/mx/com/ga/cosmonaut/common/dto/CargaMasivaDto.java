package mx.com.ga.cosmonaut.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CargaMasivaDto {

    private Integer centrocClienteId;
    private Integer personaId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaContrato;
    private Integer tipoCargaId;
    private Integer nominaPeriodoId;
    private byte[] archivo;

}
