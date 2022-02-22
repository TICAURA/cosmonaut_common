package mx.com.ga.cosmonaut.common.repository.administracion.usuarios;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.usuarios.AdmEstatusUsuariosChat;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmEstatusUsuariosChatRepository extends CrudRepository<AdmEstatusUsuariosChat, Integer> {

    boolean existsByEnLineaAndUsuarioRh(boolean enLinea, boolean usuarioRh);

    boolean existsByEnLineaAndUsuarioIdUsuarioId(boolean enLinea, Integer usuarioId);

    boolean existsByUsuarioIdUsuarioIdAndUsuarioRh(Integer usuarioId, boolean usuarioRh);

    Integer findEstatusUsuariosChatIdByUsuarioIdUsuarioId(Integer usuarioId);

    void update(@Id Integer estatusUsuariosChatId, Boolean enLinea);

}
