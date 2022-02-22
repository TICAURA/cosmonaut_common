package mx.com.ga.cosmonaut.common.repository.administracion.noticias;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;
import mx.com.ga.cosmonaut.common.entity.administracion.noticias.AdmCategoriaNoticias;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AdmCatalogosNoticiasRepository extends CrudRepository<AdmCategoriaNoticias,Integer> {
}
