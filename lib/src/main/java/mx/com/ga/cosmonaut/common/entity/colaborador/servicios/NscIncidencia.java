package mx.com.ga.cosmonaut.common.entity.colaborador.servicios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.catalogo.ubicacion.CatEstadoIncidencia;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoIncapacidad;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoIncidencia;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatUnidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedEntity(value = "nsc_incidencia")
public class NscIncidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "incidencia_id")
    private Integer incidenciaId;
    @MappedProperty(value = "fecha_inicio")
    private Timestamp fechaInicio;
    @MappedProperty(value = "fecha_fin")
    private Timestamp fechaFin;
    @MappedProperty(value = "duracion")
    private Integer duracion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_contrato")
    private Date fechaContrato;
    @MappedProperty(value = "persona_id")
    private Integer personaId;
    @MappedProperty(value = "cliente_id")
    private Integer clienteId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "ultima_actualizacion")
    private Date ultimaActualizacion;
    @MappedProperty(value = "comentarios")
    private String comentarios;
    @MappedProperty(value = "he_tiempo")
    private Integer heTiempo;
    @MappedProperty(value = "pagar_por")
    private Integer pagarPor;
    @MappedProperty(value = "monto")
    private BigDecimal monto;
    @MappedProperty(value = "comentario_acepta_rechaza")
    private String comentarioAceptaRechza;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @MappedProperty(value = "fecha_aplicacion")
    private Date fechaAplicacion;
    @MappedProperty(value = "numero_folio")
    private String numeroFolio;
    @MappedProperty(value = "url_documento")
    private String urlDocumento;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;

    @MappedProperty(value = "ultimo_estado_incidencia_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "ultimo_estado_incidencia_id")
    private CatEstadoIncidencia ultimoEstadoIncidenciaId;

    @MappedProperty(value = "tipo_incapacidad_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_incapacidad_id")
    private CatTipoIncapacidad tipoIncapacidadId;

    @MappedProperty(value = "tipo_incidencia_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "tipo_incidencia_id")
    private CatTipoIncidencia tipoIncidenciaId;

    @MappedProperty(value = "unidad_medida_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "unidad_medida_id")
    private CatUnidad unidadMedidaId;

    @MappedProperty(value = "carga_incidencias_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "carga_incidencias_id")
    private NscCargaIncidencias cargaIncidenciasId;

}
