package mx.com.ga.cosmonaut.common.dto.csd;

import lombok.Data;

@Data
public class CertificadoSelloDigitalPeticionDto {

    private String servicio;
    private String certificado;
    private String csd_cer;
    private String csd_key;
    private String csd_pass;

}
