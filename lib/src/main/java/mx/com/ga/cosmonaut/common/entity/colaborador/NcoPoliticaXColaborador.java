package mx.com.ga.cosmonaut.common.entity.colaborador;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "nco_contrato_colaborador")
public class NcoPoliticaXColaborador implements Serializable {

    private static final long serialVersionUID = 1L;
 

    @MappedProperty(value = "num_empleado")
    private String numEmpleado;
    @MappedProperty(value = "politica_id")
    private String politicaId;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "apellido_pat")
    private String apellidoPat;
    @MappedProperty(value = "apellido_mat")
    private String apellidoMat;
    private String puesto;
    private Boolean esEstandar;
   

}
