package mx.com.ga.cosmonaut.common.dto.reportes;

import lombok.Data;

@Data
public class TablaBasicaDto {

    private String descripcion;
    private String valor;

    public TablaBasicaDto(String descripcion, String valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public TablaBasicaDto() {
    }
}
