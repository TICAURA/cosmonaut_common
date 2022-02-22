package mx.com.ga.cosmonaut.common.repository.catalogo.negocio;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.catalogo.negocio.CatTipoNomina;

import java.util.List;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CatTipoNominaRepository extends CrudRepository<CatTipoNomina, Long> {
    List<CatTipoNomina> findByEsActivoOrderByDescripcion(Boolean activo);

    @Query(value = "SELECT * FROM cat_tipo_nomina WHERE tipo_nomina_id = 2 OR tipo_nomina_id = 4 OR tipo_nomina_id = 7")
    List<CatTipoNomina> findByEsActivoAguinaldo(Boolean activo);
}
