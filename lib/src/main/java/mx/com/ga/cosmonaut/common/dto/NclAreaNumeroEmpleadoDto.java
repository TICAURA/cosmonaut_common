package mx.com.ga.cosmonaut.common.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;


@Data
@Introspected
public class NclAreaNumeroEmpleadoDto {

    private Integer areaId;
    private String descripcion;
    private String nombreCorto;
    private NclCentrocCliente nclCentrocCliente;
    private Integer count;
  
}
