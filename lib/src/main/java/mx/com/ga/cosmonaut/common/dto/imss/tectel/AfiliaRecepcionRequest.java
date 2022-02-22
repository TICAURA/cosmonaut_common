package mx.com.ga.cosmonaut.common.dto.imss.tectel;

import lombok.Data;

import java.util.List;

@Data
public class AfiliaRecepcionRequest {

    private String idCsd;
    private String registroPatronal;
    private List<Long> movimientosKardexIds;
    private Integer clienteId;

}
