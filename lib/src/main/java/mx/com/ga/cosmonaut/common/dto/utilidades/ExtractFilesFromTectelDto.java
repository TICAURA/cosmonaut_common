package mx.com.ga.cosmonaut.common.dto.utilidades;

import lombok.Data;

@Data
public class ExtractFilesFromTectelDto {

    private String nombreArchivo;
    private byte[] archivo;

}
