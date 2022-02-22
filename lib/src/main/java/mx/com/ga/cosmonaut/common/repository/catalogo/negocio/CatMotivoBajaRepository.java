package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatMotivoBaja;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatMotivoBajaRepository extends CrudRepository<CatMotivoBaja, Integer> {

    List<CatMotivoBaja> findByEsActivoOrderByDescripcion(Boolean activo);

    List<CatMotivoBaja> findByDescripcion(String descripcion);

    List<CatMotivoBaja> findByDescripcionIlikeOrderByDescripcion(String descripcion);

}