package mx.com.ga.cosmonaut.common.dto.confronta;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ZipTextFileDto {

    private String nombre;
    private List<String> contenido = new ArrayList<>();

}
