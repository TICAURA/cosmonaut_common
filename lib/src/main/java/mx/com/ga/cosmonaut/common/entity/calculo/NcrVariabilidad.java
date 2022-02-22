package mx.com.ga.cosmonaut.common.entity.calculo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoCuotaImss;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "ncr_variabilidad")
public class NcrVariabilidad implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "variabilidad_id")
    private Integer variabilidadId;

    @MappedProperty(value = "centroc_cliente_id")
    private Integer centrocClienteId;

    @MappedProperty(value = "anio_fiscal")
    private Integer anioFiscal;

    @MappedProperty(value = "bimestre")
    private Integer bimestre;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_aplicacion")
    private Date fechaAplicacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_inicio")
    private Date fechaInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_fin")
    private Date fechaFin;

    @MappedProperty(value = "dias_bimestre")
    private Integer diasBimestre;

    @MappedProperty(value = "total_empleados")
    private Integer totalEmpleados;

    @MappedProperty(value = "empleados_con_diferencia")
    private Integer empleadosConDiferencia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_procesamiento")
    private Date fechaProcesamiento;

    @MappedProperty(value = "estado_variabilidad_id_actual")
    private Short estadoVariabilidadIdActual;

    @MappedProperty(value = "empleados_sin_diferencia")
    private Integer empleadosSinDiferencia;

}
