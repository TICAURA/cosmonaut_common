package mx.com.ga.cosmonaut.common.dto.confronta;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Data
@Introspected
public class TrabajadoresDto {

    private String registro_patronal;
    private String id_cliente;
    private String cliente;
    private String numero_empleado;
    private String nss;
    private String nombre_trabajador;
    private String tipo_movimiento;
    private String fecha_movimiento;
    private String integrado;

    public void complementar(TrabajadoresDto dto) {
        if (dto.getRegistro_patronal() != null && !dto.getRegistro_patronal().isBlank()) {
            this.registro_patronal = dto.getRegistro_patronal();
        }
        if (dto.getId_cliente() != null && !dto.getId_cliente().isBlank()) {
            this.registro_patronal = dto.getId_cliente();
        }
        if (dto.getCliente() != null && !dto.getCliente().isBlank()) {
            this.registro_patronal = dto.getCliente();
        }
        if (dto.getNumero_empleado() != null && !dto.getNumero_empleado().isBlank()) {
            this.registro_patronal = dto.getNumero_empleado();
        }
        if (dto.getNss() != null && !dto.getNss().isBlank()) {
            this.registro_patronal = dto.getNss();
        }
        if (dto.getNombre_trabajador() != null && !dto.getNombre_trabajador().isBlank()) {
            this.registro_patronal = dto.getNombre_trabajador();
        }
        if (dto.getTipo_movimiento() != null && !dto.getTipo_movimiento().isBlank()) {
            this.registro_patronal = dto.getTipo_movimiento();
        }
        if (dto.getFecha_movimiento() != null && !dto.getFecha_movimiento().isBlank()) {
            this.registro_patronal = dto.getFecha_movimiento();
        }
        if (dto.getIntegrado() != null && !dto.getIntegrado().isBlank()) {
            this.registro_patronal = dto.getIntegrado();
        }
    }

}
