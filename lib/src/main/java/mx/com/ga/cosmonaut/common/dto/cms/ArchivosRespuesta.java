package mx.com.ga.cosmonaut.common.dto.cms;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArchivosRespuesta {

    private Integer id;
    private Integer obsoleto;
    private Integer numeroVersion;
    private BigDecimal sizeKb;
    private String hashMd5;

}
