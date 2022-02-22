package mx.com.ga.cosmonaut.common.entity.colaborador;

import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;

@Data
@MappedEntity(value = "nco_contrato_colaborador")
public class NcoPuestoXColaborador implements Serializable {

    private static final long serialVersionUID = 1L;
 

    @MappedProperty(value = "num_empleado")
    private String numEmpleado;
    @MappedProperty(value = "area_id")
    private String areaId;
    @MappedProperty(value = "puesto_id")
    private String puestoId;
    private String area;
    private String puesto;
    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;
    private String razonSocial;
    @MappedProperty(value = "nombre")
    private String nombre;
    @MappedProperty(value = "apellido_pat")
    private String apellidoPat;
    @MappedProperty(value = "apellido_mat")
    private String apellidoMat;
   

}
