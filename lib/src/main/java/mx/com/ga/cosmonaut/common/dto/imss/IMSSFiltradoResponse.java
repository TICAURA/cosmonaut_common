package mx.com.ga.cosmonaut.common.dto.imss;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.Date;

@Data
@Introspected
public class IMSSFiltradoResponse {

    private String registro_patronal;
    private Integer kardex_colaborador_id;
    private Integer id_estatus;
    private String estatus;
    private String nombre;
    private String nombre2;
    private String apellidoPat;
    private String apellidoMat;
    private Double sbc;
    private Integer movimientoImssId;
    private String movimiento;
    private Date fecha_movimiento;
    private String politica;
    private Double salario_diario;
    private Double salario_diario_integrado;
    private boolean es_activo;
    private boolean es_imss;
    private String credencialesImssId;
    private String vigencia_movimiento;
    private Integer tipo_compensacion_id;
    private Integer  persona_id;
}