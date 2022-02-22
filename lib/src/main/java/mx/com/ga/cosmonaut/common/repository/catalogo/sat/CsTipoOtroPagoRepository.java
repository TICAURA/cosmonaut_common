package mx.com.ga.cosmonaut.common.repository.catalogo.sat;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.sat.CsTipoOtroPago;

import java.util.List;
@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CsTipoOtroPagoRepository extends CrudRepository<CsTipoOtroPago, String> {

    List<CsTipoOtroPago> findByEsActivoOrderByDescripcion(Boolean activo);

}
