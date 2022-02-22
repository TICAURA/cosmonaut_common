package mx.com.ga.cosmonaut.common.dto.imss;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import mx.com.ga.cosmonaut.common.dto.csd.ContenidoDto;
import mx.com.ga.cosmonaut.common.dto.csd.ResultadoDto;

@Data
public class GuardarCsdImssResponseDto {

    @JsonProperty(value = "codigo_resultado")
    private String resultadoCodigo;
    private ContenidoDto contenido;
    private boolean exito;
    private String mensaje;
    @JsonProperty(value = "resultado_servicio")
    private ResultadoDto resultadoServicio;
    private String tipo;

}
