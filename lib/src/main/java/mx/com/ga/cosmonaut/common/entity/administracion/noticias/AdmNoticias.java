package mx.com.ga.cosmonaut.common.entity.administracion.noticias;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import io.micronaut.data.annotation.repeatable.JoinSpecifications;
import io.micronaut.data.jdbc.annotation.JoinTable;
import lombok.Data;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;
import mx.com.ga.cosmonaut.common.entity.colaborador.NcoPersona;

import java.util.Date;
import java.util.Set;

@Data
@MappedEntity(value = "adm_noticias")
public class AdmNoticias {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(value= GeneratedValue.Type.IDENTITY)
    @MappedProperty(value="noticia_id")
    private Integer noticiaId;
    @MappedProperty(value="usuario_id")
    private Integer usuarioId;
    @MappedProperty(value="titulo")
    private String titulo;
    @MappedProperty(value="subtitulo")
    private String subtitulo;
    @MappedProperty(value="categoria_noticia_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "categoria_noticia_id")
    private AdmCategoriaNoticias categoriaId;
    @MappedProperty(value = "contenido")
    private String contenido;
    @MappedProperty(value = "thumbnail")
    private String thumbnail;
    @MappedProperty(value = "imagen")
    private String imagen;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC-5", timezone = "UTC-5")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;
    @MappedProperty(value = "fecha_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    private Date fechaInicio;
    @MappedProperty(value = "fecha_fin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    private Date fechaFin;
    @MappedProperty(value = "es_activo")
    private boolean esActivo;
    @MappedProperty(value = "enlace")
    private String enlace;
    @JoinTable(name="adm_noticiasxcliente",joinColumns = {@MappedProperty(value = "noticia_id"),@MappedProperty(value = "centroc_cliente_id")})
    @Relation(value = Relation.Kind.MANY_TO_MANY)
    private Set<NclCentrocCliente> centrocClienteId;
    @MappedProperty(value = "cliente_id")
    private Integer clienteId;
    @MappedProperty(value = "todos")
    private boolean todos;
    @MappedProperty(value = "todos_empleados")
    private boolean todosEmpleados;
    @MappedProperty(value = "grupo_nomina_id")
    private Integer grupoNominaId;
    @JoinTable(name="adm_noticiasxpersona",joinColumns = {@MappedProperty(value = "noticia_id"),@MappedProperty(value = "persona_id")})
    @Relation(value = Relation.Kind.MANY_TO_MANY)
    private Set<NcoPersona> personasId;
}
