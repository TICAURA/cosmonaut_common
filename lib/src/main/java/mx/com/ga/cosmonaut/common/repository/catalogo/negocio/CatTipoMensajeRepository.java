package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoMensaje;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatTipoMensajeRepository extends CrudRepository<CatTipoMensaje, Integer> {

    List<CatTipoMensaje> findByEsActivoOrderByDescripcion(Boolean activo);

}
