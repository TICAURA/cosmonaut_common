package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import java.util.List;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatSumaHorasJornada;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatSumaHorasJornadaRepository extends CrudRepository<CatSumaHorasJornada, Integer>{
    List<CatSumaHorasJornada> findByEsActivoOrderByDescripcion(Boolean activo);
}