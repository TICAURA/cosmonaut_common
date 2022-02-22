package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmMensajeChatCentrocostos;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmMensajeChatCentrocostosRepository extends CrudRepository<AdmMensajeChatCentrocostos, Integer> {

    List<AdmMensajeChatCentrocostos> findByCentrocClienteIdCentrocClienteId(Integer centrocClienteId);

    List<AdmMensajeChatCentrocostos> findByCentrocClienteIdCentrocClienteIdAndUsuarioIdUsuarioId(Integer centrocClienteId, Integer usuarioId);


}
