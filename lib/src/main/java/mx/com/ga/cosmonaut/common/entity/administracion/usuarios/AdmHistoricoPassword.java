package mx.com.ga.cosmonaut.common.entity.administracion.usuarios;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.micronaut.data.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedEntity(value = "adm_historico_password")
public class AdmHistoricoPassword implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(GeneratedValue.Type.IDENTITY)
    @MappedProperty(value = "historico_password_id")
    private Integer historicoPasswordId;

    @MappedProperty(value = "password")
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "UTC", timezone = "UTC")
    @DateCreated
    @MappedProperty(value = "fecha_alta")
    private Date fechaAlta;

    @MappedProperty(value = "usuario_id")
    @Relation(value = Relation.Kind.MANY_TO_ONE, mappedBy = "usuario_id")
    private AdmUsuarios usuarioId;

}
