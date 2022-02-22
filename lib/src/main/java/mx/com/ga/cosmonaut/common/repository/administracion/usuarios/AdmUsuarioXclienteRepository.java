package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarioXcliente;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmUsuarios;
import mx.com.ga.cosmonaut.common.entity.cliente.NclCentrocCliente;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmUsuarioXclienteRepository extends CrudRepository<AdmUsuarioXcliente, Integer> {

    Optional<AdmUsuarioXcliente> findByCentrocClienteIdAndUsuarioId(NclCentrocCliente centrocClienteId, AdmUsuarios usuarioId);

    @Join(value = "usuarioId", alias="u")
    @Join(value="centrocClienteId", alias="c")
    List<AdmUsuarioXcliente> findByUsuarioIdAndEsActivo(AdmUsuarios usuarioId, Boolean esActivo);

    @Join(value="centrocClienteId", alias="c")
    List<NclCentrocCliente> findCentrocClienteIdByUsuarioIdAndEsActivo(AdmUsuarios usuarioId, Boolean esActivo);

    void update(@Id Integer id, @Nullable Date fechaBaja, Boolean esActivo);

    @Query(value = "UPDATE adm_usuarioxcliente SET es_activo = false, fecha_baja = :fechaBaja WHERE usuario_id = :usuarioId ",
            nativeQuery = true)
    void inactivarTodosByUsuarioId(Integer usuarioId, Date fechaBaja);

    void deleteByUsuarioId(AdmUsuarios usuarioId);

}
