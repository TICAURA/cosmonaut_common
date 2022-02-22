package mx.com.ga.cosmonaut.common.dto.imss;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.Date;

@Data
@Introspected
public class VariabilidadFiltradoResponse {

    private Integer variabilidadId;
    private String razonSocial;
    private Integer anioFiscal;
    private Integer bimestre;
    private String periodoCalculo;
    private Date fechaAplicacion;
    private Integer total_empleados;
    private Integer diasBimestre;
    private boolean recalcula;

}
