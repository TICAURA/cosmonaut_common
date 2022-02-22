package mx.com.ga.cosmonaut.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class SqlDto {

    private String query;
    private List<Object> listaDatos;

}
