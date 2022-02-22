package mx.com.ga.cosmonaut.common.dto.csd;

import lombok.Data;

@Data
public class CertificadoSelloDigitalRespuestaDto {

    private String codigo_resultado;
    private ContenidoDto[] contenido;
    private boolean exito;
    private String mensaje;
    private ResultadoDto[] resultado_servicio;
    private String tipo;

}
