package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatPagosXliquidacion;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatPagosXliquidacionRepository extends CrudRepository<CatPagosXliquidacion,Integer> {

    List<CatPagosXliquidacion> findByEsActivoOrderByDescripcion(Boolean activo);
}
